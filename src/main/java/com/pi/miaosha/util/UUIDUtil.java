package com.pi.miaosha.util;

import java.util.UUID;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 20:45
 **/
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
