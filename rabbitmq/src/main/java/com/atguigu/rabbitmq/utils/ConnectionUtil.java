package com.atguigu.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
    public static Connection getConnection() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.206.26");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/shopping");
        connectionFactory.setUsername("dddd");
        connectionFactory.setPassword("7226210");
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
    public static void main(String[] args) throws Exception{
        Connection con = ConnectionUtil.getConnection();
        System.out.println(con);
    }
}
