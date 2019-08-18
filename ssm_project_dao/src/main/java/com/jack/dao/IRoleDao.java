package com.jack.dao;

import com.jack.domain.Permission;
import com.jack.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in(select ROLEID from USERS_ROLE where USERID=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            //多对多查询角色关联的权限信息集合
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.jack.dao.IPermissionsDao.findPermissionByRoleId"))
    })
    public List<Role> findRolesByUserId (String userId);

    @Select("select * from role")
    public List<Role> findAll ( );


    @Insert("insert into role(ROLENAME,ROLEDESC) values(#{roleName},#{roleDesc})")
    public void save (Role role);

    //id not in查询该用户不包含的角色
    @Select("select * from role where id not in(select ROLEID from USERS_ROLE where USERID=#{userId})")
    List<Role> findExcludeRolesByUserId (String userId);

    //通过id查询一个角色
    @Select("select * from role where id=#{roleId}")
    Role findByRoleId (String roleId);


    @Insert("insert into ROLE_PERMISSION(permissionid,roleid ) values(#{permissionId},#{roleId})")
    public void addPermissionToRole(@Param("permissionId") String permissionId, @Param("roleId")String roleId);
}
