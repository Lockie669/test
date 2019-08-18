package com.jack.service;

import com.jack.domain.Permission;
import com.jack.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll();

    void save (Role role);

    List<Role> findByUserId (String userId);

    Role findByRoleId (String roleId);

    void addPermissionToRole (String[] permissionIds, String roleId);
}
