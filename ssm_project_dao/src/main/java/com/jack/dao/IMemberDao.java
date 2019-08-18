package com.jack.dao;

import com.jack.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Select( "select * from member where id=#{id}" )
    public Member findById(String id);

}
