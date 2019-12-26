package com.rabbitmq.demo.queue.routing;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.utils.ConnectionUtil;

import java.io.IOException;

/**
 * 路由模式：生产者将消息发送到direct交换器，在绑定队列和交换器的时候有一个路由key，生产者发送的消息会指定一个路由key，那么消息只会发送到相应key相同的队列，接着监听该队列的消费者消费消息。
 * 也就是让消费者有选择性的接收消息。
 * Created by Chunming_Wang on 2019/12/9.
 */
public class Receive2 {

    private final static String EXCHANGE_NAME = "direct_exchange";

    private final static String QUEUE_NAME = "routing_queue_2";

    public static void main(String[] args) throws Exception{
        //1、获取连接
        Connection connection = ConnectionUtil.getConnection(
                "127.0.0.1",5672,"/","guest","guest");
        //2、声明通道
        final Channel channel = connection.createChannel();
        //3、声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4、绑定队列到交换机，指定路由key为update
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"select");
        //同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        //5、定义队列的消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Receive2: '" + message + "'");
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Receive2: 消息消费完成...");
                    //确认返回状态
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        //6、监听队列,手动返回完成状态
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }

}
