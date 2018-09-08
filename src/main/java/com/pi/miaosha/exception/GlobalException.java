package com.pi.miaosha.exception;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 20:29
 **/
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 7003707538892036802L;

    public GlobalException(String msg){
        super(msg);
    }
}
