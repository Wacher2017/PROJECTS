package com.disruptor.demo.chain;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-25 22:50
 */
public class Handler1 implements EventHandler<Trade>, WorkHandler<Trade> {

    //EventHandler
    @Override
    public void onEvent(Trade evnet, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(evnet);
    }

    //WorkHandler
    @Override
    public void onEvent(Trade event) throws Exception {
        System.out.println("handle 1 : SET NAME");
        Thread.sleep(1000);
        event.setName("H1");
    }
}
