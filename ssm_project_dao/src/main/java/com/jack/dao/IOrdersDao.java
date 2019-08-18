package com.jack.dao;

import com.jack.domain.Member;
import com.jack.domain.Orders;
import com.jack.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    @Select ( "select * from orders" )
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderDesc",column = "orderDesc"),

            //利用外键PRODUCTID查询关联的产品
            @Result(property = "product",column = "PRODUCTID",javaType = Product.class,one = @One(select = "com.jack.dao.IProductDao.findById"))
    })
    public List<Orders> findAll();

    @Select ( "select * from orders where id=#{orderId}" )
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderDesc",column = "orderDesc"),

            //利用外键PRODUCTID查询关联的产品
            @Result(property = "product",column = "PRODUCTID",javaType = Product.class,one = @One(select = "com.jack.dao.IProductDao.findById")),
            //利用外键MEMBERID查询关联的产品
            @Result(property = "member",column = "MEMBERID",javaType = Member.class,one = @One(select = "com.jack.dao.IMemberDao.findById")),
            //利用订单表的id查询关联的产品---利用id取中间表查询所有的旅客表的id,再根据旅客表的id查询所有旅客
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "com.jack.dao.ITravellerDao.findTravellerById"))
    })
    public Orders findById (String orderId);

}
