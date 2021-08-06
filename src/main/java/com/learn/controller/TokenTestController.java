package com.learn.controller;

import com.learn.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = {"token API 文档"})
public class TokenTestController {
    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 使用 /login 请求获得 token, /login 不经过拦截器
     */
    @GetMapping("/login")
    @ApiOperation("登录")
    public String login(HttpServletResponse response) {
        response.addHeader("token", tokenUtil.getToken("wxl", "admin"));
        return "成功";
    }

    /**
     * 使用 /test-token 测试 token，进过拦截器
     */
    @GetMapping("/test-token")
    @ApiOperation("token 测试")
    public Map testToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return tokenUtil.parseToken(token);
    }

}
