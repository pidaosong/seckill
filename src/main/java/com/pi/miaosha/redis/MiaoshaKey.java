package com.pi.miaosha.redis;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-10 13:28
 **/
public class MiaoshaKey extends BasePrefix{
    public MiaoshaKey(String prefix) {
        super(prefix);
    }
    public static MiaoshaKey isProductOver = new MiaoshaKey("po");
}
