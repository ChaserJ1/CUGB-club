package edu.cugb.auth.entity;

import lombok.Getter;

/**
 * @Author pengjia
 * @Data 2024/10/17 16:18
 * @Description:
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(500, "失败");

    public int code;

    public String desc;

    ResultCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResultCodeEnum getByCode(int codeval) {
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if (resultCodeEnum.code == codeval) {
                return resultCodeEnum;
            }
        }
        return null;
    }

}
