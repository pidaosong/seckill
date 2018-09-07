package com.pi.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-06 18:50
 **/
@Data
public class User {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
