package com.pi.miaosha.config;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-10 20:14
 **/
public class eee {

    private static eee test = new eee();

    public static eee get(){
        return test;
    }
    public void print(){
        System.out.println("123");
    }
}
