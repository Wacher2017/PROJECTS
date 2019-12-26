package com.disruptor.demo.chain;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-25 22:37
 */
public class TradePublisher implements Runnable {

    private CountDownLatch latch;

    private Disruptor<Trade> disruptor;

    private static final int PUBLISH_COUNT = 1;

    public TradePublisher(CountDownLatch latch, Disruptor<Trade> disruptor) {
        this.latch = latch;
        this.disruptor = disruptor;
    }

    @Override
    public void run() {
        TradeEventTranslator eventTranslator = new TradeEventTranslator();
        for (int i = 0; i < PUBLISH_COUNT; i++) {
            //新的提交任务方式
            disruptor.publishEvent(eventTranslator);
        }
        latch.countDown();
    }
}

class TradeEventTranslator implements EventTranslator<Trade> {

    private Random random = new Random();

    @Override
    public void translateTo(Trade event, long sequence) {
        this.generatorTrade(event);
    }

    private void generatorTrade(Trade event) {
        event.setPrice(random.nextDouble() * 9999);
    }
}
