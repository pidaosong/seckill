package com.pi.miaosha.controller;

import com.pi.miaosha.domain.User;
import com.pi.miaosha.redis.RedisService;
import com.pi.miaosha.result.CodeEnums;
import com.pi.miaosha.result.Result;
import com.pi.miaosha.service.UserService;
import com.pi.miaosha.util.AddCookieUtil;
import com.pi.miaosha.util.MD5Util;
import com.pi.miaosha.util.UUIDUtil;
import com.pi.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 12:44
 **/
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/info")
    public Result<User> enter(User user){
        return Result.success(user);
    }


}
