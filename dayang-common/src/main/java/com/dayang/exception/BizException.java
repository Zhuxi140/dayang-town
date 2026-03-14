package com.dayang.exception;

import com.dayang.constant.ErrorCodeEnum;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 业务异常类
 * <p>
 *     通用业务异常，封装错误码和错误信息
 * </p>
 */

@Getter
public class BizException extends RuntimeException {
    private final Integer code;
    private final String msg;

    /**
     * 通过枚举创建异常
     * @param errorCodeEnum 错误码枚举
     */
    public BizException(ErrorCodeEnum  errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }

    /**
     * 通过枚举和自定义消息创建异常
     * @param errorCodeEnum 错误码枚举
     * @param msg 自定义消息
     */
    public BizException(ErrorCodeEnum errorCodeEnum, String msg){
        super(msg);
        this.code = errorCodeEnum.getCode();
        this.msg = msg;
    }
}
