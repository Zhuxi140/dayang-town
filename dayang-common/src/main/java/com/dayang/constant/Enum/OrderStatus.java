package com.dayang.constant.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 订单状态枚举类
 */
@Getter
@AllArgsConstructor
public enum OrderStatus implements BaseEnum {

    PENDING_PAY(1,"待支付"),
    PAID_WAIT_CONFIRM(2,"已支付/待确认"),
    WAITING_ACTION(3,"待核销/待发货"),
    PROCESSING(4,"入住中/消费中/已发货"),
    COMPLETED(5,"已完成/已签收"),
    CLOSED(6,"已关闭/超时未付");

    private final int code;
    private final String msg;

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
