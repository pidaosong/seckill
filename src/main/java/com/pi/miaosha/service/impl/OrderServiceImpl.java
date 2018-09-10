package com.pi.miaosha.service.impl;

import com.pi.miaosha.dao.OrderDao;
import com.pi.miaosha.domain.MiaoshaOrder;
import com.pi.miaosha.domain.OrderInfo;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.redis.OrderKey;
import com.pi.miaosha.redis.RedisService;
import com.pi.miaosha.service.OrderService;
import com.pi.miaosha.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 20:38
 **/
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    private RedisService redisService;

    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdProductId(Long userId, long productId) {
        return redisService.get(OrderKey.MiaoshaOrder,""+userId+"_"+productId,MiaoshaOrder.class);
    }

    @Override
    public OrderInfo addOrder(User user, ProductVo product) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setCount(1);
        orderInfo.setTbUserId(user.getId());
        orderInfo.setTbProductId(product.getId());
        orderInfo.setName(product.getName());
        orderInfo.setPrice(product.getMiaoshaPrice());
        orderInfo.setChannel(1);
        orderInfo.setStatus(0);
        orderDao.insertOrderInfo(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setOrderInfoId(orderInfo.getId());
        miaoshaOrder.setTbUserId(user.getId());
        miaoshaOrder.setTbProductId(product.getId());
        int num = orderDao.insertMiaoshaOrder(miaoshaOrder);
        redisService.set(OrderKey.MiaoshaOrder,""+user.getId()+"_"+product.getId(),miaoshaOrder);
        return orderInfo;
    }

    @Override
    public OrderInfo getOrderById(long orderId) {
        return orderDao.queryOrderById(orderId);
    }
}
