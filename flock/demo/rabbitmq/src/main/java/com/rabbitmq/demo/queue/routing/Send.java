package com.rabbitmq.demo.queue.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.demo.utils.ConnectionUtil;

/**
 * 路由模式：生产者将消息发送到direct交换器，在绑定队列和交换器的时候有一个路由key，生产者发送的消息会指定一个路由key，那么消息只会发送到相应key相同的队列，接着监听该队列的消费者消费消息。
 * 也就是让消费者有选择性的接收消息。
 * Created by Chunming_Wang on 2019/12/9.
 */
public class Send {

    private final static String EXCHANGE_NAME = "direct_exchange";

    public static void main(String[] args) throws Exception{
        //1、获取连接
        Connection connection = ConnectionUtil.getConnection(
                "127.0.0.1",5672,"/","guest","guest");
        //2、声明信道
        Channel channel = connection.createChannel();
        //3、声明交换器，类型为direct
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        //4、定义消息内容
        String message = "hello rabbitmq ";
        //5、发布消息
        channel.basicPublish(EXCHANGE_NAME,"update",null,message.getBytes());
        System.out.println("[x] Sent '" + message + "'");
        //6、关闭通道
        channel.close();
        //7、关闭连接
        connection.close();
    }

}
