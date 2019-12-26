package com.flock.common.config;

import com.flock.common.aop.LogAop;
import com.flock.common.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring-boot-plus配置
 *
 * @author: wangchunming
 * @date: 2019-08-16 14:37
 * @since 1.2.0-RELEASE
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({SpringBootPlusProperties.class})
public class SpringBootPlusConfig {

    /**
     * 配置日志AOP
     * @param springBootPlusProperties
     * @return
     */
    @Bean
    public LogAop logAop(SpringBootPlusProperties springBootPlusProperties){
        LogAop logAop = new LogAop();
        logAop.setRequestLogFormat(springBootPlusProperties.isRequestLogFormat());
        logAop.setResponseLogFormat(springBootPlusProperties.isResponseLogFormat());
        log.info("init LogAop success");
        return logAop;
    }

    /**
     * 配置SpringContextUtil组件
     * @return
     */
    @Bean
    public SpringContextUtil context() {
        return new SpringContextUtil();
    }

}
