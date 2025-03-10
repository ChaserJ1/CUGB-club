package edu.cugb.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mysql.cj.xdevapi.JsonArray;
import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.cugb.subject.common.enums.CategoryTypeEnum;
import edu.cugb.subject.common.enums.IsDeletedFlagEnum;
import edu.cugb.subject.domain.convert.SubjectCategoryConverter;
import edu.cugb.subject.domain.convert.SubjectLabelConverter;
import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import edu.cugb.subject.domain.entity.SubjectLabelBO;
import edu.cugb.subject.domain.service.SubjectCategoryDomainService;
import edu.cugb.subject.domain.service.SubjectLabelDomainService;
import edu.cugb.subject.domain.service.util.CacheUtil;
import edu.cugb.subject.infra.basic.entity.SubjectCategory;
import edu.cugb.subject.infra.basic.entity.SubjectLabel;
import edu.cugb.subject.infra.basic.entity.SubjectMapping;
import edu.cugb.subject.infra.basic.service.SubjectCategoryService;
import edu.cugb.subject.infra.basic.service.SubjectLabelService;
import edu.cugb.subject.infra.basic.service.SubjectMappingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Author pengjia
 * @Data 2024/10/17 14:39
 * @Description:
 */
@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    @Resource
    private SubjectCategoryService subjectCategoryService;
    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private ThreadPoolExecutor labelThreadPool;

    @Resource
    private CacheUtil cacheUtil;





    /**
     * 新增分类
     *
     * @param subjectCategoryBO
     */
    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.add.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBOToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
        subjectCategoryService.insert(subjectCategory);

    }

    /**
     * 根据分类类型categoryType查询分类
     *
     * @param subjectCategoryBO
     * @return
     */
    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBOToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());

        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);

        List<SubjectCategoryBO> boList = SubjectCategoryConverter.INSTANCE
                .convertCategoryToBO(subjectCategoryList);

        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryPrimaryCategory.boList:{}",
                    JSON.toJSONString(boList));
        }
        boList.forEach(bo -> {
            Integer subjectCount = subjectCategoryService.queryCategoryCount(bo.getId());
            bo.setCount(subjectCount);
        });
        return boList;
    }

    /**
     * 更新分类
     *
     * @param subjectCategoryBO
     * @return
     */
    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBOToCategory(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    /**
     * 删除分类
     *
     * @param subjectCategoryBO
     * @return
     */
    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBOToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int delete = subjectCategoryService.delete(subjectCategory);
        return true;

    }

    /**
     * 根据前端传来的大类id，查询分类、标签
     *
     * @param subjectCategoryBO
     * @return
     */
    @Override
    @SneakyThrows
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        //本地缓存
        String cacheKey = "categoryAndLabel." + subjectCategoryBO.getId();
        List<SubjectCategoryBO> SubjectCategoryBOS = cacheUtil.getResult(cacheKey, SubjectCategoryBO.class,
                (key) -> getSubjectCategoryBOS(subjectCategoryBO.getId()));
        return SubjectCategoryBOS;
    }

    @NotNull
    private List<SubjectCategoryBO> getSubjectCategoryBOS(Long categoryId) {
        //查询大类下所有分类
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(categoryId);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryCategoryAndLabel.subjectCategoryList:{}",
                    JSON.toJSONString(subjectCategoryList));
        }
        List<SubjectCategoryBO> categoryBOList = SubjectCategoryConverter.INSTANCE.convertCategoryToBO(subjectCategoryList);
        HashMap<Long, List<SubjectLabelBO>> map = new HashMap<>();
        //创建了一个包含 CompletableFuture 对象的列表，
        // 每个 CompletableFuture 都会在 labelThreadPool 线程池中
        // 异步执行 getLabelBOList(categoryBO) 操作
        List<CompletableFuture<Map<Long, List<SubjectLabelBO>>>> completableFutureList =
                categoryBOList.stream()
                .map(categoryBO -> CompletableFuture.supplyAsync(
                        () -> getLabelBOList(categoryBO),
                        labelThreadPool
                ))
                .collect(Collectors.toList());
        //completableFutureList数组，将数组中的每个元素的map存入一个新的hashmap集合中
        completableFutureList.forEach(mapCompletableFuture -> {
                    try {
                        Map<Long, List<SubjectLabelBO>> resultMap = mapCompletableFuture.get();
                        map.putAll(resultMap);
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        //依次获取标签信息
       /* List<FutureTask<Map<Long, List<SubjectLabelBO>>>> futureTaskList = new ArrayList<>();


        //线程池并发调用,异步查询labelBOList
        //1.将原有的单次从数据库中查询labelBOList的同步过程转换成为往线程池中提交任务的异步过程，
        // 并将结果装入一个map中，K为categoryId，V为对应labelBOList，并将这个map装入futureTaskList数组中
        categoryBOList.forEach(categoryBo -> {
            FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask = new FutureTask<>(() ->
                    getLabelBOList(categoryBo)
            );
            futureTaskList.add(futureTask);
            labelThreadPool.submit(futureTask);
        });
        //2.遍历futureTaskList数组，将不为空的map存入一个新的map中
        for (FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask : futureTaskList) {
            Map<Long, List<SubjectLabelBO>> resultMap = futureTask.get();
            if (CollectionUtils.isEmpty(resultMap)) {
                continue;
            }
            map.putAll(resultMap);
        }*/
        //3.对categoryBOList循环遍历，将新map中的labelBOList插入对应的category中
        categoryBOList.forEach(categoryBO -> {
            categoryBO.setSubjectLabelBOList(map.get(categoryBO.getId()));
        });
        //这样就可以一次性查询到分类以及分类下的标签
        return categoryBOList;
    }

    /**
     * 根据CategoryId拿到对应的labelBOList，放入map中返回
     *
     * @param categoryBO
     * @return
     */
    private Map<Long, List<SubjectLabelBO>> getLabelBOList(SubjectCategoryBO categoryBO) {
        if (log.isInfoEnabled()){
            log.info("getLabelBOList:{}",JSON.toJSONString(categoryBO));
        }
        Map<Long, List<SubjectLabelBO>> labelMap = new HashMap<>();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryBO.getId());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return null;
        }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> labelBOList = SubjectLabelConverter.INSTANCE.convertLabelListToBOList(labelList);
        labelMap.put(categoryBO.getId(), labelBOList);
        return labelMap;
    }
}
