package com.rabbitmq.demo.queue.publishsubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.demo.utils.ConnectionUtil;

/**
 * 发布订阅模式：一个消费者将消息首先发送到交换器，交换器绑定到多个队列，然后被监听该队列的消费者所接收并消费。
 * ps:X表示交换器，在RabbitMQ中，交换器主要有四种类型:direct、fanout、topic、headers，这里的交换器是 fanout。下面我们会介绍这几种交换器。
 * Created by Chunming_Wang on 2019/12/9.
 */
public class Send {

    private final static String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws Exception{
        //1、获取连接
        Connection connection = ConnectionUtil.getConnection(
                "127.0.0.1",5672,"/","guest","guest");
        //2、声明信道
        Channel channel = connection.createChannel();
        //3、声明交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //4、定义消息内容
        String message = "hello rabbitmq ";
        //5、发布消息
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println("[x] Sent '" + message + "'");
        //6、关闭通道
        channel.close();
        //7、关闭连接
        connection.close();
    }

}
