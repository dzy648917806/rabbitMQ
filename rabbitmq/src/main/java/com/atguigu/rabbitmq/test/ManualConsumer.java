package com.atguigu.rabbitmq.test;

import com.atguigu.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ManualConsumer {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        //创建频道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(Producer.QUEUE_NAME,true,false,false,null);
        //创建消费者;并设置消息处理
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException, UnsupportedEncodingException {
                //收到的消息
                System.out.println(new String(body));
                //int i = 1/0;
                //手动进行ACK
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //监听消息
        /**
         * 参数1：队列名称
         * 参数2：是否自动确认，设置为true为表示消息接收到自动向mq回复收到了'mq接收到回复会删除消息'反之则到手动确认
         * 参数3：消息接收到后回调
         */
        channel.basicConsume(Producer.QUEUE_NAME,false,consumer);
        //不关闭资源，应该一直监听消息
        //channel.close();
        //connection.close();
        }
    }
