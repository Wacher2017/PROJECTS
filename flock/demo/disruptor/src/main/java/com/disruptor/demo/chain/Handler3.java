package com.disruptor.demo.chain;

import com.lmax.disruptor.EventHandler;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-25 22:50
 */
public class Handler3 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handler 3 : NAME: "
                + event.getName()
                + ", ID: "
                + event.getId()
                + ", INSTANCE: "
                + event.toString());
    }

}
