package com.jack.service.impl;

import com.jack.dao.IPermissionsDao;
import com.jack.domain.Permission;
import com.jack.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionsDao iPermissionsDao;

    @Override
    public List<Permission> findAll ( ) {
        return iPermissionsDao.findAll();
    }

    @Override
    public void save (Permission permission) {

        iPermissionsDao.save(permission);
    }

    @Override
    public List<Permission> findExcludePermissionByRoleId (String roleId) {
        return iPermissionsDao.findExcludePermissionByRoleId(roleId);
    }
}
