package edu.cugb.subject.domain.convert;

import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import edu.cugb.subject.domain.entity.SubjectLabelBO;
import edu.cugb.subject.infra.basic.entity.SubjectCategory;
import edu.cugb.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/17 15:19
 * @Description:
 */
@Mapper
public interface SubjectLabelConverter {
    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);

    SubjectLabel convertBOToLabel(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> convertLabelListToBOList(List<SubjectLabel> subjectLabelList);

}
