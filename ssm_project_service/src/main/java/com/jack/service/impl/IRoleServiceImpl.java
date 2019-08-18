package com.jack.service.impl;

import com.jack.dao.IRoleDao;
import com.jack.domain.Permission;
import com.jack.domain.Role;
import com.jack.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public List<Role> findAll ( ) {

        return iRoleDao.findAll();
    }

    @Override
    public void save (Role role) {

        iRoleDao.save(role);
    }

    @Override
    public List<Role> findByUserId (String userId) {

        return iRoleDao.findExcludeRolesByUserId ( userId );
    }

    @Override
    public Role findByRoleId (String roleId) {
        return iRoleDao.findByRoleId(roleId);
    }

    @Override
    public void addPermissionToRole (String[] permissionIds, String roleId) {
        for (String permissionId : permissionIds) {
            iRoleDao.addPermissionToRole(permissionId,roleId);
        }

    }


}
