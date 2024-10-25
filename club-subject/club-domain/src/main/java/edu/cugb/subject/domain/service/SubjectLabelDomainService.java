package edu.cugb.subject.domain.service;

import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import edu.cugb.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/17 12:43
 * @Description:
 */
public interface SubjectLabelDomainService {

    /**
     * 新增标签
     *
     * @param subjectLabelBO
     */
    Boolean add(SubjectLabelBO subjectLabelBO);

    /**
     * 更新标签
     *
     * @param subjectLabelBO
     * @return
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     *
     * @param subjectLabelBO
     * @return
     */
    Boolean delete(SubjectLabelBO subjectLabelBO);

    /**
     * 查询分类下标签
     *
     * @param subjectLabelBO
     * @return
     */
    List<SubjectLabelBO> queryByCategoryId(SubjectLabelBO subjectLabelBO);
}
