package com.dayang.constant.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 店铺类型枚举
 */

@AllArgsConstructor
@Getter
public enum ShopType implements BaseEnum {

    SELF_OPERATED(1, "自营店"),
    SUPPLIER(2, "供应商"),
    DISTRIBUTION_STORE(3, "分销店");

    private final int code;
    private final String msg;

    /**
     * 根据code获取枚举
     * @param code  code
     * @return 枚举
     */
    public static ShopType fromCode(int code) {
        for (ShopType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid ShopType code: " + code);
    }
}
