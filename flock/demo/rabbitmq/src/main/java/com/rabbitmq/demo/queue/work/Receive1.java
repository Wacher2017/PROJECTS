package com.rabbitmq.demo.queue.work;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.utils.ConnectionUtil;

import java.io.IOException;

/**
 * work 模式：一个生产者对应多个消费者，但只能有一个消费者获得消息 （竞争消费者模式）
 * 应用场景：效率高的消费者消费消息多。可以用来进行负载均衡。
 * Created by Chunming_Wang on 2019/12/9.
 */
public class Receive1 {

    private final static String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception{
        //1、获取连接
        Connection connection = ConnectionUtil.getConnection(
                "127.0.0.1",5672,"/","guest","guest");
        //2、声明通道
        final Channel channel = connection.createChannel();
        //3、声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //同一时刻服务器只会发送一条消息给消费者(能者多劳)，若不加该设置，消费者获取的消息条数是一样的(即不构成竞争关系)
        channel.basicQos(1);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        //4、定义队列的消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("消费者1: 消息消费完成...");
                    //确认返回状态
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        //5、监听队列
        /*
         true:表示自动确认，只要消息从队列中获取，无论消费者获取到消息后是否成功消费，都会认为消息已经成功消费
         false:表示手动确认，消费者获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，
         如果消费者一直没有反馈，那么该消息将一直处于不可用状态，并且服务器会认为该消费者已经挂掉，不会再给其
         发送消息，直到该消费者反馈。
         */
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }

}
