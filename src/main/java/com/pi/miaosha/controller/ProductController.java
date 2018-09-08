package com.pi.miaosha.controller;

import com.pi.miaosha.domain.Product;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.service.ProductService;
import com.pi.miaosha.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 14:06
 **/
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/productlist")
    private String getProductList(Model model,User user){

        //查询商品列表
        List<ProductVo> productList = productService.getProductVoList();
        model.addAttribute("productList",productList);
        model.addAttribute("user",user);
        return "productlist";
    }
}
