
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
public class SubjectInfoBO extends PageInfo implements Serializable {
    private static final long serialVersionUID = -71684389794293822L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 分类id列表
     */
    private List<Integer> categoryIds;
    /**
     * 标签id列表
     */
    private List<Integer> labelIds;
    /**
     * 标签id名称列表
     */
    private List<String> labelNameList;
    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 标签id
     */
    private Long labelId;


}

