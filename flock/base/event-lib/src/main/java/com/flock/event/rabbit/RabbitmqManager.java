package com.flock.event.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface RabbitmqManager {

    /**
     * 发送消息
     *
     * @param queueName 队列名称
     * @param messages  消息集合（要求消息必须可json化）
     * @throws IOException      通讯异常
     * @throws TimeoutException 超时异常
     */
    void sendMessage(String queueName, Object... messages) throws IOException, TimeoutException;


    /**
     * 广播消息，每个订阅的进程都能收到
     */
    void sendBroadcastMessage(Object... messages) throws IOException, TimeoutException;

    /**
     * 发送带主体的信息，只有注册了的进程才会监听到
     */
    void sendTopicMessage(String exchange, String topic, boolean durable, Object... messages) throws IOException, TimeoutException;


    /**
     * 删除队列（同时丢弃队列中的数据）
     *
     * @param queueName 队列名称
     * @throws IOException      通讯异常
     * @throws TimeoutException 超时异常
     */
    void dropQueue(String queueName) throws IOException, TimeoutException;

    /**
     * 添加消息消费者
     *
     * @param queueName    队列名称
     * @param messageClazz 消息类型，如果类型转化失败，该消息会被丢弃处理
     * @param consumer     消息处理回调
     * @param <T>          消息类型
     * @throws IOException      通讯异常
     * @throws TimeoutException 超时异常
     */
    <T> void addConsumer(String queueName, Class<T> messageClazz, RabbitmqConsumer<T> consumer, boolean autoAck) throws IOException, TimeoutException;

    <T> void addConsumer(String queueName, String exchange, boolean durable, Class<T> messageClazz, RabbitmqConsumer<T> consumer, boolean autoAck) throws IOException, TimeoutException;

    <T> void addConsumer(String queueName, String exchange, boolean durable, String topic, Class<T> messageClazz, RabbitmqConsumer<T> consumer, boolean autoAck) throws IOException, TimeoutException;

    /**
     * 主动获取队列中的一条消息
     *
     * @param queueName    队列名称
     * @param messageClazz 消息类型，如果类型转化失败，该消息会被丢弃处理
     * @param autoAck      是否自动确认
     * @param <T>          消息类型
     * @throws IOException      通讯异常
     * @throws TimeoutException 超时异常
     */
    <T> RabbitmqMessage<T> getMessage(String queueName, Class<T> messageClazz, boolean autoAck) throws IOException, TimeoutException;

}
