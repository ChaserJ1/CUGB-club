package edu.cugb.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import edu.cugb.subject.common.enums.IsDeletedFlagEnum;
import edu.cugb.subject.domain.convert.SubjectInfoConverter;
import edu.cugb.subject.domain.convert.SubjectLabelConverter;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.domain.entity.SubjectLabelBO;
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
import org.springframework.stereotype.Service;
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


    /**
     * 新增分类
     *
     * @param subjectInfoBO
     */
    @Override
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
                mappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(mappingList);
    }
}
