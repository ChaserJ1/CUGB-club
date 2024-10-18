package edu.cugb.subject.application.convert;

import edu.cugb.subject.application.dto.SubjectCategoryDTO;
import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-17T17:43:35+0800",
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
        subjectCategoryBO.setIsDeleted( subjectCategoryDTO.getIsDeleted() );

        return subjectCategoryBO;
    }
}
