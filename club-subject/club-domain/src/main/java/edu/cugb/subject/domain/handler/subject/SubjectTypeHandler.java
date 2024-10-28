package edu.cugb.subject.domain.handler.subject;

import edu.cugb.subject.common.enums.SubjectInfoTypeEnum;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.infra.basic.entity.SubjectInfo;
import org.springframework.stereotype.Component;

/**
 * @Author pengjia
 * @Data 2024/10/25 19:42
 * @Description:
 */

public interface SubjectTypeHandler {
    /**
     * 题目类型识别
     *
     * @return
     */
    SubjectInfoTypeEnum getHandlerType();


    /**
     * 实际的题目的插入
     *
     * @param subjectInfoBO
     */
    void add(SubjectInfoBO subjectInfoBO);
}
