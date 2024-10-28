package edu.cugb.subject.common.enums;

import lombok.Getter;

/**
 * @Author pengjia
 * @Data 2024/10/23 17:20
 * @Description: 题目类型枚举
 */
@Getter
public enum SubjectInfoTypeEnum {

    RADIO(1, "单选"),
    MULTIPLE(2, "多选"),
    JUDGE(3, "判断"),
    BRIEF(4, "简答");

    public int code;

    public String desc;


    SubjectInfoTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SubjectInfoTypeEnum getByCode(int codeval) {
        for (SubjectInfoTypeEnum subjectInfoTypeEnum : SubjectInfoTypeEnum.values()) {
            if (subjectInfoTypeEnum.code == codeval) {
                return subjectInfoTypeEnum;
            }
        }
        return null;
    }
}
