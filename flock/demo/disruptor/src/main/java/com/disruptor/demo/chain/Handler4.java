package com.disruptor.demo.chain;

import com.lmax.disruptor.EventHandler;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-25 22:50
 */
public class Handler4 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handler 4 : SET PRICE");
        event.setPrice(17.0);
    }

}
