package com.pi.miaosha.service.impl;

import com.pi.miaosha.domain.OrderInfo;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.service.MiaoshaService;
import com.pi.miaosha.service.OrderServer;
import com.pi.miaosha.service.ProductService;
import com.pi.miaosha.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 20:45
 **/
@Service
@Transactional
public class MiaoshaServiceImpl implements MiaoshaService {


    @Autowired
    private ProductService productService;

    @Autowired
    private OrderServer orderServer;
    /**
     * 减库存 下订单 写入订单
     * @param user
     * @param product
     * @return
     */
    @Override
    public OrderInfo miaosha(User user, ProductVo product) {

        //减库存
        int effectedNum = productService.updateStock(product);

        //创建order_info miaosha_order
        OrderInfo orderInfo = orderServer.addOrder(user,product);

        return orderInfo;
    }
}
