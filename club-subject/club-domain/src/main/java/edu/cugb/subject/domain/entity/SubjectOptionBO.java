
package edu.cugb.subject.domain.entity;

import edu.cugb.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目DTO
 *
 * @author makejava
 * @since 2024-10-25 15:42:11
 */
@Data
public class SubjectOptionBO implements Serializable {
    private static final long serialVersionUID = -71684389794293822L;


    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;


}

