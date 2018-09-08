package com.pi.miaosha.dao;

import com.pi.miaosha.domain.Product;
import com.pi.miaosha.vo.ProductVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {
    @Autowired
    ProductDao productDao;

    @Test
    public void queryProductVoList() {
        List<ProductVo> list = productDao.queryProductVoList();
        System.out.println(list.get(0).toString());
    }
}