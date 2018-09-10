package com.pi.miaosha.dao;

import com.pi.miaosha.domain.MiaoshaProduct;
import com.pi.miaosha.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-08 13:48
 **/
@Mapper
public interface ProductDao {

    @Select("select p.*,mp.miaosha_price,mp.count,mp.start_date,mp.end_date from miaosha_product mp left join tb_product p on mp.tb_product_id = p.id")
    List<ProductVo> queryProductVoList();

    @Select("select p.*,mp.miaosha_price,mp.count,mp.start_date,mp.end_date from miaosha_product mp left join tb_product p on mp.tb_product_id = p.id where p.id=#{id}")
    ProductVo queryProductVo(long id);

    @Update("update miaosha_product set count = #{count} where tb_product_id = #{tbProductId} and count > 0 ")
    int updateStock(MiaoshaProduct p);
}
