package edu.cugb.subject.application.controller;

import com.alibaba.fastjson.JSON;
import edu.cugb.subject.application.convert.SubjectCategoryDTOConverter;
import edu.cugb.subject.application.dto.SubjectCategoryDTO;
import edu.cugb.subject.common.entity.Result;
import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import edu.cugb.subject.domain.service.SubjectCategoryDomainService;
import edu.cugb.subject.infra.basic.entity.SubjectCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author pengjia
 * @Data 2024/10/17 15:47
 * @Description: 刷题分类Controller
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {
    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertBOToCategory(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            return Result.fail();
        }


    }
}
