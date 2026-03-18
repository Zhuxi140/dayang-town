package com.dayang.exception;

import com.dayang.constant.Enum.ErrorCodeEnum;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 未登录异常（401）
 */
@Getter
public class NotLoginException extends RuntimeException {
    private final Integer code;
    private final String msg;

    /**
     * 通过枚举创建异常
     * @param errorCodeEnum 错误码枚举
     */
    public NotLoginException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }

    /**
     * 通过自定义消息创建429异常
     * @param msg 自定义消息
     */
    public NotLoginException(String msg){
        super(msg);
        // 使用默认的错误码(429)
        this.code = ErrorCodeEnum.USER_NOT_LOGIN.getCode();
        this.msg = msg;
    }
}
