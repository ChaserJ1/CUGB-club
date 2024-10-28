package edu.cugb.subject.domain.convert;

import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.domain.entity.SubjectLabelBO;
import edu.cugb.subject.infra.basic.entity.SubjectInfo;
import edu.cugb.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author pengjia
 * @Data 2024/10/17 15:19
 * @Description:
 */
@Mapper
public interface SubjectInfoConverter {
    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBOToInfo(SubjectInfoBO subjectInfoBO);


}
