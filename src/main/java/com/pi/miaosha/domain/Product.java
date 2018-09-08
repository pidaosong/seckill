package com.pi.miaosha.domain;

import lombok.Data;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 13:45
 **/
@Data
public class Product {
    private Long id;
    private String name;
    private String title;
    private String img;
    private String detail;
    private Double price;
    private Integer stock;
    private MiaoshaProduct miaoshaProduct;
}
