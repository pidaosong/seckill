package com.pi.miaosha.dao;

import com.pi.miaosha.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
