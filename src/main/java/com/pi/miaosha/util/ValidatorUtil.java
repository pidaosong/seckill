package com.pi.miaosha.util;


import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 14:04
 **/
public class ValidatorUtil {

    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
       if (StringUtils.isEmpty(src)){
           return false;
       }
        Matcher m = mobile_pattern.matcher(src);
       return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("13112345678"));
    }
}
