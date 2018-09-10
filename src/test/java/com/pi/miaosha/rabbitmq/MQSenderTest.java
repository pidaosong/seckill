package com.pi.miaosha.rabbitmq;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MQSenderTest {
    @Autowired
    private MQSender mqSender;

    /*@Test
    public void send() {
        mqSender.send("你好");
    }

    @Test
    public void send1(){
        mqSender.sendTopic("nihao");
    }

    @Test
    public void send2(){
        mqSender.sendFanout("你好啊");
    }

    @Test
    public void send3(){
        mqSender.sendHeader("你好啊");
    }*/
}