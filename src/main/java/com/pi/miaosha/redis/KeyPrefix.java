package com.pi.miaosha.redis;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 10:54
 **/
public interface KeyPrefix {
    
    /** 
    * @Description: 过期时间 
    * @Param:  
    * @return:
    * @Author: Mr.Pi 
    * @Date: 2018/9/7 10:58
    */ 
    public int expireSeconds();

    public String getPrefix();
}
