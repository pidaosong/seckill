package com.pi.miaosha.redis;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 11:00
 **/
public class UserKey extends BasePrefix{


    private UserKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }

    public static UserKey token = new UserKey(6000,"tk");

    public static UserKey getById = new UserKey(0,"id");
}
