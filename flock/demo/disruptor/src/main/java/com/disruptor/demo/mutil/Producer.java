package com.disruptor.demo.mutil;

import com.lmax.disruptor.RingBuffer;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-26 15:09
 */
public class Producer {

    private RingBuffer<Order> ringBuffer;

    public Producer(RingBuffer<Order> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(String uuid) {
        long sequence = ringBuffer.next();
        try {
            Order order = ringBuffer.get(sequence);
            order.setId(uuid);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

}
