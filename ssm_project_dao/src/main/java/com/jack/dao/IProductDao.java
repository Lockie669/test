package com.jack.dao;

import com.jack.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Select ( "select * from product where id=#{id}" )
    public Product findById(String id);

    /**
     * 查询所有
     * @return
     */
    @Select ( "select * from product" )
    public List<Product> findAll();

    /**
     * 保存产品
     * @param product
     */
    @Insert ( "insert into\n" +
            "product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)\n" +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},\n" +
            "#{productDesc},#{productStatus})" )
    void saveProduct (Product product);
}
