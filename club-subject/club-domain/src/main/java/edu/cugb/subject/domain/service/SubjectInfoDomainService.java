package edu.cugb.subject.domain.service;

import edu.cugb.subject.common.entity.PageResult;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/17 12:43
 * @Description:
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     *
     * @param subjectInfoBO
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 分页查询题目
     *
     * @param subjectInfoBO
     * @return
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目信息
     *
     * @param subjectInfoBO
     * @return
     */
    SubjectInfoBO getSubjectInfo(SubjectInfoBO subjectInfoBO);
}
