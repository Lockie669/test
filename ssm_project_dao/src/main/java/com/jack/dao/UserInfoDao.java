package com.jack.dao;

import com.jack.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserInfoDao {


    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            //查询角色
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.jack.dao.IRoleDao.findRolesByUserId")),
    }
    )
    public UserInfo findByUserName (String username);

    //查询所有用户
    @Select("select * from users")
    public List<UserInfo> findAll ( );

    //保存一个用户信息
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void saveUserInfo (UserInfo userInfo);

    //查询一个用户信息详情
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            //查询角色
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.jack.dao.IRoleDao.findRolesByUserId"))
    })
    public UserInfo findById (String id);

    //给用户添加角色--这里是两个对象类型的参数，不能简单的完成自动赋值，需要手动指定
    @Insert("insert into USERS_ROLE(userid,roleid) values(#{userid},#{roleid})")
    public void addRoleToUser (@Param("roleid") String roleId, @Param("userid") String userId);
}
