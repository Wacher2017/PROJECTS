package com.rabbitmq.demo.queue.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.demo.utils.ConnectionUtil;

/**
 * 主题模式：路由模式是根据路由key进行完整的匹配（完全相等才发送消息）这里的通配符模式通俗的来讲就是模糊匹配。
 * 符号“#”表示匹配一个或多个词，符号“*”表示匹配一个词。
 * Created by Chunming_Wang on 2019/12/9.
 */
public class Send {

    private final static String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws Exception{
        //1、获取连接
        Connection connection = ConnectionUtil.getConnection(
                "127.0.0.1",5672,"/","guest","guest");
        //2、声明信道
        Channel channel = connection.createChannel();
        //3、声明交换器，类型为direct
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //4、定义消息内容
        for (int i = 0; i < 30; i++) {
            String message = "hello rabbitmq " + i;
            //5、发布消息
            channel.basicPublish(EXCHANGE_NAME,"update.Name",null,message.getBytes());
            System.out.println("[x] Sent '" + message + "'");
            //模拟发送消息延时，便于演示多个消费者竞争接受消息
            Thread.sleep(i * 10);
        }

        //6、关闭通道
        channel.close();
        //7、关闭连接
        connection.close();
    }

}
