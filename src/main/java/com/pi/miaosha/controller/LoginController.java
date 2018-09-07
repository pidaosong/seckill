package com.pi.miaosha.controller;

import com.pi.miaosha.domain.User;
import com.pi.miaosha.result.CodeEnums;
import com.pi.miaosha.result.Result;
import com.pi.miaosha.service.UserService;
import com.pi.miaosha.util.MD5Util;
import com.pi.miaosha.util.ValidatorUtil;
import com.pi.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/enter")
    public Map<String,Object> enter(LoginVo loginVo){
        Map<String,Object> modelMap = new HashMap<>();
        logger.info(loginVo.toString());
        String password = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if (StringUtils.isEmpty(password)){
            modelMap.put("error",Result.error(CodeEnums.PASSWORD_EMPTY).getMsg());
            return modelMap;
        }
        if (StringUtils.isEmpty(mobile)){
            modelMap.put("error",Result.error(CodeEnums.MOBILE_EMPTY).getMsg());
            return modelMap;
        }
        if (!ValidatorUtil.isMobile(mobile)){
            modelMap.put("error",Result.error(CodeEnums.MOBILE_ERROR).getMsg());
            return modelMap;
        }
        CodeEnums ce = login(mobile,password);
        if (ce.getCode()==0){
            modelMap.put("success",true);
        }else {
            modelMap.put("error",Result.error(ce).getMsg());
        }
        return modelMap;
    }

    private CodeEnums login(String mobile, String password) {
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
        return CodeEnums.SUCCESS;
    }
}
