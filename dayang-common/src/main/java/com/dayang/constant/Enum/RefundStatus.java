package com.dayang.constant.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 退款售后状态枚举类
 */

@AllArgsConstructor
@Getter
public enum RefundStatus implements BaseEnum {

    NO_REFUND(0,"无售后/正常状态"),
    REFUNDING(1,"售后中"),
    PARTIAL_REFUNDED(2,"部分退款成功"),
    FULL_REFUNDED(3,"全额退款成功"),
    REFUND_REJECTED(4,"商家拒绝退款");

    private final int code;
    private final String msg;

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
