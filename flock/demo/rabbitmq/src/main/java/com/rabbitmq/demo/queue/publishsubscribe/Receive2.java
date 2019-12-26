package com.rabbitmq.demo.queue.publishsubscribe;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.utils.ConnectionUtil;

import java.io.IOException;

/**
 * 发布订阅模式：一个消费者将消息首先发送到交换器，交换器绑定到多个队列，然后被监听该队列的消费者所接收并消费。
 * ps:X表示交换器，在RabbitMQ中，交换器主要有四种类型:direct、fanout、topic、headers，这里的交换器是 fanout。下面我们会介绍这几种交换器。
 *
 * 消费1和消费者2都消费了该消息
 * ps：这是因为消费者1和消费者2都监听了被同一个交换器绑定的队列。如果消息发送到没有队列绑定的交换器时，消息将丢失，因为交换器没有存储消息的能力，消息只能存储在队列中。
 * Created by Chunming_Wang on 2019/12/9.
 */
public class Receive2 {

    private final static String EXCHANGE_NAME = "fanout_exchange";

    //注意：消费者1和消费者2两者监听的队列名称是不一样的
    private final static String QUEUE_NAME = "publish_subscribe_queue_2";

    public static void main(String[] args) throws Exception{
        //1、获取连接
        Connection connection = ConnectionUtil.getConnection(
                "127.0.0.1",5672,"/","guest","guest");
        //2、声明通道
        final Channel channel = connection.createChannel();
        //3、声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4、绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"");
        //同一时刻服务器只会发送一条消息给消费者(能者多劳)，若不加该设置，消费者获取的消息条数是一样的(即不构成竞争关系)
        channel.basicQos(1);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        //5、定义队列的消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Consumer2: '" + message + "'");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Consumer2: 消息消费完成...");
                    //确认返回状态
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        //6、监听队列,手动返回完成状态
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }

}
