package com.learn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 读取 token 配置
 *
 * @author wangxl
 * @date 2021/7/24 17:37
 */
@Data
@Configuration
@EnableConfigurationProperties(TokenConfiguration.class)
@ConfigurationProperties(prefix = TokenConfiguration.PREFIX, ignoreInvalidFields = true)
public class TokenConfiguration {
    public static final String PREFIX = "token";

    /**
     * 私有 key
     */
    private String privateKey;

    /**
     * 年轻的 token
     */
    private Long yangToken;

    /**
     * 老 token
     */
    private Long oldToken;
}
