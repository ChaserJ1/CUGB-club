package edu.cugb.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目答案DTO
 *
 * @author makejava
 * @since 2024-10-25 15:42:11
 */
@Data
public class SubjectAnswerDTO implements Serializable {
    private static final long serialVersionUID = -71684389794293822L;

    /**
     * 答案选项标识
     */
    private Long optionType;

    /**
     * 选项内容
     */
    private String optionContent;

    /**
     * 是否正确
     */
    private int isCorrect;


}

