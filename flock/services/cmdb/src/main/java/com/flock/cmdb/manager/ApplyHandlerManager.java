package com.flock.cmdb.manager;

import com.flock.common.contexts.HandlerContext;
import com.flock.common.enums.ProcessTypeEnum;
import com.flock.common.handler.AbstractHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handler 管理器
 * Created by Chunming_Wang on 2019/12/26.
 */
@Service
public class ApplyHandlerManager<T> {

    private HandlerContext handlerContext;

    @Autowired
    public ApplyHandlerManager(HandlerContext handlerContext) {
        this.handlerContext = handlerContext;
    }

    @SuppressWarnings("unchecked")
    void execute(ProcessTypeEnum typeEnum, T data) {
        AbstractHandler<T> handler = handlerContext.getInstance(typeEnum);
        if(handler!= null) {
            handler.handler(data);
        }
    }

}
