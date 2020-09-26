package com.atguigu.rabbitmq.fanout;

import com.atguigu.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Consumer2 {
    private final static String QUEUE_NAME = "fanout_exchange_queue_2";
    private final static String EXCHANGE_NAME = "fanout_exchange_test";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //body 即消息体
                String msg = new String(body);
                System.out.println("[消费者2] received：" + msg + "!");
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
