package com.pi.miaosha.controller;

import com.pi.miaosha.domain.User;
import com.pi.miaosha.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    /*@GetMapping("/productlist")
    public String getProductlist(*//*Model model, User user*//*){
        //model.addAttribute("user",user);
        return "productlist";
    }*/







}
