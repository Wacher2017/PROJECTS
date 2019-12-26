package com.flock.event.internal;

import com.flock.event.entity.InternalEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * 事件发布器
 * Created by Chunming_Wang on 2019/12/20.
 */
@Slf4j
public class Publisher<E extends InternalEvent> implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(E event) {
        log.info("event publishing...");
        publisher.publishEvent(event);
        log.info("event publish successful. Event: {}", event);
    }

}
