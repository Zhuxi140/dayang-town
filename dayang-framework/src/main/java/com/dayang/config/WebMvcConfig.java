package com.dayang.config;

import com.dayang.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhuxi
 * @apiNote WebMvc配置类
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        //TODO: 拦截器拦截路径 待补充
        registry.addInterceptor(new AuthInterceptor())
                .excludePathPatterns();
    }
}
