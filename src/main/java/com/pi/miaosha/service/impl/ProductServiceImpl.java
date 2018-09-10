package com.pi.miaosha.service.impl;

import com.pi.miaosha.dao.ProductDao;
import com.pi.miaosha.domain.MiaoshaProduct;
import com.pi.miaosha.domain.Product;
import com.pi.miaosha.service.ProductService;
import com.pi.miaosha.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 14:03
 **/
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public List<ProductVo> getProductVoList() {
        return productDao.queryProductVoList();
    }

    @Override
    public ProductVo getProduct(long id) {
        return productDao.queryProductVo(id);
    }

    @Override
    public int updateStock(ProductVo product) {
        MiaoshaProduct p = new MiaoshaProduct();
        p.setTbProductId(product.getId());
        p.setCount(product.getCount()-1);
        return productDao.updateStock(p);
    }


}
