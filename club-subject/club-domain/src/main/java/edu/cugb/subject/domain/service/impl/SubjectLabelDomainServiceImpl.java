package edu.cugb.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import edu.cugb.subject.common.enums.IsDeletedFlagEnum;
import edu.cugb.subject.domain.convert.SubjectCategoryConverter;
import edu.cugb.subject.domain.convert.SubjectLabelConverter;
import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import edu.cugb.subject.domain.entity.SubjectLabelBO;
import edu.cugb.subject.domain.service.SubjectLabelDomainService;
import edu.cugb.subject.infra.basic.entity.SubjectCategory;
import edu.cugb.subject.infra.basic.entity.SubjectLabel;
import edu.cugb.subject.infra.basic.entity.SubjectMapping;
import edu.cugb.subject.infra.basic.service.SubjectLabelService;
import edu.cugb.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author pengjia
 * @Data 2024/10/17 14:39
 * @Description:
 */
@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private SubjectMappingService subjectMappingService;


    /**
     * 新增分类
     *
     * @param subjectLabelBO
     */
    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelController.add.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBOToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
        int count = subjectLabelService.insert(subjectLabel);
        return count > 0;
    }

    /**
     * 更新分类
     *
     * @param subjectLabelBO
     * @return
     */
    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBOToLabel(subjectLabelBO);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBOToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public List<SubjectLabelBO> queryByCategoryId(SubjectLabelBO subjectLabelBO) {
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return Collections.emptyList();
        }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> boList = new LinkedList<>();
        labelList.forEach(label -> {
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setId(label.getId());
            bo.setLabelName(label.getLabelName());
            bo.setSortNum(label.getSortNum());
            bo.setCategoryId(categoryId);
            boList.add(bo);
        });
        return boList;
    }


}
