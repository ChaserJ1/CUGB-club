package edu.cugb.subject.application.convert;

import edu.cugb.subject.application.dto.SubjectCategoryDTO;
import edu.cugb.subject.application.dto.SubjectLabelDTO;
import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import edu.cugb.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/17 15:19
 * @Description: 标签DTO转换
 */
@Mapper
public interface SubjectLabelDTOConverter {
    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDTOToBO(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertBOLIstToDTOList(List<SubjectLabelBO> subjectLabelBOList);
}
