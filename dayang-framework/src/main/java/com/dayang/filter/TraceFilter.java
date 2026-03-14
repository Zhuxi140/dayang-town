package com.dayang.filter;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.dayang.util.IdGenerator;
import java.io.IOException;

/**
 * @author zhuxi
 * @apiNote 链路追踪过滤器
 * <p>
 *     接收请求头中的traceId，如果没有则兜底生成一个，并设置到MDC中
 * </p>
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TraceFilter implements Filter {
    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String traceId = httpRequest.getHeader(TRACE_ID);

        // 兜底生成traceId，避免消息头中的traceId无效
        if (!StrUtil.isNotBlank(traceId)){
            traceId = IdGenerator.generateTraceId();
        }

        MDC.put(TRACE_ID, traceId);

        httpResponse.setHeader(TRACE_ID, traceId);

        try{
            chain.doFilter(request, response);
        }finally {
            // 请求处理完，移除MDC中的traceId 防止线程变量污染
            MDC.remove(TRACE_ID);
        }
    }
}
