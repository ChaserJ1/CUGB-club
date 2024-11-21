package edu.cugb.subject.domain.handler.subject;

import com.google.common.base.Preconditions;
import edu.cugb.subject.common.enums.IsDeletedFlagEnum;
import edu.cugb.subject.common.enums.SubjectInfoTypeEnum;
import edu.cugb.subject.domain.convert.SubjectTypeConverter;
import edu.cugb.subject.domain.entity.SubjectAnswerBO;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.domain.entity.SubjectOptionBO;
import edu.cugb.subject.infra.basic.entity.SubjectRadio;
import edu.cugb.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/25 19:45
 * @Description: 单选题目的策略类
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler {

    @Resource
    SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }


    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //单选题插入
        List<SubjectRadio> subjectRadioList = new LinkedList<>();
        Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoBO.getOptionList()),
                "选项不能为空");
        //插入选项信息
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectRadio subjectRadio = SubjectTypeConverter.INSTANCE.convertAnswerBOToRadio(option);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
            subjectRadioList.add(subjectRadio);
        });
        subjectRadioService.batchInsert(subjectRadioList);


    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId((long) subjectId);
        List<SubjectRadio> result = subjectRadioService.queryByCondition(subjectRadio);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectTypeConverter.INSTANCE
                .convertRadioListToAnswerBO(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
