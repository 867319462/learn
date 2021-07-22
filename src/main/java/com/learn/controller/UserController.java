package com.learn.controller;


import com.learn.entity.User;
import com.learn.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangxl
 * @since 2021-07-17
 */
@Api(tags = {"用户api文档"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("/list")
    public List<User> getUsers() {
        return userService.list();
    }

    @ApiOperation("你好")
    @GetMapping("/hello")
    public String hello() {
        return "hello world 123";
    }
}
