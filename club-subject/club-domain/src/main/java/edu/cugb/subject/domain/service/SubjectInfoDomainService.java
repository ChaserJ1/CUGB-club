package edu.cugb.subject.domain.service;

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


}
