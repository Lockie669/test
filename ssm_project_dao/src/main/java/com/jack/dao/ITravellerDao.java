package com.jack.dao;

import com.jack.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {
    //多对多的子查询
    @Select ( "select * from traveller where id in (select TRAVELLERID from ORDER_TRAVELLER where ORDERID=#{id} )" )
    public List<Traveller> findTravellerById(String id);
}
