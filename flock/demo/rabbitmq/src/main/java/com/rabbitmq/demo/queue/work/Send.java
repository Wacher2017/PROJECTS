package com.rabbitmq.demo.queue.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.demo.utils.ConnectionUtil;

/**
 * work 模式：一个生产者对应多个消费者，但只能有一个消费者获得消息 （竞争消费者模式）
 * 应用场景：效率高的消费者消费消息多。可以用来进行负载均衡。
 * Created by Chunming_Wang on 2019/12/9.
 */
public class Send {

    private final static String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception{
        //1、获取连接
        Connection connection = ConnectionUtil.getConnection(
                "127.0.0.1",5672,"/","guest","guest");
        //2、声明信道
        Channel channel = connection.createChannel();
        //3、声明(创建)队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4、定义消息内容(发布多条消息)
        for (int i = 0; i < 10; i++) {
            String message = "hello rabbitmq " + i;
            //5、发布消息
            channel.basicPublish("", QUEUE_NAME,null, message.getBytes());
            System.out.println("[x] Sent'"+message+"'");
            //模拟发送消息延时，便于演示多个消费者竞争接受消息
            Thread.sleep(i * 10);
        }
        //6、关闭通道
        channel.close();
        //7、关闭连接
        connection.close();
    }

}
