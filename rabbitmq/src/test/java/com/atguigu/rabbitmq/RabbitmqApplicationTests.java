package com.atguigu.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    void testsend() throws InterruptedException{
        String msg = "hello,Spring boot amqp";
        this.amqpTemplate.convertAndSend("spring.test.exchange","a.b",msg);
        //等待10秒后结束
        Thread.sleep(10000);
    }

}
