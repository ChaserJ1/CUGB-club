package edu.cugb.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author makejava
 * @since 2024-10-16 11:04:20
 */
@Data
public class SubjectCategoryBO implements Serializable {
    private static final long serialVersionUID = -50489605395169067L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类类型
     */
    private Integer categoryType;
    /**
     * 图标连接
     */
    private String imageUrl;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 是否删除 0: 未删除 1: 已删除
     */
    private Integer isDeleted;


}

