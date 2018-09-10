package com.pi.miaosha.controller;

import com.pi.miaosha.domain.OrderInfo;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.redis.RedisService;
import com.pi.miaosha.result.Result;
import com.pi.miaosha.service.MiaoshaService;
import com.pi.miaosha.service.OrderService;
import com.pi.miaosha.service.ProductService;
import com.pi.miaosha.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-10 12:56
 **/
@Controller
public class OrderController {
    @Autowired
    private MiaoshaService miaoshaService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/orderdetail")
    public String info(Model model, User user,
                                      @RequestParam("orderId") long orderId) {
        if(user == null) {
            return "login";
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return "miaoshaerror";
        }
        long id = order.getTbProductId();
        ProductVo productVo = productService.getProduct(id);
        model.addAttribute("orderinfo",order);
        model.addAttribute("product",productVo);
        return "orderdetail";
    }
}
