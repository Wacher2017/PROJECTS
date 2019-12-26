package com.flock.event.rabbit;

import java.io.IOException;

public interface RabbitmqMessage<T> {

    /**
     * 获取信息对象，队列无信息时返回null
     */
    T getMessage();

    /**
     * 确认应答
     *
     * @throws IOException 通讯异常
     */
    void ack() throws IOException;

    /**
     * 拒绝应答
     *
     * @param reQueue 消息是否重新放回队列里进行重发（请注意防止无限重发）
     * @throws IOException 通讯异常
     */
    void reject(boolean reQueue) throws IOException;
}
