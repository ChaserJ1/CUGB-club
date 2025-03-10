package edu.cugb.subject.domain.service;

import edu.cugb.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/17 12:43
 * @Description:
 */
public interface SubjectCategoryDomainService {


    /**
     * 新增分类
     *
     * @param subjectCategoryBO
     */
    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询岗位分类
     *
     * @param subjectCategoryBO
     * @return
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 更新分类
     *
     * @param subjectCategoryBO
     * @return
     */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    /**
     * 删除分类
     *
     * @param subjectCategoryBO
     * @return
     */
    Boolean delete(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询分类、标签
     * @param subjectCategoryBO
     * @return
     */
    List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO);
}
