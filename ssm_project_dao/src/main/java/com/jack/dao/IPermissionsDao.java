package com.jack.dao;

import com.jack.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionsDao {

    @Select("select * from permission where id in(select PERMISSIONID from role_permission where ROLEID=#{id})")
    public List<Permission> findPermissionByRoleId (String id);

    @Select("select * from permission")
    public List<Permission> findAll ( );

    @Insert("insert into permission(permissionname,url) values(#{permissionName},#{url})")
    public void save (Permission permission);

    @Select("select * from permission where id not in(select PERMISSIONID from ROLE_PERMISSION where ROLEID =#{roleId})")
    List<Permission> findExcludePermissionByRoleId (String roleId);


}
