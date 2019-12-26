package com.rabbitmq.demo.queue.topic;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.utils.ConnectionUtil;

import java.io.IOException;

/**
 * 主题模式：路由模式是根据路由key进行完整的匹配（完全相等才发送消息）这里的通配符模式通俗的来讲就是模糊匹配。
 * 符号“#”表示匹配一个或多个词，符号“*”表示匹配一个词。
 *
 * 生产者发送消息绑定的路由key为update.Name；消费者1监听的队列和交换器绑定路由key为update.#；消费者2监听的队列和交换器绑定路由key为select.#。
 * 很显然，消费者1会接收到消息，而消费者2接收不到。
 * Created by Chunming_Wang on 2019/12/9.
 */
public class Receive1 {

    private final static String EXCHANGE_NAME = "topic_exchange";

    private final static String QUEUE_NAME = "topic_queue_1";

    public static void main(String[] args) throws Exception{
        //1、获取连接
        Connection connection = ConnectionUtil.getConnection(
                "127.0.0.1",5672,"/","guest","guest");
        //2、声明通道
        final Channel channel = connection.createChannel();
        //3、声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4、绑定队列到交换机，指定路由key为update.#
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"update.#");
        //同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        //5、定义队列的消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Handler1: '" + message + "'");
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Handler1: 消息消费完成...");
                    //确认返回状态
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        //6、监听队列,手动返回完成状态
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }

}
