package com.atguigu.rabbitmq.work;

import com.atguigu.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Consumer2 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Producer.QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException, UnsupportedEncodingException {
                //body 即消息体
                String msg = new String(body);
                System.out.println("[消费者2] received：" + msg + "!");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
                //手动ACK
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //监听队列
        channel.basicConsume(Producer.QUEUE_NAME,false,consumer);
    }
}
