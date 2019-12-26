package com.disruptor.demo.chain;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-25 22:50
 */
public class Handler2 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handle 2 : SET ID");
        Thread.sleep(2000);
        event.setId(UUID.randomUUID().toString());
    }

}
