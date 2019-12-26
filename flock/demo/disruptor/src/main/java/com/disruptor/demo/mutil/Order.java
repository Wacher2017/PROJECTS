package com.disruptor.demo.mutil;

import lombok.Data;

/**
 * Disruptor 中的 Event
 * @author wangchunming
 * @version 1.0
 * @date 2019-05-25 22:26
 */
@Data
public class Order {

    private String id;

    private String name;

    private double price;

}
