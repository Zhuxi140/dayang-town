package com.dayang.exception;

import com.dayang.constant.Enum.ErrorCodeEnum;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 系统异常类
 */
@Getter
public class SystemException extends RuntimeException {
    private final Integer code;
    private final String msg;

    /**
     * 通过枚举创建异常
     * @param errorCodeEnum 错误码枚举
     */
    public SystemException(ErrorCodeEnum errorCodeEnum, Throwable cause) {
        super(errorCodeEnum.getMsg(),cause);
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }

    /**
     * 通过枚举和自定义消息创建异常
     * @param errorCodeEnum 错误码枚举
     * @param msg 自定义消息
     */
    public SystemException(ErrorCodeEnum errorCodeEnum, String msg, Throwable cause){
        super(msg,cause);
        this.code = errorCodeEnum.getCode();
        this.msg = msg;
    }
}
