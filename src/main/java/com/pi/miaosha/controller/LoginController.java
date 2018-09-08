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
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/enter")
    public Map<String,Object> enter(@Valid LoginVo loginVo,HttpServletResponse response){
        Map<String,Object> modelMap = new HashMap<>();
        if (loginVo!=null){
            String password = loginVo.getPassword();
            String mobile = loginVo.getMobile();
            CodeEnums ce = login(response,mobile,password);
            if (ce.getCode()==0){
                modelMap.put("success",Result.success(true));
            }else {
                modelMap.put("success",Result.success(false));
                modelMap.put("error",Result.error(ce));
            }
        }else {
            modelMap.put("success",Result.success(false));
            modelMap.put("error",Result.error(CodeEnums.PARAM_EMPTY));
        }
        return modelMap;
    }

    private CodeEnums login(HttpServletResponse response, String mobile, String password) {
        long id = Long.valueOf(mobile);
        User user = userService.getUserById(id);
        if (user==null){
            return CodeEnums.MOBILE_NOT_EXIST;
        }
        //验证密码
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDbPass(password,saltDB);
        String dbPass = user.getPassword();
        if (!calcPass.equals(dbPass)){
            return CodeEnums.PASSWORD_ERROR;
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, user, token);
        return CodeEnums.SUCCESS;
    }

    private void addCookie(HttpServletResponse response, User user, String token) {
        AddCookieUtil.addCookie(response, token, user,redisService);
    }
}
