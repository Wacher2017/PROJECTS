package com.flock.event.external;

import com.flock.common.utils.JsonUtil;
import com.flock.event.rabbit.RabbitmqConsumer;
import com.flock.event.rabbit.RabbitmqManager;
import com.flock.event.rabbit.RabbitmqMessage;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * Created by Chunming_Wang on 2019/12/20.
 */
@Slf4j
public class Producer implements RabbitmqManager {

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private HashMap<String, Channel> channelMap = new HashMap<>();
    private static final String DEFAULT_EXCHANGE = "operation";

    public Producer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;

    }

    private synchronized Channel getChannel(String queueName, boolean reuse) throws IOException, TimeoutException {
        if (connection == null || !connection.isOpen()) {
            connection = connectionFactory.newConnection();
        }
        if (reuse) {
            Channel channel = channelMap.get(queueName);
            if (channel == null) {
                channel = connection.createChannel();
                channel.queueDeclare(queueName, true, false, false, null);
                channel.basicQos(1);
                channelMap.put(queueName, channel);
            }
            return channel;
        } else {
            Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, true, false, false, null);
            channel.basicQos(1);
            return channel;
        }
    }

    private synchronized Channel getExchangeChannel(String exchange, String type, boolean reuse, boolean durable) throws IOException, TimeoutException {
        if (connection == null || !connection.isOpen()) {
            connection = connectionFactory.newConnection();
        }
        if (reuse) {
            Channel channel = channelMap.get(exchange);
            if (channel == null) {
                channel = connection.createChannel();
                channel.exchangeDeclare(exchange, type, true);
                channel.basicQos(1);
                channelMap.put(exchange, channel);
            }
            return channel;
        } else {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(exchange, type, durable);
            channel.basicQos(1);
            return channel;
        }
    }

    @Override
    public void sendMessage(String queueName, Object... messages) throws IOException, TimeoutException {
        Channel channel = getChannel(queueName, true);
        synchronized (channel) {
            for (Object message : messages) {
                channel.basicPublish("", queueName, MessageProperties.MINIMAL_PERSISTENT_BASIC, JsonUtil.stringify(message).getBytes(Charset.forName("UTF-8")));
            }
        }
    }

    @Override
    public void sendBroadcastMessage(Object... messages) throws IOException, TimeoutException {
        Channel channel = getExchangeChannel(DEFAULT_EXCHANGE, "fanout", true, false);
        synchronized (channel) {
            for (Object message : messages) {
                channel.basicPublish(DEFAULT_EXCHANGE, "", MessageProperties.MINIMAL_PERSISTENT_BASIC, JsonUtil.stringify(message).getBytes(Charset.forName("UTF-8")));
            }
        }
    }

    @Override
    public void sendTopicMessage(String exchange, String topic, boolean durable, Object... messages) throws IOException, TimeoutException {
        if (StringUtils.isBlank(exchange)) {
            Channel channel = getExchangeChannel(DEFAULT_EXCHANGE, "fanout", true, durable);
            synchronized (channel) {
                for (Object message : messages) {
                    channel.basicPublish(DEFAULT_EXCHANGE, "", MessageProperties.MINIMAL_PERSISTENT_BASIC, JsonUtil.stringify(message).getBytes(Charset.forName("UTF-8")));
                }
            }
        } else if (StringUtils.isBlank(topic)) {
            Channel channel = getExchangeChannel(exchange, "fanout", true, durable);
            synchronized (channel) {
                for (Object message : messages) {
                    channel.basicPublish(exchange, "", MessageProperties.MINIMAL_PERSISTENT_BASIC, JsonUtil.stringify(message).getBytes(Charset.forName("UTF-8")));
                }
            }
        } else {
            Channel channel = getExchangeChannel(exchange, "topic", true, durable);
            synchronized (channel) {
                for (Object message : messages) {
                    if (durable) {
                        channel.basicPublish(exchange, topic, MessageProperties.MINIMAL_PERSISTENT_BASIC, JsonUtil.stringify(message).getBytes(Charset.forName("UTF-8")));
                    } else {
                        channel.basicPublish(exchange, topic, MessageProperties.BASIC, JsonUtil.stringify(message).getBytes(Charset.forName("UTF-8")));
                    }

                }
            }
        }
    }

    @Override
    public void dropQueue(String queueName) throws IOException, TimeoutException {
        Channel channel = getChannel(queueName, true);
        AMQP.Queue.DeleteOk deleteOk = channel.queueDelete(queueName);
        channelMap.remove(queueName);
        channel.close();
        log.info(MessageFormat.format("drop queue[{0}] with {1} data", queueName, deleteOk.getMessageCount()));
    }

    @Override
    public <T> void addConsumer(String queueName, Class<T> messageClazz, RabbitmqConsumer<T> consumer, boolean autoAck) throws IOException, TimeoutException {
        final Channel channel = getChannel(queueName, false);
        channel.basicConsume(queueName, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String messageStr = new String(body, "UTF-8");
                long tag = envelope.getDeliveryTag();
                T message;
                try {
                    message = JsonUtil.parse(messageStr, messageClazz);
                } catch (Exception ex) {
                    log.error(MessageFormat.format("parse message failed. queueName={0},declared class={1},message={2}", queueName, messageClazz.getSimpleName(), messageStr), ex);
                    if (!autoAck) {
                        channel.basicReject(tag, false);
                    }
                    return;
                }
                handleMessage(queueName, channel, message, messageStr, messageClazz, consumer, tag, autoAck);
            }
        });
    }

    @Override
    public <T> void addConsumer(String queueName, String exchange, boolean durable, Class<T> messageClazz, RabbitmqConsumer<T> consumer, boolean autoAck) throws IOException, TimeoutException {
        final Channel channel = getExchangeChannel(exchange, "fanout", true, false);
        if (durable) {
            channel.queueDeclare(queueName, true, false, false, null);
        } else {
            channel.queueDeclare(queueName, false, false, true, null);
        }
        channel.queueBind(queueName, exchange, "");
        channel.basicQos(1);
        channel.basicConsume(queueName, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String messageStr = new String(body, "UTF-8");
                long tag = envelope.getDeliveryTag();
                T message;
                try {
                    Map eventMap = JsonUtil.parse(messageStr, Map.class);
                    String className = (String) eventMap.get("className");
                    message = JsonUtil.parse(messageStr, (Class<T>) Class.forName(className));
                    //message = JSON.parse(messageStr, messageClazz);
                } catch (Exception ex) {
                    log.error(MessageFormat.format("parse message failed. queueName={0},declared class={1},message={2}", queueName, messageClazz.getSimpleName(), messageStr), ex);
                    if (!autoAck) {
                        channel.basicReject(tag, false);
                    }
                    return;
                }
                handleMessage(queueName, channel, message, messageStr, messageClazz, consumer, tag, autoAck);
            }
        });
    }

    @Override
    public <T> void addConsumer(String queueName, String exchange, boolean durable, String topic, Class<T> messageClazz, RabbitmqConsumer<T> consumer, boolean autoAck) throws IOException, TimeoutException {
        final Channel channel = getExchangeChannel(exchange, "topic", true, durable);
        if (durable) {
            channel.queueDeclare(queueName, true, false, false, null);
        } else {
            channel.queueDeclare(queueName, false, false, true, null);
        }

        channel.queueBind(queueName, exchange, topic);
        channel.basicQos(1);
        channel.basicConsume(queueName, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String messageStr = new String(body, "UTF-8");
                long tag = envelope.getDeliveryTag();
                T message;
                try {
                    Map eventMap = JsonUtil.parse(messageStr, Map.class);
                    String className = (String) eventMap.get("className");
                    if(StringUtils.isNotBlank(className)){
                        message = JsonUtil.parse(messageStr, (Class<T>) Class.forName(className));
                    }else{
                        message = JsonUtil.parse(messageStr, messageClazz);
                    }
                } catch (Exception ex) {
                    log.error(MessageFormat.format("parse message failed. queueName={0},declared class={1},message={2}", queueName, messageClazz.getSimpleName(), messageStr), ex);
                    if (!autoAck) {
                        channel.basicReject(tag, false);
                    }
                    return;
                }
                handleMessage(queueName, channel, message, messageStr, messageClazz, consumer, tag, autoAck);
            }
        });
    }

    private <T> void handleMessage(String queueName, Channel channel, T message, String messageStr, Class<T> messageClazz, RabbitmqConsumer<T> consumer, long tag, boolean autoAck) throws IOException {
        try {
            String name = Thread.currentThread().getName();
            if (name.contains("-thread-")) {
                Thread.currentThread().setName(queueName + name.replaceAll("pool-", "-").replaceAll("-thread-", "-"));
            }
            consumer.onMessage(buildMessage(channel, message, tag, autoAck));
        } catch (Exception ex) {
            log.error(MessageFormat.format("handle message failed,discard message. queueName={0},declared class={1},message={2}", queueName, messageClazz.getSimpleName(), messageStr), ex);
            if (!autoAck) {
                channel.basicReject(tag, false);
            }
        }
    }

    private <T> RabbitmqMessage<T> buildMessage(final Channel channel, final T message, final long tag, final boolean autoAck) {
        return new RabbitmqMessage<T>() {
            @Override
            public T getMessage() {
                return message;
            }

            @Override
            public void ack() throws IOException {
                if (message != null && !autoAck) {
                    channel.basicAck(tag, false);
                }
            }

            @Override
            public void reject(boolean reQueue) throws IOException {
                if (message != null && !autoAck) {
                    channel.basicReject(tag, reQueue);
                }
            }
        };
    }

    @Override
    public <T> RabbitmqMessage<T> getMessage(String queueName, Class<T> messageClazz, boolean autoAck) throws IOException, TimeoutException {
        Channel channel = getChannel(queueName, true);
        synchronized (channel) {
            T message = null;
            long tag = 0;
            GetResponse response = channel.basicGet(queueName, autoAck);
            if (response != null) {
                String messageStr = new String(response.getBody(), "UTF-8");
                tag = response.getEnvelope().getDeliveryTag();
                try {
                    message = JsonUtil.parse(messageStr, messageClazz);
                } catch (Exception ex) {
                    log.error(MessageFormat.format("parse message failed. queueName={0},declared class={1},message={2}", queueName, messageClazz.getSimpleName(), messageStr), ex);
                    if (!autoAck) {
                        channel.basicReject(tag, false);
                    }
                }
            }
            return buildMessage(channel, message, tag, autoAck);
        }
    }

}
