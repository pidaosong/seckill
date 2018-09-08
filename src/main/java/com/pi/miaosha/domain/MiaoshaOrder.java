package com.pi.miaosha.domain;

import lombok.Data;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 13:37
 **/
@Data
public class MiaoshaOrder {
    private Long id;
    private Long tbUserId;
    private Long orderInfoId;
    private Long tbProductId;
}
