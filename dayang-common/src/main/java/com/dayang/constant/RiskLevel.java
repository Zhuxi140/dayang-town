package com.dayang.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 风险等级枚举类
 */

@Getter
@AllArgsConstructor
public enum RiskLevel {
    LOW(0,"安全"),
    MIDDLE(1,"低风险"),
    HIGH(2,"高风险");

    private final Integer code;
    private final String value;
}
