package com.learn.interceptor;

import cn.hutool.core.util.StrUtil;
import com.learn.config.TokenConfiguration;
import com.learn.exception.TokenAuthExpiredException;
import com.learn.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 认证管理拦截器
 *
 * @author wangxl
 * @date 2021/7/24 17:17
 */
@Slf4j
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private TokenConfiguration tokenConfiguration;

    private static final String TOKEN = "token";

    /**
     * 权限认证的拦截操作.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        log.info("======= 进入拦截器 ========");

        //为空就返回错误
        String token = request.getHeader(TOKEN);
        if (StrUtil.isBlank(token)) {
            return false;
        }
        log.info("============== token:" + token);
        Map<String, String> map = tokenUtil.parseToken(token);
        String userId = map.get("userId");
        String userRole = map.get("userRole");

        long timeOfUse = System.currentTimeMillis() - Long.parseLong(map.get("timeStamp"));

        // token 过期，则抛出异常
        if (timeOfUse > tokenConfiguration.getOldToken()) {
            throw new TokenAuthExpiredException();
        }

        // token 时间比较长了，就刷新 token
        if (timeOfUse >= tokenConfiguration.getYangToken()) {
            response.setHeader(TOKEN, tokenUtil.getToken(userId, userRole));
        }

        return "user".equals(userRole) || "admin".equals(userRole);
    }

}
