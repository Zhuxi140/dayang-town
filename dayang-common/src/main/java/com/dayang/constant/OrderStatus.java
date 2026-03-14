package com.dayang.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 订单状态枚举类
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {

    PENDING_PAY(1),
    PAID_WAIT_CONFIRM(2),
    WAITING_ACTION(3),
    PROCESSING(4),
    COMPLETED(5),
    CLOSED(6);

    private final int code;

    /**
     * 通过code获取枚举
     * @param code 订单状态码
     * @return 订单状态枚举
     */
    public static OrderStatus getByCode(int code) {
        for (OrderStatus value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("UnKnow order_status_code" + code);
    }

}
