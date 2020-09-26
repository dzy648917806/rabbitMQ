package com.atguigu.rabbitmq.fanout;

import com.atguigu.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
    private final static String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //消息内容
        String message = "hello everyone";
        //发布消息到EXCHANGE
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println("[生产者]Sent '" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
