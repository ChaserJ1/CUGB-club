package edu.cugb.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import edu.cugb.subject.application.convert.SubjectCategoryDTOConverter;
import edu.cugb.subject.application.convert.SubjectLabelDTOConverter;
import edu.cugb.subject.application.dto.SubjectCategoryDTO;
import edu.cugb.subject.application.dto.SubjectLabelDTO;
import edu.cugb.subject.common.entity.Result;
import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import edu.cugb.subject.domain.entity.SubjectLabelBO;
import edu.cugb.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/24 13:18
 * @Description: 标签模块
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    /**
     * 新增标签
     *
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            //校验
            Preconditions.checkArgument(!StringUtils.isBlank(subjectLabelDTO.getLabelName()), "标签名称不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOToBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增标签失败");
        }
    }

    /**
     * 更新标签
     *
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.update.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOToBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(result);

        } catch (Exception e) {
            log.error("SubjectLabelController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新标签失败");
        }
    }

    /**
     * 删除标签
     *
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.delete.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOToBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.delete(subjectLabelBO);
            return Result.ok(result);

        } catch (Exception e) {
            log.error("SubjectLabelController.update.error:{}", e.getMessage(), e);
            return Result.fail("删除标签失败");
        }
    }

    /**
     * 查询分类下标签
     *
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/queryByCategoryId")
    public Result<List<SubjectLabelDTO>> queryByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.
                    isInfoEnabled()) {
                log.info("SubjectLabelController.queryByCategoryId.dto:{}",
                        JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "分类id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOToBO(subjectLabelDTO);
            List<SubjectLabelBO> boList = subjectLabelDomainService.queryByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> subjectCategoryDTOList = SubjectLabelDTOConverter.INSTANCE.convertBOLIstToDTOList(boList);
            return Result.ok(subjectCategoryDTOList);

        } catch (Exception e) {
            log.error("SubjectLabelController.queryByCategoryId.error:{}", e.getMessage(), e);
            return Result.fail("查询分类下失败");
        }
    }

}
