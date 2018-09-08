package com.pi.miaosha.vo;

import com.pi.miaosha.domain.Product;
import lombok.Data;

import java.util.Date;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 15:08
 **/
@Data
public class ProductVo extends Product {
    private Double miaoshaPrice;
    private Integer count;
    private Date startDate;
    private Date endDate;
}
