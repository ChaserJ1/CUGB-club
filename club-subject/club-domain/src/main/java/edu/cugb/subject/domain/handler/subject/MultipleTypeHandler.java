package edu.cugb.subject.domain.handler.subject;

import com.google.common.base.Preconditions;
import edu.cugb.subject.common.enums.IsDeletedFlagEnum;
import edu.cugb.subject.common.enums.SubjectInfoTypeEnum;
import edu.cugb.subject.domain.convert.SubjectTypeConverter;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.infra.basic.entity.SubjectMultiple;
import edu.cugb.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/25 19:45
 * @Description: 多选题目的策略类
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler {
    @Resource
    SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //多选题插入
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoBO.getOptionList()),
                "选项不能为空");
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = SubjectTypeConverter.INSTANCE.convertAnswerBOToMultiple(option);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
            subjectMultipleList.add(subjectMultiple);
        });
        subjectMultipleService.batchInsert(subjectMultipleList);
    }
}
