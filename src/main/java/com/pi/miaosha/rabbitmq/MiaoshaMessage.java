package com.pi.miaosha.rabbitmq;

import com.pi.miaosha.domain.User;
import lombok.Data;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-10 12:23
 **/
@Data
public class MiaoshaMessage {
    private User user;
    private long id;
}
