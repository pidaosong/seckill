package com.pi.miaosha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 12:48
 **/
@Controller
public class redirectController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
