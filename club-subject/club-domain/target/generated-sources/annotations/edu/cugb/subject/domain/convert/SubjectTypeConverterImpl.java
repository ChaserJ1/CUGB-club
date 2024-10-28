package edu.cugb.subject.domain.convert;

import edu.cugb.subject.domain.entity.SubjectAnswerBO;
import edu.cugb.subject.infra.basic.entity.SubjectBrief;
import edu.cugb.subject.infra.basic.entity.SubjectJudge;
import edu.cugb.subject.infra.basic.entity.SubjectMultiple;
import edu.cugb.subject.infra.basic.entity.SubjectRadio;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-27T19:37:39+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_152 (Oracle Corporation)"
)
public class SubjectTypeConverterImpl implements SubjectTypeConverter {

    @Override
    public SubjectRadio convertAnswerBOToRadio(SubjectAnswerBO subjectAnswerBO) {
        if ( subjectAnswerBO == null ) {
            return null;
        }

        SubjectRadio subjectRadio = new SubjectRadio();

        if ( subjectAnswerBO.getOptionType() != null ) {
            subjectRadio.setOptionType( subjectAnswerBO.getOptionType().intValue() );
        }
        subjectRadio.setOptionContent( subjectAnswerBO.getOptionContent() );
        subjectRadio.setIsCorrect( subjectAnswerBO.getIsCorrect() );

        return subjectRadio;
    }

    @Override
    public SubjectMultiple convertAnswerBOToMultiple(SubjectAnswerBO subjectAnswerBO) {
        if ( subjectAnswerBO == null ) {
            return null;
        }

        SubjectMultiple subjectMultiple = new SubjectMultiple();

        subjectMultiple.setOptionType( subjectAnswerBO.getOptionType() );
        subjectMultiple.setOptionContent( subjectAnswerBO.getOptionContent() );
        subjectMultiple.setIsCorrect( subjectAnswerBO.getIsCorrect() );

        return subjectMultiple;
    }

    @Override
    public SubjectJudge convertAnswerBOToJudge(SubjectAnswerBO subjectAnswerBO) {
        if ( subjectAnswerBO == null ) {
            return null;
        }

        SubjectJudge subjectJudge = new SubjectJudge();

        subjectJudge.setIsCorrect( subjectAnswerBO.getIsCorrect() );

        return subjectJudge;
    }

    @Override
    public SubjectBrief convertAnswerBOToBrief(SubjectAnswerBO subjectAnswerBO) {
        if ( subjectAnswerBO == null ) {
            return null;
        }

        SubjectBrief subjectBrief = new SubjectBrief();

        return subjectBrief;
    }
}
