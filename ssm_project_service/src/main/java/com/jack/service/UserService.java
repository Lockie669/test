package com.jack.service;

import com.jack.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService  {
    public List<UserInfo> findAll ( );

    public void saveUserInfo (UserInfo userInfo );

    UserInfo findById (String id );

    void addRoleToUser (String[] roleIds, String userId);
}
