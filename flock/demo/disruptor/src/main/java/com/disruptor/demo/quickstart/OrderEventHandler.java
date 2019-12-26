package com.disruptor.demo.quickstart;

import com.lmax.disruptor.EventHandler;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-25 21:15
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者：" + event.getValue());
    }

}
