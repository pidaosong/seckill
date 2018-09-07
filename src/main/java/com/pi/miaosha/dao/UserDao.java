package com.pi.miaosha.dao;

import com.pi.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-06 18:55
 **/
@Mapper
public interface UserDao {

    @Select("select id,nickname,password,salt,head,register_date," +
            "last_login_date,login_count from tb_user where id = #{id}")
    User queryUserById(long id);

    @Insert("insert into user(id,name) values(#{id},#{name})")
    int insertUser(User user);
}
