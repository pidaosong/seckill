package com.pi.miaosha.dao;

import com.pi.miaosha.config.eee;
import com.pi.miaosha.domain.MiaoshaOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {
    @Autowired
    OrderDao orderDao;


    @Test
    public void getMiaoshaOrderByUserIdProductId() {
        MiaoshaOrder miaoshaOrder = orderDao.getMiaoshaOrderByUserIdProductId(1L,1L);
        System.out.println(miaoshaOrder.toString());
    }

    @Test
    public void test(){
        eee.get().print();
    }
}