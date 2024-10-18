package edu.cugb.subject.application.controller;

import edu.cugb.subject.infra.basic.entity.SubjectCategory;
import edu.cugb.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author pengjia
 * @Data 2024/10/16 9:22
 * @Description:
 */
@RestController
public class SubjectController {
    @Resource
    private SubjectCategoryService subjectCategoryService;

    @GetMapping(("/test"))
    public String test() {
        SubjectCategory subjectCategory = subjectCategoryService.queryById(1L);
        return subjectCategory.getCategoryName();
    }
}
