package com.disruptor.demo.quickstart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-25 21:20
 */
public class Main {

    public static void main(String[] args) {

        // 参数准备工作

        OrderEventFactory orderEventFactory = new OrderEventFactory();

        int ringBufferSize = 1024 * 1024;

        ExecutorService excutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        /**
         * 1.消息（event）工程对象
         * 2.ringBufferSize：容器长度
         * 3.excutor：线程池（生成需要使用自定义线程池）
         * 4.ProducerType：单生产者 还是 多生产者
         * 5.waitStrategy：等待策略
         */
        // 1.实例化 disruptor 对象
        Disruptor<OrderEvent> disruptor = new Disruptor<>(
                orderEventFactory,
                ringBufferSize,
                excutor,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

        // 2.添加消费者的监听（构建 disruptor 与消费者的一个关联关系）
        disruptor.handleEventsWith(new OrderEventHandler());

        // 3.启动 disruptor
        disruptor.start();

        // 4.获取实际存储数据的容器：RingBuffer
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();

        OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        for (long i = 0; i < 100; i++) {
            bb.putLong(0, i);
            producer.sendData(bb);
        }

        disruptor.shutdown();
        excutor.shutdown();

    }

}
