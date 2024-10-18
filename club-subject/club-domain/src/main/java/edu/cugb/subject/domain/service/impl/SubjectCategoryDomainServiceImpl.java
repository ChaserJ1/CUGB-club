package edu.cugb.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import edu.cugb.subject.domain.convert.SubjectCategoryConverter;
import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import edu.cugb.subject.domain.service.SubjectCategoryDomainService;
import edu.cugb.subject.infra.basic.entity.SubjectCategory;
import edu.cugb.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author pengjia
 * @Data 2024/10/17 14:39
 * @Description:
 */
@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.add.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBOToCategory(subjectCategoryBO);
        subjectCategoryService.insert(subjectCategory);

    }
}
