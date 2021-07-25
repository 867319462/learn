package com.learn.interceptor;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 日志拦截器
 *
 * @author wangxl
 * @date 2021/7/24 23:03
 */
@Slf4j
@Component
public class LoggerInterceptor implements HandlerInterceptor {
    /**
     * 线程 ID 常量
     */
    private static final String THREAD_ID = "THREAD_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("preHandle running ...");
        //使用UUID生成唯一编号
        String threadId = UUID.randomUUID().toString().replaceAll("-", "");
        //判断MDC(log4j中的上下文对象) 中是否有该threadId
        if (StrUtil.isBlank(MDC.get(THREAD_ID))) {
            //如果没有，添加
            MDC.put(THREAD_ID, threadId);
        }
        //永远返回true
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.remove(THREAD_ID);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
