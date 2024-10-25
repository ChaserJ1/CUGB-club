package edu.cugb.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签dto
 *
 * @author makejava
 * @since 2024-10-24 12:52:42
 */
@Data
public class SubjectLabelDTO implements Serializable {
    private static final long serialVersionUID = 147840583441771786L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;


    private Integer isDeleted;


}

