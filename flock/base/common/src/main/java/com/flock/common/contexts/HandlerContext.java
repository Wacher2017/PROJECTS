package com.flock.common.contexts;

import com.flock.common.anno.HandlerType;
import com.flock.common.enums.ProcessTypeEnum;
import com.flock.common.handler.AbstractHandler;
import com.flock.common.utils.ClassScanerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HandlerContext implements ApplicationContextAware {

    private String handlerPath;

    public HandlerContext(String handlerPath) {
        if(StringUtils.isBlank(handlerPath)) {
            throw new IllegalArgumentException("handler path can not be blank");
        }
        this.handlerPath = handlerPath;
    }

    private final Map<ProcessTypeEnum, Class<?>> handlerMap = new ConcurrentHashMap<>();

    private ApplicationContext context;

    public AbstractHandler getInstance(ProcessTypeEnum type) {
        if(handlerMap.isEmpty()){
            init();
        }
        Class<?> clazz = handlerMap.get(type);
        if (clazz == null) {
            //throw new IllegalArgumentException("not found handler for type: " + type);
            return null;
        }
        return (AbstractHandler)context.getBean(clazz);
    }

    private void init(){
        ClassScanerUtil.scan(handlerPath, HandlerType.class).forEach(clazz -> {
            // 获取注解中的类型值
            ProcessTypeEnum type = clazz.getAnnotation(HandlerType.class).value();
            // 将注解中的类型值作为key，对应的类作为value，保存在Map中
            handlerMap.put(type, clazz);
        });
    }

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
