package com.pi.miaosha.redis;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-09 20:32
 **/
public class ProductKey extends BasePrefix{


    public ProductKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static  ProductKey productList = new ProductKey(60,"pl");

    public static ProductKey productDeatil = new ProductKey(60,"pd");

    public static ProductKey miaoshaProduct = new ProductKey(0,"mp");
}
