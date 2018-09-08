package com.pi.miaosha.service;

import com.pi.miaosha.domain.MiaoshaOrder;
import com.pi.miaosha.domain.OrderInfo;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.vo.ProductVo;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 20:23
 **/
public interface OrderServer {
    MiaoshaOrder getMiaoshaOrderByUserIdProductId(Long userId, long productId);

    OrderInfo addOrder(User user, ProductVo product);
}
