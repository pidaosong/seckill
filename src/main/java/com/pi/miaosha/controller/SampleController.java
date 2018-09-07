package com.pi.miaosha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-06 13:33
 **/
@Controller
@RequestMapping("/demo")
public class SampleController {
  /*  @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/thymeleaf")
    public String thymeleaf(){
        return "hello";
    }

    @GetMapping("/getuser")
    @ResponseBody
    public Map<String,Object> getUserByid(){
        Map<String,Object> modelMap = new HashMap<>();
        User user = userService.getById(1);
        modelMap.put("user",user);
        return modelMap;
    }

    @GetMapping("/redis")
    @ResponseBody
    public Map<String,Object> redis(){
        Map<String, Object> modelMap = new HashMap<>();
        User user = userService.getById(1);
        boolean bl = redisService.set(UserKey.getById,""+user.getId(),user);
        User str = redisService.get(UserKey.getById,""+user.getId(), User.class);
        modelMap.put("redis", str);
        return modelMap;
    }*/
}
