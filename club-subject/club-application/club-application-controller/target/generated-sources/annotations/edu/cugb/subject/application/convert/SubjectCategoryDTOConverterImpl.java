package edu.cugb.subject.application.convert;

import edu.cugb.subject.application.dto.SubjectCategoryDTO;
import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-08T18:40:02+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_152 (Oracle Corporation)"
)
public class SubjectCategoryDTOConverterImpl implements SubjectCategoryDTOConverter {

    @Override
    public SubjectCategoryBO convertBOToCategory(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( subjectCategoryDTO.getId() );
        subjectCategoryBO.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBO.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBO.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBO.setParentId( subjectCategoryDTO.getParentId() );
        subjectCategoryBO.setCount( subjectCategoryDTO.getCount() );
        subjectCategoryBO.setIsDeleted( subjectCategoryDTO.getIsDeleted() );

        return subjectCategoryBO;
    }

    @Override
    public List<SubjectCategoryDTO> convertBOToDTOList(List<SubjectCategoryBO> subjectCategoryBO) {
        if ( subjectCategoryBO == null ) {
            return null;
        }

        List<SubjectCategoryDTO> list = new ArrayList<SubjectCategoryDTO>( subjectCategoryBO.size() );
        for ( SubjectCategoryBO subjectCategoryBO1 : subjectCategoryBO ) {
            list.add( convertBOToDTO( subjectCategoryBO1 ) );
        }

        return list;
    }

    @Override
    public SubjectCategoryBO convertDTOToBO(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( subjectCategoryDTO.getId() );
        subjectCategoryBO.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBO.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBO.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBO.setParentId( subjectCategoryDTO.getParentId() );
        subjectCategoryBO.setCount( subjectCategoryDTO.getCount() );
        subjectCategoryBO.setIsDeleted( subjectCategoryDTO.getIsDeleted() );

        return subjectCategoryBO;
    }

    @Override
    public SubjectCategoryDTO convertBOToDTO(SubjectCategoryBO subjectCategoryBO) {
        if ( subjectCategoryBO == null ) {
            return null;
        }

        SubjectCategoryDTO subjectCategoryDTO = new SubjectCategoryDTO();

        subjectCategoryDTO.setId( subjectCategoryBO.getId() );
        subjectCategoryDTO.setCategoryName( subjectCategoryBO.getCategoryName() );
        subjectCategoryDTO.setCategoryType( subjectCategoryBO.getCategoryType() );
        subjectCategoryDTO.setImageUrl( subjectCategoryBO.getImageUrl() );
        subjectCategoryDTO.setParentId( subjectCategoryBO.getParentId() );
        subjectCategoryDTO.setCount( subjectCategoryBO.getCount() );
        subjectCategoryDTO.setIsDeleted( subjectCategoryBO.getIsDeleted() );

        return subjectCategoryDTO;
    }
}
