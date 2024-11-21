package edu.cugb.subject.domain.convert;

import edu.cugb.subject.domain.entity.SubjectAnswerBO;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.domain.entity.SubjectOptionBO;
import edu.cugb.subject.infra.basic.entity.SubjectBrief;
import edu.cugb.subject.infra.basic.entity.SubjectJudge;
import edu.cugb.subject.infra.basic.entity.SubjectMultiple;
import edu.cugb.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/17 15:19
 * @Description:
 */
@Mapper
public interface SubjectTypeConverter {
    SubjectTypeConverter INSTANCE = Mappers.getMapper(SubjectTypeConverter.class);

    SubjectRadio convertAnswerBOToRadio(SubjectAnswerBO subjectAnswerBO);


    SubjectMultiple convertAnswerBOToMultiple(SubjectAnswerBO subjectAnswerBO);


    SubjectBrief convertBOToBrief(SubjectInfoBO subjectInfoBO);


    List<SubjectAnswerBO> convertJudgeListToAnswerBO(List<SubjectJudge> subjectJudgeList);

    List<SubjectAnswerBO> convertMultipleListToAnswerBO(List<SubjectMultiple> result);

    List<SubjectAnswerBO> convertRadioListToAnswerBO(List<SubjectRadio> result);
}
