package com.disruptor.demo.chain;

import com.lmax.disruptor.EventHandler;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-25 22:50
 */
public class Handler5 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handler 5 : GET PRICE: " + event.getPrice());
        event.setPrice(event.getPrice() + 3.0);
    }

}
