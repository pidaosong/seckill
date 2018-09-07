package com.pi.miaosha.service.impl;

import com.pi.miaosha.dao.UserDao;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-06 19:02
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public User getUserById(long id) {
        return userDao.queryUserById(id);
    }

    @Override
    public int addUser(User user) {
        return 0;
    }
}
