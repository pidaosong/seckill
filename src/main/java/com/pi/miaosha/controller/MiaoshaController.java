package com.pi.miaosha.controller;

import com.pi.miaosha.domain.MiaoshaOrder;
import com.pi.miaosha.domain.OrderInfo;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.result.CodeEnums;
import com.pi.miaosha.service.MiaoshaService;
import com.pi.miaosha.service.OrderServer;
import com.pi.miaosha.service.ProductService;
import com.pi.miaosha.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 20:11
 **/
@Controller
public class MiaoshaController {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderServer orderServer;

    @Autowired
    private MiaoshaService miaoshaService;

    @PostMapping("/miaosha")
    private String miaosha(Model model, User user, @RequestParam("id")long id){
        model.addAttribute("user",user);
        if(user==null){
            return "login";
        }
        //判断库存
        ProductVo product = productService.getProduct(id);
        int stock = product.getStock();
        if (stock<=0){
            model.addAttribute("error",CodeEnums.MIAO_SHA_OVER.getMsg());
            return "miaoshaerror";
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderServer.getMiaoshaOrderByUserIdProductId(user.getId(),id);
        if (order!=null){
            model.addAttribute("error",CodeEnums.REPEAT_MIAOSHA.getMsg());
            return "miaoshaerror";
        }
        //减库存 下订单 写入订单
        OrderInfo orderInfo = miaoshaService.miaosha(user,product);
        model.addAttribute("orderinfo",orderInfo);
        model.addAttribute("product",product);
        return null;
    }
}
