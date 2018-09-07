package com.pi.miaosha.redis;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 11:00
 **/
public class UserKey extends BasePrefix{

    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");

    public static UserKey getByName = new UserKey("name");
}
