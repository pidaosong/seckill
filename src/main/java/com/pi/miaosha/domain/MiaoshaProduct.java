package com.pi.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 13:39
 **/
@Data
public class MiaoshaProduct {
    private Long id;
    private Long tbProductId;
    private Double price;
    private Integer count;
    private Date startDate;
    private Date endDate;
}
