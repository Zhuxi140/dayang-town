package com.dayang.constant.Enum;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 会员等级枚举类
 */

@AllArgsConstructor
@Getter
public enum VipLevel implements BaseEnum {

    NORMAL(1,"普通会员"),
    GOLD(2,"黄金会员"),
    DIAMOND(3,"钻石会员");
    private final int code;
    private final String msg;

    /**
     * 通过code获取枚举
     * @param code  会员等级码
     * @return  会员等级枚举
     */
    public static VipLevel getByCode(int code) {
        for (VipLevel value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("UnKnow vip_level_code" + code);
    }
}
