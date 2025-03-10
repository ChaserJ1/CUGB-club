package edu.cugb.subject.domain.convert;

import edu.cugb.subject.domain.entity.SubjectLabelBO;
import edu.cugb.subject.infra.basic.entity.SubjectLabel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-08T18:39:59+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_152 (Oracle Corporation)"
)
public class SubjectLabelConverterImpl implements SubjectLabelConverter {

    @Override
    public SubjectLabel convertBOToLabel(SubjectLabelBO subjectLabelBO) {
        if ( subjectLabelBO == null ) {
            return null;
        }

        SubjectLabel subjectLabel = new SubjectLabel();

        subjectLabel.setId( subjectLabelBO.getId() );
        subjectLabel.setLabelName( subjectLabelBO.getLabelName() );
        subjectLabel.setSortNum( subjectLabelBO.getSortNum() );
        subjectLabel.setCategoryId( subjectLabelBO.getCategoryId() );

        return subjectLabel;
    }

    @Override
    public List<SubjectLabelBO> convertLabelListToBOList(List<SubjectLabel> subjectLabelList) {
        if ( subjectLabelList == null ) {
            return null;
        }

        List<SubjectLabelBO> list = new ArrayList<SubjectLabelBO>( subjectLabelList.size() );
        for ( SubjectLabel subjectLabel : subjectLabelList ) {
            list.add( subjectLabelToSubjectLabelBO( subjectLabel ) );
        }

        return list;
    }

    protected SubjectLabelBO subjectLabelToSubjectLabelBO(SubjectLabel subjectLabel) {
        if ( subjectLabel == null ) {
            return null;
        }

        SubjectLabelBO subjectLabelBO = new SubjectLabelBO();

        subjectLabelBO.setId( subjectLabel.getId() );
        subjectLabelBO.setCategoryId( subjectLabel.getCategoryId() );
        subjectLabelBO.setLabelName( subjectLabel.getLabelName() );
        subjectLabelBO.setSortNum( subjectLabel.getSortNum() );

        return subjectLabelBO;
    }
}
