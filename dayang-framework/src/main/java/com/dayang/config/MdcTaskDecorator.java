package com.dayang.config;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.NonNull;
import com.dayang.util.TraceUtil;

import java.util.Map;

/**
 * @author zhuxi
 * @apiNote Spring异步任务装饰器
 * <p>
 *     将主线程的MDC 传递给 @Async子线程
 * </p>
 */
public class MdcTaskDecorator implements TaskDecorator {
    @Override
    @NonNull
    public Runnable decorate(@NonNull Runnable runnable) {

        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return ()->{
            try{
                // 将主线程的MDC 拷贝给子线程
                if (contextMap != null){
                    MDC.setContextMap(contextMap);
                }else {
                    // 极端情况下，为防止因traceId为空或无效导致 前端判断出错、后续流程追踪丢失等情况
                    // 兜底生成traceId，并写入MDC
                    TraceUtil.getTraceId();
                }
                runnable.run();
            }finally {
                // 移除MDC中的traceId 防止线程变量污染
                TraceUtil.clearTraceId();
            }
        };
    }
}
