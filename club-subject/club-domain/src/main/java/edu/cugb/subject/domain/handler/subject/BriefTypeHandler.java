package edu.cugb.subject.domain.handler.subject;

import com.google.common.base.Preconditions;
import edu.cugb.subject.common.enums.IsDeletedFlagEnum;
import edu.cugb.subject.common.enums.SubjectInfoTypeEnum;
import edu.cugb.subject.domain.convert.SubjectTypeConverter;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.infra.basic.entity.SubjectBrief;
import edu.cugb.subject.infra.basic.entity.SubjectJudge;
import edu.cugb.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

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
        List<SubjectBrief> subjectBriefList = new LinkedList<>();
        Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoBO.getOptionList()),
                "选项不能为空");
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectBrief subjectBrief = SubjectTypeConverter.INSTANCE.convertAnswerBOToBrief(option);
            subjectBrief.setSubjectId(subjectInfoBO.getId());
            subjectBrief.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
            subjectBriefList.add(subjectBrief);
        });
        subjectBriefService.batchInsert(subjectBriefList);
    }
}
