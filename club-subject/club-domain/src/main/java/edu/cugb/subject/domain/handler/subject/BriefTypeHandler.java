package edu.cugb.subject.domain.handler.subject;

import com.google.common.base.Preconditions;
import edu.cugb.subject.common.enums.IsDeletedFlagEnum;
import edu.cugb.subject.common.enums.SubjectInfoTypeEnum;
import edu.cugb.subject.domain.convert.SubjectTypeConverter;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.domain.entity.SubjectOptionBO;
import edu.cugb.subject.infra.basic.entity.SubjectBrief;
import edu.cugb.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author pengjia
 * @Data 2024/10/25 19:45
 * @Description: 简答题目的策略类
 */
@Component
public class BriefTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //简答题插入
        SubjectBrief subjectBrief = SubjectTypeConverter.INSTANCE.convertBOToBrief(subjectInfoBO);
        Preconditions.checkNotNull(subjectInfoBO.getSubjectAnswer(), "简答题答案不能为空");
        Preconditions.checkNotNull(subjectInfoBO.getSubjectParse(), "简答题解析不能为空");
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBrief.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId((long) subjectId);
        SubjectBrief result = subjectBriefService.queryByCondition(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;

    }
}
