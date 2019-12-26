package com.flock.event.entity;

import com.flock.event.enums.EventType;
import org.springframework.context.ApplicationEvent;

/**
 * 进程内事件对象定义
 * Created by Chunming_Wang on 2019/12/20.
 */
public class InternalEvent extends ApplicationEvent {

    private EventContext eventContext;

    private EventType eventType;

    private Object data;

    public InternalEvent(Object source) {
        super(source);
    }

    public EventContext getEventContext() {
        return eventContext;
    }

    public void setEventContext(EventContext eventContext) {
        this.eventContext = eventContext;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
