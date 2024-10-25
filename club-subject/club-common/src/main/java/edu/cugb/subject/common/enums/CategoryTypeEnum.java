package edu.cugb.subject.common.enums;

import lombok.Getter;

/**
 * @Author pengjia
 * @Data 2024/10/23 17:20
 * @Description: 分类类型枚举
 */
@Getter
public enum CategoryTypeEnum {

    PRIMARY(1, "岗位大类"),
    SECOND(2, "二级分类");

    public int code;

    public String desc;


    CategoryTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
