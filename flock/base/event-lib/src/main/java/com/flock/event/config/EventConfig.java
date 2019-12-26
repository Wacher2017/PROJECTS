package com.flock.event.config;

import com.flock.event.external.Producer;
import com.flock.event.internal.Publisher;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息中间件配置
 * Created by Chunming_Wang on 2019/12/20.
 */
@Configuration
public class EventConfig {

    @Value("${mq.host:localhost}")
    private String host;

    @Value("${mq.port:5672}")
    private Integer port;

    @Value("${mq.username:admin}")
    private String username;

    @Value("${mq.password:admin}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public Producer producer(ConnectionFactory connectionFactory) {
        return new Producer(connectionFactory);
    }

    @Bean
    Publisher publisher(){
        return new Publisher();
    }

}
