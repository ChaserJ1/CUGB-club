package edu.cugb.subject.application.convert;

import edu.cugb.subject.application.dto.SubjectCategoryDTO;
import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import edu.cugb.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/17 15:19
 * @Description:
 */
@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertBOToCategory(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convertBOToDTOList(List<SubjectCategoryBO> subjectCategoryBO);

    SubjectCategoryBO convertDTOToBO(SubjectCategoryDTO subjectCategoryDTO);

    SubjectCategoryDTO convertBOToDTO(SubjectCategoryBO subjectCategoryBO);
}
