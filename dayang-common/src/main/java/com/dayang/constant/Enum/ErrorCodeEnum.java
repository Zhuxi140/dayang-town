package com.dayang.constant.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 错误码枚举类
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {
    // ------------ 10xxx 系统/全局级错误码 ------------
    SUCCESS(10000, "成功"),
    SYSTEM_ERROR(500, "系统错误,请稍后重试或联系客服"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    TOO_MANY_REQUESTS(429, "请求过于频繁"),
    PARAM_VALID_ERROR(10001, "参数校验失败"),
    INTERFACE_NOT_FOUND(404, "接口不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    REQUEST_BODY_OVERFLOW(413, "请求实体过大"),
    NOT_SUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型"),
    REQUEST_TIMEOUT(408, "请求超时"),
    DATA_TYPE_ERROR(10002, "数据类型错误"),
    OBJECT_TO_JSON_ERROR(10003, "JSON序列化错误"),
    JSON_TO_OBJECT_ERROR(10004, "JSON反序列化错误"),

    // ------------ 20xxx 用户认证与授权错误码 ------------
    USER_NOT_LOGIN(401, "用户未登录"),
    TOKEN_EXPIRED(401, "Token已过期"),
    TOKEN_INVALID(401, "Token无效"),
    PERMISSION_DENIED(403, "权限不足"),
    CODE_ERROR(20001, "验证码错误"),
    CODE_EXPIRED(20002, "验证码已过期"),
    PHONE_ALREADY_EXISTS(20003, "手机号已注册"),
    WECHAT_GET_PERMISSION_ERROR(20004, "微信授权失败"),
    PASSWORD_STRENGTH_ERROR(20005, "密码强度不够"),
    USER_ALREADY_FREEZE(403, "用户已冻结"),
    RISK_CONTROL(403, "你的账户当前存在异常，请等待稍后重试或联系客服"),

    // ------------ 30xxx 民宿预订错误码 ------------
    HOMESTAY_NOT_EXIST(30001, "民宿不存在或已下架"),
    HOMESTAY_STOCK_NOT_ENOUGH(30002, "民宿库存不足"),
    CHECK_IN_TIME_ERROR(30003, "入住时间不合法"),
    OVER_CHECK_IN_DAYS(30004, "入住天数超出限制"),
    BOOK_PEOPLE_OVER_LIMIT(30005, "入住人数超出限制"),
    BOOK_TIME_CONFLICT(30006, "预订时间冲突"),
    NO_REFUND_RULE_RESTRICTIONS(30007, "不符合退款规则"),
    BOOT_PERSON_INFO_NO_INTACT(30008, "入住人数信息不完整"),
    NO_SUPPORT_CHILD_OR_BABY(30009, "不支持儿童或婴儿入住"),
    PRICE_ALREADY_UPDATED(30010, "价格已更新,请重新获取价格"),

    // ------------ 40xxx 特产电商错误码 ------------
    PRODUCT_NOT_EXIST(40001, "商品不存在或已下架"),
    PRODUCT_STOCK_NOT_ENOUGH(40002, "商品库存不足"),
    PRODUCT_SPEC_NOT_EXIST(40003, "商品规格不存在"),
    MIN_PURCHASE_LIMIT(40004, "起售数量限制"),
    PURCHASE_LIMIT_EXCEEDED(40005, "限购数量超限"),
    DELIVERY_AREA_NOT_SUPPORTED(40006, "配送区域不支持"),
    PICKUP_POINT_CLOSED(40007, "自提点已关闭"),
    PRODUCT_WEIGHT_VOLUME_EXCEEDED(40008, "商品重量/体积超限"),
    PRE_SALE_PRODUCT_NOT_DELIVERABLE(40009, "预售商品未到发货期"),
    FRESH_PRODUCT_DELIVERY_TIME_LIMIT(40010, "生鲜商品配送时间限制"),

    // ------------ 50xxx 茶室预订错误码 ------------
    TEA_HOUSE_TIME_SLOT_BOOKED(50001, "茶室时段已被预订"),
    MIN_ADVANCE_BOOKING_TIME(50002, "最少提前预订时间"),
    MIN_CONSUMPTION_PEOPLE_NOT_MET(50003, "最少消费人数不足"),
    MAX_CONSUMPTION_PEOPLE_EXCEEDED(50004, "最多消费人数超限"),
    UNAVAILABLE_BOOKING_TIME(50005, "不可预订时间段"),
    PRIVATE_ROOM_MIN_CONSUMPTION_NOT_MET(50006, "包间最低消费未达标");


    private final int code;
    private final String msg;
}
