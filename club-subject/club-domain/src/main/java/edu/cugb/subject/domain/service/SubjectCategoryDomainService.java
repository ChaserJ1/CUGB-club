package edu.cugb.subject.domain.service;

import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import edu.cugb.subject.infra.basic.entity.SubjectCategory;

import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/17 12:43
 * @Description:
 */
public interface SubjectCategoryDomainService {

    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询岗位分类
     *
     * @param subjectCategoryBO
     * @return
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);
}
