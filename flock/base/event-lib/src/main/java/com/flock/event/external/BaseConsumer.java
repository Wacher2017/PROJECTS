package com.flock.event.external;

import com.flock.event.rabbit.RabbitmqConsumer;
import com.flock.event.rabbit.RabbitmqMessage;

/**
 * 消费者
 * Created by Chunming_Wang on 2019/12/20.
 */
public class BaseConsumer<T> implements RabbitmqConsumer<T> {

    @Override
    public void onMessage(RabbitmqMessage<T> message) {

    }
}
