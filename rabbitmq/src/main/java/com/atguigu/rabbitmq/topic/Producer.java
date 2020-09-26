package com.atguigu.rabbitmq.topic;

import com.atguigu.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class Producer {
    private final static String EXCHANGE_NAME = "topic_exchange_test";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明exchange，指定类型为topic
        channel.exchangeDeclare(EXCHANGE_NAME,"topic",true);//durable=true 表示路由支持持久化的
        //消息内容
        String message = "更新商品：id = 1001";
        //发送消息，并且指定routing key 为：insert ,代表新增商品
        channel.basicPublish(EXCHANGE_NAME,"item.xxx.yyy", MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        System.out.println("[商品服务：]Sent '" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
