package com.atguigu.rabbitmq.topic;

import com.atguigu.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    private final static String QUEUE_NAME = "topic_exchange_queue_1";
    private final static String EXCHANGE_NAME = "topic_exchange_test";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //druable=true表示队列是支持持久化的
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //绑定队列到交换机,同时要指定需要订阅的routing key。假设此处需要update和delete消息
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.update");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.delete");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("[消费者1] received:" + msg + "！");
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
