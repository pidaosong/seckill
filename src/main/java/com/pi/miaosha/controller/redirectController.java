package com.pi.miaosha.controller;

import com.pi.miaosha.domain.User;
import com.pi.miaosha.redis.RedisService;
import com.pi.miaosha.redis.UserKey;
import com.pi.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 12:48
 **/
@Controller
public class redirectController {
    @Autowired
    private RedisService redisService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/productlist")
    public String getProductlist(Model model, @CookieValue(value = "token",required = false) String cookieToken
           ,@RequestParam(value="token",required = false)String paramToken){
        if (StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        User user = redisService.get(UserKey.token,token,User.class);
        model.addAttribute("user",user);
        return "productlist";
    }
}
