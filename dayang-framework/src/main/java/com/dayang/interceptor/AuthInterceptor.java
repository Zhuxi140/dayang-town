package com.dayang.interceptor;

import com.dayang.context.AuthContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author zhuxi
 * @apiNote 权限认证拦截器
 */

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");

        //TODO: 检查authorization有效性  验证并提取token  将其存入AuthContext  并finally 删除AuthContext

        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler, Exception ex) throws Exception {
        // 删除AuthContext 防止线程污染
        AuthContext.remove();
    }
}
