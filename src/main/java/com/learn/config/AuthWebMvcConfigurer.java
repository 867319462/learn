package com.learn.config;

import com.learn.interceptor.AuthHandlerInterceptor;
import com.learn.interceptor.LoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 认证配置
 *
 * @author wangxl
 * @date 2021/7/24 17:14
 */
@Configuration
public class AuthWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AuthHandlerInterceptor authHandlerInterceptor;

    @Autowired
    private LoggerInterceptor loggerInterceptor;

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor);
        registry.addInterceptor(authHandlerInterceptor)
                // 拦截路径 /jwt/**
                .addPathPatterns("/jwt/**")
                // 排除拦截的路径 /jwt/login
                .excludePathPatterns("/jwt/login");
    }
}