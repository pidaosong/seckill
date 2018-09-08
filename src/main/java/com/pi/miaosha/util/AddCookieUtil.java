package com.pi.miaosha.util;

import com.pi.miaosha.domain.User;
import com.pi.miaosha.redis.RedisService;
import com.pi.miaosha.redis.UserKey;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 12:04
 **/
public class AddCookieUtil {

    public static  String COOKIE_NAME_TOKEN = "token";


    public static void addCookie(HttpServletResponse response, String token, User user,RedisService redisService) {
        redisService.set(UserKey.token,token,user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
