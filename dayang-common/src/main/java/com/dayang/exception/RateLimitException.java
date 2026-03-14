package com.dayang.exception;

import com.dayang.constant.ErrorCodeEnum;
import lombok.Getter;

/**
 * @author zhuxi
 * @apiNote 频率限制异常类(429)
 */
@Getter
public class RateLimitException extends RuntimeException{
    private final Integer code;
    private final String msg;

    /**
     * 通过枚举创建异常
     * @param errorCodeEnum 错误码枚举
     */
    public RateLimitException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }

    /**
     * 通过自定义消息创建429异常
     * @param msg 自定义消息
     */
    public RateLimitException(String msg){
        super(msg);
        // 使用默认的错误码(429)
        this.code = ErrorCodeEnum.TOO_MANY_REQUESTS.getCode();
        this.msg = msg;
    }
}
