package com.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置类
 *
 * @author wangxl
 * @date 2021/7/17 9:16
 */
@Configuration
@EnableSwagger2
public class Knife4jConfiguration {

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2)
                // api 信息
                .apiInfo(getApiInfo())
                // 分组名称
                .groupName("2.X")
                .select()
                // 这里指定 controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.learn.controller.cs"))
                // 扫描所有 controller
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                // api 信息
                .apiInfo(getApiInfo())
                // 分组名称
                .groupName("1.X")
                .select()
                // 这里指定 controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.learn.controller"))
                // 扫描所有 controller
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api 信息
     *
     * @return ApiInfo
     */
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                // 描述
                .description("项目API文档")
                // 服务条款
                .termsOfServiceUrl("")
                // 联系方式
                .contact(getContact())
                // 版本
                .version("1.0")
                .build();
    }

    /**
     * 联系方式
     *
     * @return Contact
     */
    private Contact getContact() {
        return new Contact("王晓磊", "", "sdwxl1995@163.com");
    }
}
