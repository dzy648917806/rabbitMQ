package com.atguigu.rabbitmq.direct;

import com.atguigu.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
    private final static String EXCHANGE_NAME = "direct_exchange_test";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明exchange，指定类型为direct
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //消息内容
        String message = "商品删除了insert，id = 1001";
        //发送消息，并且指定routing key 为：insert ,代表新增商品
        channel.basicPublish(EXCHANGE_NAME,"insert",null,message.getBytes());
        System.out.println("[商品服务：]Sent '" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
