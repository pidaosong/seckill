package com.pi.miaosha.service;

import com.pi.miaosha.vo.ProductVo;

import java.util.List;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 14:01
 **/
public interface ProductService {
    /**
     * 查询所有商品信息
     * @return
     */
    List<ProductVo> getProductVoList();
}
