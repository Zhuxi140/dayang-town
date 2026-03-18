package com.dayang.constant.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 风险等级枚举类
 */

@Getter
@AllArgsConstructor
public enum RiskLevel implements BaseEnum {
    LOW(0,"安全"),
    MIDDLE(1,"低风险"),
    HIGH(2,"高风险");

    private final int code;
    private final String value;

    /**
     * 根据code获取枚举值
     * @param code code
     * @return 枚举值
     */
    public static RiskLevel fromCode(int code) {
        for (RiskLevel value : values()) {
            if (code == value.code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid RiskLevel code: " + code);
    }
}
