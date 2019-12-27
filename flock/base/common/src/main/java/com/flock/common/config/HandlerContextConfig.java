package com.flock.common.config;

import com.flock.common.contexts.HandlerContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HandlerContext全局配置
 * Created by Chunming_Wang on 2019/12/26.
 */
@Configuration
@ConditionalOnProperty(name = "flock.handler.enable", havingValue = "true")
public class HandlerContextConfig {

    /**
     * handler 路径
     */
    @Value("${flock.handler.classpath:}")
    private String hanlderPath;

    @Bean
    HandlerContext handlerContext() {
        return new HandlerContext(hanlderPath);
    }
}
