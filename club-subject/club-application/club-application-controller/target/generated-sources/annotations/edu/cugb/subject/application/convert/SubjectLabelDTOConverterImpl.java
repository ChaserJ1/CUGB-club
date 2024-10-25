package edu.cugb.subject.application.convert;

import edu.cugb.subject.application.dto.SubjectLabelDTO;
import edu.cugb.subject.domain.entity.SubjectLabelBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-25T14:39:31+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_152 (Oracle Corporation)"
)
public class SubjectLabelDTOConverterImpl implements SubjectLabelDTOConverter {

    @Override
    public SubjectLabelBO convertDTOToBO(SubjectLabelDTO subjectLabelDTO) {
        if ( subjectLabelDTO == null ) {
            return null;
        }

        SubjectLabelBO subjectLabelBO = new SubjectLabelBO();

        subjectLabelBO.setId( subjectLabelDTO.getId() );
        subjectLabelBO.setCategoryId( subjectLabelDTO.getCategoryId() );
        subjectLabelBO.setLabelName( subjectLabelDTO.getLabelName() );
        subjectLabelBO.setSortNum( subjectLabelDTO.getSortNum() );

        return subjectLabelBO;
    }

    @Override
    public List<SubjectLabelDTO> convertBOLIstToDTOList(List<SubjectLabelBO> subjectLabelBOList) {
        if ( subjectLabelBOList == null ) {
            return null;
        }

        List<SubjectLabelDTO> list = new ArrayList<SubjectLabelDTO>( subjectLabelBOList.size() );
        for ( SubjectLabelBO subjectLabelBO : subjectLabelBOList ) {
            list.add( subjectLabelBOToSubjectLabelDTO( subjectLabelBO ) );
        }

        return list;
    }

    protected SubjectLabelDTO subjectLabelBOToSubjectLabelDTO(SubjectLabelBO subjectLabelBO) {
        if ( subjectLabelBO == null ) {
            return null;
        }

        SubjectLabelDTO subjectLabelDTO = new SubjectLabelDTO();

        subjectLabelDTO.setId( subjectLabelBO.getId() );
        subjectLabelDTO.setCategoryId( subjectLabelBO.getCategoryId() );
        subjectLabelDTO.setLabelName( subjectLabelBO.getLabelName() );
        subjectLabelDTO.setSortNum( subjectLabelBO.getSortNum() );

        return subjectLabelDTO;
    }
}
