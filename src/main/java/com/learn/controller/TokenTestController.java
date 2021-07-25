package com.learn.controller;

import com.learn.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * token 测试
 *
 * @author wangxl
 * @date 2021/7/24 17:20
 */
@RestController
@RequestMapping("/jwt")
public class TokenTestController {
    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 使用 /login 请求获得 token, /login 不经过拦截器
     */
    @RequestMapping("/login")
    public String login(HttpServletResponse response) {
        response.addHeader("token", tokenUtil.getToken("wxl", "admin"));
        return "成功";
    }

    /**
     * 使用 /test-token 测试 token，进过拦截器
     */
    @RequestMapping("/test-token")
    public Map testToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return tokenUtil.parseToken(token);
    }

}
