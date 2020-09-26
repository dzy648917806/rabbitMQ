package com.atguigu.rabbitmq.work;

import com.atguigu.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
    final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //循环发布任务
        for (int i = 0; i < 50; i++) {
            //消息内容
            String message = "task .. " + i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("[x]Sent '" + message + "'");
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
