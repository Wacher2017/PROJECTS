package com.flock.event.rabbit;

public interface RabbitmqConsumer<T> {

    /**
     * 处理收到的消息，注意完成处理后必须要进行应答，否则不会收到新的消息
     * 处理方法中抛出异常时，会自动进行拒绝应答，且消息会被丢弃
     *
     * @param message 消息
     */
    void onMessage(RabbitmqMessage<T> message);

}
