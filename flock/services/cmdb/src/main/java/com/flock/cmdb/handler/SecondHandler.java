package com.flock.cmdb.handler;

import com.flock.common.anno.HandlerType;
import com.flock.common.enums.ProcessTypeEnum;
import com.flock.common.handler.AbstractHandler;
import org.springframework.stereotype.Component;

/**
 * Handler 示例二
 * Created by Chunming_Wang on 2019/12/26.
 */
@Component
@HandlerType(ProcessTypeEnum.HANDLER_TWO)
public class SecondHandler extends AbstractHandler {

    @Override
    public void handler(Object data) {
        System.out.println("execute handler second business");
    }

}
