package com.pi.miaosha.service.impl;

import com.pi.miaosha.dao.UserDao;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.exception.GlobalException;
import com.pi.miaosha.redis.RedisService;
import com.pi.miaosha.redis.UserKey;
import com.pi.miaosha.result.CodeEnums;
import com.pi.miaosha.service.UserService;
import com.pi.miaosha.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-06 19:02
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private RedisService redisService;

    @Override
    public User getUserById(long id) {
        //取缓存
        User user = redisService.get(UserKey.getById,""+id,User.class);
        if (user!=null){
            return user;
        }
        user = userDao.queryUserById(id);
        if (user!=null){
            redisService.set(UserKey.getById,""+id,user);
        }
        return user;
    }

    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(long id, String formPass,String token) {
        //取user缓存
        User user = getUserById(id);
        if (user==null){
            throw new GlobalException(CodeEnums.MOBILE_NOT_EXIST.getMsg());
        }
        //更新数据库
        User userUpdate = new User();
        userUpdate.setId(id);
        userUpdate.setPassword(MD5Util.formPassToDbPass(formPass,user.getSalt()));
        int effectedNum = userDao.updateUser(userUpdate);
        //存缓存
        redisService.delete(UserKey.getById,""+id);
        user.setPassword(userUpdate.getPassword());
        redisService.set(UserKey.token,token,user);
        return effectedNum;
    }
}
