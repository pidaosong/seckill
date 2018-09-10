package com.pi.miaosha.redis;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-10 10:14
 **/
public class OrderKey extends BasePrefix{
    public OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey MiaoshaOrder = new OrderKey("mo");
}
