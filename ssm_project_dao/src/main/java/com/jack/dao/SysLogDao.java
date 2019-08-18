package com.jack.dao;

import com.jack.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {

    @Insert ( "insert into sysLog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})" )
    public void save (SysLog sysLog);

    @Select( "select * from syslog" )
    List<SysLog> findAll ( ) throws Exception;
}
