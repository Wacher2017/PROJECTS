package com.rabbitmq.demo.queue.simple;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.utils.ConnectionUtil;

import java.io.IOException;

/**
 * 简单队列：一个生产者对应一个消费者
 * Created by Chunming_Wang on 2019/12/9.
 */
public class Receive {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception{
        //1、获取连接
        Connection connection = ConnectionUtil.getConnection(
                "127.0.0.1",5672,"/","guest","guest");
        //2、声明通道
        Channel channel = connection.createChannel();
        //3、声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        //4、定义队列的消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        //5、监听队列
        /*
         true:表示自动确认，只要消息从队列中获取，无论消费者获取到消息后是否成功消费，都会认为消息已经成功消费
         false:表示手动确认，消费者获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，
         如果消费者一直没有反馈，那么该消息将一直处于不可用状态，并且服务器会认为该消费者已经挂掉，不会再给其
         发送消息，直到该消费者反馈。
         */
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
