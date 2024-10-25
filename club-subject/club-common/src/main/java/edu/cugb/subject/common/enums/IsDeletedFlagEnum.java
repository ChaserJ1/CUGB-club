package edu.cugb.subject.common.enums;

import lombok.Getter;

/**
 * @Author pengjia
 * @Data 2024/10/23 17:20
 * @Description: 删除状态枚举
 */
@Getter
public enum IsDeletedFlagEnum {

    DELETED(1, "已删除"),
    Un_DELETED(0, "未删除");

    public int code;

    public String desc;


    IsDeletedFlagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
