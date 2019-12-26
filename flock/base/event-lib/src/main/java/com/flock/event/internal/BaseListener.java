package com.flock.event.internal;

import com.flock.event.entity.InternalEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;

/**
 * 事件监听器
 * Created by Chunming_Wang on 2019/12/20.
 */
@Slf4j
public class BaseListener<E extends InternalEvent> implements ApplicationListener<E>, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void onApplicationEvent(E event) {
        log.info("receive event: {}", event);
    }

}
