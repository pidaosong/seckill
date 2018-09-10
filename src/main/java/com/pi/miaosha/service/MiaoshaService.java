package com.pi.miaosha.service;

import com.pi.miaosha.domain.OrderInfo;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.vo.ProductVo;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 20:33
 **/
public interface MiaoshaService {
    OrderInfo miaosha(User user, ProductVo product);

    long getMiaoshaResult(Long userId, long productId);
}
