package com.dayang.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 退款售后状态枚举类
 */

@AllArgsConstructor
@Getter
public enum RefundStatus {

    NO_REFUND(0),
    REFUNDING(1),
    PARTIAL_REFUNDED(2),
    FULL_REFUNDED(3),
    REFUND_REJECTED(4);

    private final int code;

    /**
     * 通过code获取枚举
     * @param code 订单状态码
     * @return 订单状态枚举
     */
    public static RefundStatus getByCode(int code) {
        for (RefundStatus value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("UnKnow refund_status_code" + code);
    }
}
