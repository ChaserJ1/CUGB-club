package edu.cugb.subject.domain.convert;

import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import edu.cugb.subject.infra.basic.entity.SubjectCategory;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-17T17:27:20+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_152 (Oracle Corporation)"
)
public class SubjectCategoryConverterImpl implements SubjectCategoryConverter {

    @Override
    public SubjectCategory convertBOToCategory(SubjectCategoryBO subjectCategoryBO) {
        if ( subjectCategoryBO == null ) {
            return null;
        }

        SubjectCategory subjectCategory = new SubjectCategory();

        subjectCategory.setId( subjectCategoryBO.getId() );
        subjectCategory.setCategoryName( subjectCategoryBO.getCategoryName() );
        subjectCategory.setCategoryType( subjectCategoryBO.getCategoryType() );
        subjectCategory.setImageUrl( subjectCategoryBO.getImageUrl() );
        subjectCategory.setParentId( subjectCategoryBO.getParentId() );
        subjectCategory.setIsDeleted( subjectCategoryBO.getIsDeleted() );

        return subjectCategory;
    }
}
