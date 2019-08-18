package com.jack.service;

import com.jack.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll();

    public void save ( Permission permission);

    List<Permission> findExcludePermissionByRoleId (String roleId);
}
