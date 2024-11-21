package edu.cugb.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.cugb.subject.common.entity.PageResult;
import edu.cugb.subject.common.enums.IsDeletedFlagEnum;
import edu.cugb.subject.domain.convert.SubjectInfoConverter;
import edu.cugb.subject.domain.convert.SubjectLabelConverter;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.domain.entity.SubjectLabelBO;
import edu.cugb.subject.domain.entity.SubjectOptionBO;
import edu.cugb.subject.domain.handler.subject.SubjectHandlerFactory;
import edu.cugb.subject.domain.handler.subject.SubjectTypeHandler;
import edu.cugb.subject.domain.service.SubjectInfoDomainService;
import edu.cugb.subject.domain.service.SubjectLabelDomainService;
import edu.cugb.subject.infra.basic.entity.SubjectInfo;
import edu.cugb.subject.infra.basic.entity.SubjectLabel;
import edu.cugb.subject.infra.basic.entity.SubjectMapping;
import edu.cugb.subject.infra.basic.service.SubjectInfoService;
import edu.cugb.subject.infra.basic.service.SubjectLabelService;
import edu.cugb.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author pengjia
 * @Data 2024/10/17 14:39
 * @Description:wwww
 */
@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectHandlerFactory subjectHandlerFactory;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectLabelService subjectLabelService;


    /**
     * 新增分类
     *
     * @param subjectInfoBO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoController.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }
        //采取工厂+策略的模式来新增分类，工厂包含四种类型，根据传入的type自动映射选择处理  主表info+各自类型的题目
        //主表信息插入
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBOToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);

        //类型特有信息插入
        SubjectTypeHandler handler = subjectHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);

        //mapping表的信息插入(题目id、分类id、标签id的映射关系)
        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> mappingList = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfoBO.getId());
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setLabelId(Long.valueOf(labelId));
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
                mappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(mappingList);
    }

    /**
     * 分页查询题目列表
     *
     * @param subjectInfoBO
     * @return
     */
    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(@NotNull SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize() + 1;
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBOToInfo(subjectInfoBO);
        int count = subjectInfoService.countByCondition(subjectInfo,
                subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId());
        if (count == 0) {
            return pageResult;
        }
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, start,
                subjectInfoBO.getPageSize(),
                subjectInfoBO.getCategoryId(),
                subjectInfoBO.getLabelId());
        List<SubjectInfoBO> subjectInfoBOList = SubjectInfoConverter.INSTANCE.
                convertInfoListToBOList(subjectInfoList);
        pageResult.setRecords(subjectInfoBOList);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public SubjectInfoBO getSubjectInfo(SubjectInfoBO subjectInfoBO) {
        //根据题目id查询题目主体信息
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        if (subjectInfo == null) {
            // 处理 subjectInfo 为空的情况
            throw new IllegalArgumentException("找不到ID为: " + subjectInfoBO.getId() + "的题目信息");
        }
        //根据题目类别查询题目答案信息
        SubjectTypeHandler handler = subjectHandlerFactory.getHandler(subjectInfo.getSubjectType());
        SubjectOptionBO subjectOptionBO = handler.query(subjectInfoBO.getId().intValue());
        //拼接成bo回传
        SubjectInfoBO infoBO = SubjectInfoConverter.INSTANCE.convertOptionBOAndInfoToBO(subjectInfo, subjectOptionBO);
        //查询标签信息
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNameList = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        infoBO.setLabelNameList(labelNameList);
        return infoBO;
    }
}