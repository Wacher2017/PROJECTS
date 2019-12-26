package com.disruptor.demo.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-25 21:14
 */
public class OrderEventFactory implements EventFactory {

    @Override
    public Object newInstance() {
        return new OrderEvent();
    }

}
