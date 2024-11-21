package edu.cugb.subject.domain.handler.subject;

import com.google.common.base.Preconditions;
import edu.cugb.subject.common.enums.IsDeletedFlagEnum;
import edu.cugb.subject.common.enums.SubjectInfoTypeEnum;
import edu.cugb.subject.domain.convert.SubjectTypeConverter;
import edu.cugb.subject.domain.entity.SubjectAnswerBO;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.domain.entity.SubjectOptionBO;
import edu.cugb.subject.infra.basic.entity.SubjectBrief;
import edu.cugb.subject.infra.basic.entity.SubjectJudge;
import edu.cugb.subject.infra.basic.entity.SubjectRadio;
import edu.cugb.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/25 19:45
 * @Description: 判断题目的策略类
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectJudgeService subjectJudgeService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //判断题插入
        SubjectJudge subjectJudge = new SubjectJudge();
        Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoBO.getOptionList()),
                "isCorrect不能为空");
        //从列表中获取第一个元素
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudgeService.insert(subjectJudge);
    }


    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId((long) subjectId);
        List<SubjectJudge> result = subjectJudgeService.queryByCondition(subjectJudge);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectTypeConverter.INSTANCE
                .convertJudgeListToAnswerBO(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }


}

