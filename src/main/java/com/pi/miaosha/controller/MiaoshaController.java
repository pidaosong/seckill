package com.pi.miaosha.controller;

import com.pi.miaosha.domain.MiaoshaOrder;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.rabbitmq.MQSender;
import com.pi.miaosha.rabbitmq.MiaoshaMessage;
import com.pi.miaosha.redis.OrderKey;
import com.pi.miaosha.redis.ProductKey;
import com.pi.miaosha.redis.RedisService;
import com.pi.miaosha.result.CodeEnums;
import com.pi.miaosha.result.Result;
import com.pi.miaosha.service.MiaoshaService;
import com.pi.miaosha.service.OrderService;
import com.pi.miaosha.service.ProductService;
import com.pi.miaosha.vo.ProductVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 20:11
 **/
@Controller
public class MiaoshaController implements InitializingBean {
    @Autowired
    private ProductService productService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private OrderService orderServer;

    @Autowired
    private MiaoshaService miaoshaService;

    @Autowired
    private MQSender mqSender;

    private HashMap<Long,Boolean> localOverMap = new HashMap<>();

    /**
     * 系统初始化
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception{
        List<ProductVo> prodcutList = productService.getProductVoList();
        if (prodcutList==null){
            return;
        }
        for (ProductVo productVo : prodcutList) {
              redisService.set(ProductKey.miaoshaProduct,""+productVo.getId(),productVo.getCount());
              localOverMap.put(productVo.getId(),false);
        }
    }



    @PostMapping("/miaosha")
    @ResponseBody
    private Result<Integer> miaosha(Model model, User user, @RequestParam("id")long id){
        model.addAttribute("user",user);
        if(user==null){
            return Result.error(CodeEnums.SESSION_ERROR);
        }
        //内存标记,减少redis访问
        boolean over = localOverMap.get(id);
        if (over){
            return Result.error(CodeEnums.MIAO_SHA_OVER);
        }

        //预减库存
        long count = redisService.decr(ProductKey.miaoshaProduct,""+id);
        if (count<0){
            localOverMap.put(id,true);
            return Result.error(CodeEnums.MIAO_SHA_OVER);
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderServer.getMiaoshaOrderByUserIdProductId(user.getId(),id);
        if (order!=null){
            return Result.error(CodeEnums.REPEAT_MIAOSHA);
        }
        //入队
        MiaoshaMessage mm = new MiaoshaMessage();
        mm.setUser(user);
        mm.setId(id);
        mqSender.sendMiaoshaMessage(mm);
        return Result.success(0);
       /* //判断库存
        ProductVo product = productService.getProduct(id);
        int count = product.getCount();
        if (count<=0){
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
        model.addAttribute("product",product);*/
    }

    /**
     * orderId：成功
     * -1：秒杀失败
     * 0： 排队中
     * */
    @GetMapping("/result")
    @ResponseBody
    public Result<Long> miaoshaResult(Model model,User user,
                                      @RequestParam("id")long id) {
        model.addAttribute("user", user);
        if(user == null) {
            return Result.error(CodeEnums.SESSION_ERROR);
        }
        long result  = miaoshaService.getMiaoshaResult(user.getId(), id);
        return Result.success(result);
    }
}
