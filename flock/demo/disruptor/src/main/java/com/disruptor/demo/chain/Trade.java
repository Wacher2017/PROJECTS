package com.disruptor.demo.chain;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Disruptor 中的 Event
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-25 22:26
 */
@Data
public class Trade {

    private String id;

    private String name;

    private double price;

    private AtomicInteger count = new AtomicInteger(0);

}
