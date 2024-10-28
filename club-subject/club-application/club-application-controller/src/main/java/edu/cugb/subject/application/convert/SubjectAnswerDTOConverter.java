package edu.cugb.subject.application.convert;

import edu.cugb.subject.application.dto.SubjectAnswerDTO;
import edu.cugb.subject.application.dto.SubjectInfoDTO;
import edu.cugb.subject.domain.entity.SubjectAnswerBO;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/17 15:19
 * @Description: 标签DTO转换
 */
@Mapper
public interface SubjectAnswerDTOConverter {
    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    SubjectAnswerBO convertDTOToBO(SubjectAnswerDTO subjectAnswerDTO);

    List<SubjectAnswerBO> convertDTOListToBOList(List<SubjectAnswerDTO> dtoList);


}
