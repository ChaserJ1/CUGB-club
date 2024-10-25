package edu.cugb.subject.domain.convert;

import edu.cugb.subject.domain.entity.SubjectLabelBO;
import edu.cugb.subject.infra.basic.entity.SubjectLabel;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-25T14:39:26+0800",
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
        if ( subjectLabelBO.getCategoryId() != null ) {
            subjectLabel.setCategoryId( String.valueOf( subjectLabelBO.getCategoryId() ) );
        }

        return subjectLabel;
    }
}
