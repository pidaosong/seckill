package com.pi.miaosha.rabbitmq;

import com.pi.miaosha.redis.RedisService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-10 10:55
 **/
@Component
public class MQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMiaoshaMessage(MiaoshaMessage mm) {
        String msg = RedisService.beanToString(mm);
        amqpTemplate.convertAndSend(MQconfig.MIAOSHA_QUEUE, msg);
    }

    /*public void send(Object message){
        String msg = RedisService.beanToString(message);
        amqpTemplate.convertAndSend(MQconfig.QUEUE,msg);
    }

    public void sendTopic(Object message){
        String msg = RedisService.beanToString(message);
        amqpTemplate.convertAndSend(MQconfig.TOPIC_EXCHANGE,"topic.key1",msg+"1");
        amqpTemplate.convertAndSend(MQconfig.TOPIC_EXCHANGE,"topic.2",msg+"2");
    }

    public void sendFanout(Object message){
        String msg = RedisService.beanToString(message);
        amqpTemplate.convertAndSend(MQconfig.FANOUT_EXCHANGE,"",msg);
    }

    public void sendHeader(Object message){
        String msg = RedisService.beanToString(message);
        MessageProperties properties = new MessageProperties();
		properties.setHeader("header1", "value1");
		properties.setHeader("header2", "value2");
        Message obj = new Message(msg.getBytes(),properties);
        amqpTemplate.convertAndSend(MQconfig.HEADERS_EXCHANGE,"",obj);
    }*/
}
