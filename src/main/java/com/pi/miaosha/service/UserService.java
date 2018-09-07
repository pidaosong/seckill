package com.pi.miaosha.service;

import com.pi.miaosha.domain.User;
import com.pi.miaosha.vo.LoginVo;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-06 19:00
 **/
public interface UserService {
    
    User getUserById(long id);
    

    int addUser(User user);

}
