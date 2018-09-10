package com.pi.miaosha.dao;

import com.pi.miaosha.domain.MiaoshaOrder;
import com.pi.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 20:39
 **/
@Mapper
public interface OrderDao {

    @Select("select * from miaosha_order where tb_user_id=#{userId} and tb_product_id=#{productId}")
    MiaoshaOrder getMiaoshaOrderByUserIdProductId(@Param("userId") Long userId, @Param("productId") long productId);

    @Insert("insert into order_info(tb_user_id,tb_product_id,delivery_addr_id,name," +
            "count,price,channel,status,create_date) values(#{tbUserId},#{tbProductId}," +
            "#{deliveryAddrId},#{name},#{count},#{price},#{channel},#{status},#{createDate})")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = long.class, before = false,statement = "select last_insert_id()")
    long insertOrderInfo(OrderInfo orderInfo);

    @Insert("insert into miaosha_order(tb_user_id,order_info_id,tb_product_id) values(" +
            "#{tbUserId},#{orderInfoId},#{tbProductId})")
    int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

    @Select("select * from order_info where id = #{id}")
    OrderInfo queryOrderById(long id);
}
