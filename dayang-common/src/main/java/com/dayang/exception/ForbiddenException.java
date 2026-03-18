package com.dayang.exception;

import com.dayang.constant.Enum.ErrorCodeEnum;
import lombok.Getter;
import static com.dayang.constant.Enum.ErrorCodeEnum.PERMISSION_DENIED;

/**
 * @author zhuxi
 * @apiNote 403异常
 */
@Getter
public class ForbiddenException extends RuntimeException {
    private final Integer code;
    private final String msg;

    /**
     * 通过自定义提示消息创建403异常
     * @param msg 自定义提示消息
     */
    public ForbiddenException(String msg) {
        super(msg);
        // 固定403业务错误码
        this.code = PERMISSION_DENIED.getCode();
        this.msg = msg;
    }

    /**
     * 通过枚举类创建403异常
     * @param errorCodeEnum 错误码枚举
     */
    public ForbiddenException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }
}
