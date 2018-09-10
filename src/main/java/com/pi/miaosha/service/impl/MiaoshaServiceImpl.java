package com.pi.miaosha.service.impl;

import com.pi.miaosha.domain.MiaoshaOrder;
import com.pi.miaosha.domain.OrderInfo;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.redis.MiaoshaKey;
import com.pi.miaosha.redis.RedisService;
import com.pi.miaosha.service.MiaoshaService;
import com.pi.miaosha.service.OrderService;
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
    private OrderService orderServer;

    @Autowired
    private RedisService redisService;
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
        if (effectedNum>0)
        {
            //创建order_info miaosha_order
            return orderServer.addOrder(user,product);

        }else {
            setProductOver(product.getId());
            return null;
        }
    }

    private void setProductOver(Long id) {
        redisService.set(MiaoshaKey.isProductOver,""+id,true);
    }

    @Override
    public long getMiaoshaResult(Long userId, long productId) {
        MiaoshaOrder order = orderServer.getMiaoshaOrderByUserIdProductId(userId,productId);
        if (order!=null){
            return order.getOrderInfoId();
        }else {
            boolean isOver = getProductOver(productId);
            if (isOver){
                return -1;
            }else {
                return 0;
            }
        }
    }

    private boolean getProductOver(long productId) {
        return redisService.exists(MiaoshaKey.isProductOver,""+productId);
    }
}
