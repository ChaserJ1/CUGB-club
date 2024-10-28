package edu.cugb.subject.domain.convert;

import edu.cugb.subject.domain.entity.SubjectAnswerBO;
import edu.cugb.subject.infra.basic.entity.SubjectBrief;
import edu.cugb.subject.infra.basic.entity.SubjectJudge;
import edu.cugb.subject.infra.basic.entity.SubjectMultiple;
import edu.cugb.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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

    SubjectJudge convertAnswerBOToJudge(SubjectAnswerBO subjectAnswerBO);

    SubjectBrief convertAnswerBOToBrief(SubjectAnswerBO subjectAnswerBO);
}
