package edu.cugb.subject.domain.convert;

import edu.cugb.subject.domain.entity.SubjectAnswerBO;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.infra.basic.entity.SubjectBrief;
import edu.cugb.subject.infra.basic.entity.SubjectJudge;
import edu.cugb.subject.infra.basic.entity.SubjectMultiple;
import edu.cugb.subject.infra.basic.entity.SubjectRadio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T21:44:54+0800",
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
        subjectRadio.setIsDeleted( subjectAnswerBO.getIsDeleted() );

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
        subjectMultiple.setIsDeleted( subjectAnswerBO.getIsDeleted() );

        return subjectMultiple;
    }

    @Override
    public SubjectBrief convertBOToBrief(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectBrief subjectBrief = new SubjectBrief();

        subjectBrief.setId( subjectInfoBO.getId() );
        subjectBrief.setSubjectAnswer( subjectInfoBO.getSubjectAnswer() );

        return subjectBrief;
    }

    @Override
    public List<SubjectAnswerBO> convertJudgeListToAnswerBO(List<SubjectJudge> subjectJudgeList) {
        if ( subjectJudgeList == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( subjectJudgeList.size() );
        for ( SubjectJudge subjectJudge : subjectJudgeList ) {
            list.add( subjectJudgeToSubjectAnswerBO( subjectJudge ) );
        }

        return list;
    }

    @Override
    public List<SubjectAnswerBO> convertMultipleListToAnswerBO(List<SubjectMultiple> result) {
        if ( result == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( result.size() );
        for ( SubjectMultiple subjectMultiple : result ) {
            list.add( subjectMultipleToSubjectAnswerBO( subjectMultiple ) );
        }

        return list;
    }

    @Override
    public List<SubjectAnswerBO> convertRadioListToAnswerBO(List<SubjectRadio> result) {
        if ( result == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( result.size() );
        for ( SubjectRadio subjectRadio : result ) {
            list.add( subjectRadioToSubjectAnswerBO( subjectRadio ) );
        }

        return list;
    }

    protected SubjectAnswerBO subjectJudgeToSubjectAnswerBO(SubjectJudge subjectJudge) {
        if ( subjectJudge == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        if ( subjectJudge.getIsCorrect() != null ) {
            subjectAnswerBO.setIsCorrect( subjectJudge.getIsCorrect() );
        }
        subjectAnswerBO.setIsDeleted( subjectJudge.getIsDeleted() );

        return subjectAnswerBO;
    }

    protected SubjectAnswerBO subjectMultipleToSubjectAnswerBO(SubjectMultiple subjectMultiple) {
        if ( subjectMultiple == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectMultiple.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectMultiple.getOptionContent() );
        if ( subjectMultiple.getIsCorrect() != null ) {
            subjectAnswerBO.setIsCorrect( subjectMultiple.getIsCorrect() );
        }
        subjectAnswerBO.setIsDeleted( subjectMultiple.getIsDeleted() );

        return subjectAnswerBO;
    }

    protected SubjectAnswerBO subjectRadioToSubjectAnswerBO(SubjectRadio subjectRadio) {
        if ( subjectRadio == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        if ( subjectRadio.getOptionType() != null ) {
            subjectAnswerBO.setOptionType( subjectRadio.getOptionType().longValue() );
        }
        subjectAnswerBO.setOptionContent( subjectRadio.getOptionContent() );
        if ( subjectRadio.getIsCorrect() != null ) {
            subjectAnswerBO.setIsCorrect( subjectRadio.getIsCorrect() );
        }
        subjectAnswerBO.setIsDeleted( subjectRadio.getIsDeleted() );

        return subjectAnswerBO;
    }
}
