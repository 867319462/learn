package com.learn.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Durid 配置类
 *
 * @author wangxl
 * @date 2021/7/17 19:07
 */
@Configuration
public class DuridConfiguration {

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // IP白名单 (没有配置或者为空，则允许所有访问)
        registrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow)
        registrationBean.addInitParameter("deny", "");
        // 用户名
        registrationBean.addInitParameter("loginUsername", "admin");
        // 密码
        registrationBean.addInitParameter("loginPassword", "123456");
        // 是否开启重置
        registrationBean.addInitParameter("resetEnable", "false");
        return registrationBean;
    }

}