package com.atguigu.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    static final String QUEUE_NAME = "simple_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2.设置参数 //主机地址;默认为 localhost
        connectionFactory.setHost("192.168.206.26");
        // 连接端口;默认为 5672
        connectionFactory.setPort(5672);
        // 虚拟主机名称;默认为 /
        connectionFactory.setVirtualHost("/shopping");
        // 连接用户名；默认为guest
        connectionFactory.setUsername("admin");
        // 连接密码；默认为guest
        connectionFactory.setPassword("7226210");
        // 3.创建连接
        Connection connection = connectionFactory.newConnection();
        // 4.创建频道
        Channel channel = connection.createChannel();
        // 5.声明（创建）队列
        /**
         * 参数1：队列名称
         *参数2：是否定义持久化队列
         * 参数3：是否独占本次连接
         * 参数4：是否在不使用的时候自动删除队列
         * 参数5：队列其它参数 */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 要发送的信息
        String message = "你好；小兔子！";
        /**
         * 参数1：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数2：路由key,简单模式可以传递队列名称
         * 参数3：消息其它属性
         * 参数4：消息内容
         */
        //6.发送消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("已发送消息：" + message);
        //7.关闭资源
        channel.close();
        connection.close();
        }
}
