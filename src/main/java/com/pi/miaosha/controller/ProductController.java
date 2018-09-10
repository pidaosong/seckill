package com.pi.miaosha.controller;

import com.pi.miaosha.domain.User;
import com.pi.miaosha.redis.RedisService;
import com.pi.miaosha.service.ProductService;
import com.pi.miaosha.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 14:06
 **/
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private RedisService redisService;


    @GetMapping("/productlist")
    private String getProductList(Model model,User user){
        model.addAttribute("user",user);
        //
        //查询商品列表
        List<ProductVo> productList = productService.getProductVoList();
        model.addAttribute("productList",productList);

        return "productlist";
    }

    @GetMapping("/productdetail/{id}")
    private String getProductdetail(Model model, User user, @PathVariable("id")long id){

        //查询商品
        ProductVo product = productService.getProduct(id);
        model.addAttribute("product",product);
        model.addAttribute("user",user);

        long startDate = product.getStartDate().getTime();
        long endDate = product.getEndDate().getTime();
        long now = System.currentTimeMillis();

        //状态
        int miaoshaStatus = 0;
        //准备开始时间
        int remainSeconds = 0;

/*
        if (now<startDate){//秒杀还没开始
            miaoshaStatus = 0;
            remainSeconds = (int)(startDate - now)/1000;
        }else if(now>endDate){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }*/

        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSeconds",remainSeconds);
        return "productdetail";
    }

    @GetMapping("/productdetail1/{id}")
    @ResponseBody
    private Map<String,Object> getProductdetail1(User user, @PathVariable("id")long id){
        Map<String,Object> modelmap = new HashMap<>();
        //查询商品
        ProductVo product = productService.getProduct(id);

        modelmap.put("product",product);
        modelmap.put("user",user);


        long startDate = product.getStartDate().getTime();
        long endDate = product.getEndDate().getTime();
        long now = System.currentTimeMillis();

        //状态
        int miaoshaStatus = 0;
        //准备开始时间
        int remainSeconds = 0;


        if (now<startDate){//秒杀还没开始
            miaoshaStatus = 0;
            remainSeconds = (int)(startDate - now)/1000;
        }else if(now>endDate){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }

        modelmap.put("miaoshaStatus",miaoshaStatus);
        modelmap.put("remainSeconds",remainSeconds);
        modelmap.put("success",true);
        return modelmap;
    }
}
