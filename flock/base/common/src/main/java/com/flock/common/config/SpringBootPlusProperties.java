package com.flock.common.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * spring-boot-plus属性配置信息
 *
 * @author: wangchunming
 * @date: 2019-08-16 14:35
 * @since 1.2.0-RELEASE
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "flock")
public class SpringBootPlusProperties {

    /**
     * 是否启用ansi控制台输出有颜色的字体，local环境建议开启，服务器环境设置为false
     */
    private boolean enableAnsi;

    /**
     * 请求日志在控制台是否格式化输出，local环境建议开启，服务器环境设置为false
     */
    private boolean requestLogFormat;

    /**
     * 响应日志在控制台是否格式化输出，local环境建议开启，服务器环境设置为false
     */
    private boolean responseLogFormat;

}
