package com.dayang.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.dayang.util.TraceUtil;

/**
 * @author zhuxi
 * @apiNote 封装结果类
 */
@AllArgsConstructor
@Getter
@Setter
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;
    private Long timestamp;
    private String traceId;

    /**
     * 通用成功(无数据)
     * @return 成功结果封装对象
     */
    public static <T> Result<T> success(){
        return success( null);
    }

    /**
     * 通用成功(有数据)
     * @param data 返回数据
     * @return 成功结果封装对象
     */
    public static <T> Result<T> success(T data){
        Long timestamp = System.currentTimeMillis();
        return new Result<>(0, "success", data,timestamp, TraceUtil.getTraceId());
    }

    /**
     * 通用错误
     * @param code 业务错误码
     * @param msg 错误信息
     * @return 错误结果封装对象
     */
    public static <T> Result<T> error(Integer code, String msg){
        Long timestamp = System.currentTimeMillis();
        return new Result<>(code, msg, null,timestamp, TraceUtil.getTraceId());
    }


}
