package com.dayang.web;

import com.dayang.api.Result;
import com.dayang.constant.ErrorCodeEnum;
import com.dayang.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * @author zhuxi
 * @apiNote 全局异常处理器
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    public Result<String> handleBizException(BizException e){
        log.info("业务异常被拦截：code={}, msg={}", e.getCode(),e.getMessage());
        return Result.error(e.getCode(), e.getMsg());
    }

    /**
     * 403异常
     */
    @ExceptionHandler(ForbiddenException.class)
    public Result<String> handleForbiddenException(ForbiddenException e){
        log.warn("禁止访问_403：msg={}",e.getMessage());
        return Result.error(e.getCode(), e.getMsg());
    }

    /**
     * 429异常
     */
    @ExceptionHandler(RateLimitException.class)
    public Result<String> handleRateLimitException(RateLimitException e){
        log.warn("请求过多_429：msg={}",e.getMessage());
        return Result.error(e.getCode(), e.getMsg());
    }

    /**
     * 401异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Result<String> handleNotLoginException(NotLoginException e){
        log.info("未经授权_401：msg={}",e.getMessage());
        return Result.error(e.getCode(), e.getMsg());
    }


    @ExceptionHandler({
            NoResourceFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MaxUploadSizeExceededException.class
    }
    )
    public Result<String> handleFrameworkException(Exception e){
        if (e instanceof HttpRequestMethodNotSupportedException){
            log.warn("不支持的请求方法被拦截: {}",e.getMessage());
            return Result.error(ErrorCodeEnum.METHOD_NOT_ALLOWED.getCode(), ErrorCodeEnum.METHOD_NOT_ALLOWED.getMsg());
        }else if (e instanceof HttpMediaTypeNotSupportedException){
            log.warn("不支持的媒体类型被拦截: {}",e.getMessage());
            return Result.error(ErrorCodeEnum.NOT_SUPPORTED_MEDIA_TYPE.getCode(), ErrorCodeEnum.NOT_SUPPORTED_MEDIA_TYPE.getMsg());
        }else if(e instanceof MaxUploadSizeExceededException){
            log.warn("请求实体过大_413: {}",e.getMessage());
            return Result.error(ErrorCodeEnum.REQUEST_BODY_OVERFLOW.getCode(), ErrorCodeEnum.REQUEST_BODY_OVERFLOW.getMsg());
        }

        log.warn("接口不存在_404: {}",e.getMessage());
        return Result.error(ErrorCodeEnum.INTERFACE_NOT_FOUND.getCode(), ErrorCodeEnum.INTERFACE_NOT_FOUND.getMsg());
    }

    /**
     * 系统未知异常（500）
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e){
        log.error("系统内部发生未知异常", e);
        return Result.error(ErrorCodeEnum.SYSTEM_ERROR.getCode(), ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(SystemException.class)
    public Result<String> handleSysException(SystemException e){
        log.error("系统内部发生异常 ", e);
        return Result.error(e.getCode(), ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }



}
