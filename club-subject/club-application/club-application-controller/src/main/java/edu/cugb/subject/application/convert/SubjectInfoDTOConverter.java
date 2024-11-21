package edu.cugb.subject.application.convert;

import edu.cugb.subject.application.dto.SubjectInfoDTO;
import edu.cugb.subject.common.entity.PageResult;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author pengjia
 * @Data 2024/10/17 15:19
 * @Description: 标签DTO转换
 */
@Mapper
public interface SubjectInfoDTOConverter {
    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDTOToBO(SubjectInfoDTO subjectInfoDTO);


    SubjectInfoDTO convertBOToDTO(SubjectInfoBO result);
}
