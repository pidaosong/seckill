package com.pi.miaosha.vo;

import com.pi.miaosha.validator.IsMobile;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 13:23
 **/
@Data
public class LoginVo {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    private String password;
}
