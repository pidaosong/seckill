package com.pi.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 13:42
 **/
@Data
public class OrderInfo {
    private Long id;
    private Long tbUserId;
    private Long tbProductId;
    private Long deliveryAddrId;
    private String name;
    private Integer count;
    private Double price;
    private Integer channel;
    private Integer status;
    private Date createDate;
    private Date payDate;
}
