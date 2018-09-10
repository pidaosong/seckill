package com.pi.miaosha.rabbitmq;

import com.pi.miaosha.domain.MiaoshaOrder;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.redis.RedisService;
import com.pi.miaosha.service.MiaoshaService;
import com.pi.miaosha.service.OrderService;
import com.pi.miaosha.service.ProductService;
import com.pi.miaosha.vo.ProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-10 10:55
 **/
@Component
public class MQReceiver {

    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    private ProductService productService;


    @Autowired
    private OrderService orderServer;

    @Autowired
    private MiaoshaService miaoshaService;

    @RabbitListener(queues=MQconfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        logger.info("receive message:"+message);
        MiaoshaMessage mm  = RedisService.stringToBean(message, MiaoshaMessage.class);
        User user = mm.getUser();
        long id = mm.getId();

        ProductVo product = productService.getProduct(id);
        int count = product.getCount();
        if(count <= 0) {
            return;
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderServer.getMiaoshaOrderByUserIdProductId(user.getId(),id);
        if(order != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        miaoshaService.miaosha(user, product);
    }


    /*@RabbitListener(queues = MQconfig.QUEUE)
    public void receive(String massage){
        logger.info("massage:"+massage);
    }

    @RabbitListener(queues = MQconfig.TOPIC_QUEUE1)
    public void receiveTopic1(String massage){
        logger.info("massage receiveTopic1:"+massage);
    }

    @RabbitListener(queues = MQconfig.TOPIC_QUEUE2)
    public void receiveTopic2(String massage){
        logger.info("massage receiveTopic2:"+massage);
    }

    @RabbitListener(queues=MQconfig.HEADER_QUEUE)
		public void receiveHeaderQueue(byte[] message) {
			logger.info(" header  queue message:"+new String(message));
		}*/

}
