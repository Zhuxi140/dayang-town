package com.dayang.constant.Enum;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.MappedTypes;

/**
 * @author zhuxi
 * @apiNote 认证状态枚举类
 */
@Getter
@AllArgsConstructor
public enum AuthStatus implements BaseEnum {

    UNAUTHENTICATED(0, "未认证/待审核"),
    AUTHENTICATED(1, "已认证"),
    REJECTED(2, "已认证");

    private final int code;
    private final String msg;

    /**
     * 通过code获取枚举
     * @param code 认证状态码
     * @return 认证状态枚举
     */
    public static AuthStatus getByCode(int code) {
        for (AuthStatus value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("UnKnow auth_status_code" + code);
    }
}
