package com.jack.service.impl;

import com.jack.dao.UserInfoDao;
import com.jack.domain.Role;
import com.jack.domain.UserInfo;
import com.jack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * UserDetails是一个接口，我们可以认为UserDetails作用是于封装当前进行认证的用户信息--返回给spring security框架进行登录处理
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        //根据名字查询该用户信息
        UserInfo userInfo = userInfoDao.findByUserName ( username );
        //查询用户关联的角色集合
        List<Role> roles = userInfo.getRoles ();
        //调用方法返回授权的信息集合
        List<SimpleGrantedAuthority> authority=getAuthority ( roles );

        //这个User是spring security自带的对象类，下面是含参构造函数
       User user=new User ( userInfo.getUsername (),userInfo.getPassword (),userInfo.getStatus ()==0?false:true,
               true,true,true, authority);
        return user;
//         public User(String username, String password, boolean enabled,
//                boolean accountNonExpired, boolean credentialsNonExpired,
//                boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
//                Collection<? extends GrantedAuthority> authorities   需要GrantedAuthority类或者他的子类的对象集合作为参数
    }

    //封装授权的信息名字
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){


        List<SimpleGrantedAuthority> authority=new ArrayList<> (  );
        //遍历角色集合，给用户不同的授权名字
        for (Role role : roles) {
            authority.add ( new SimpleGrantedAuthority (  "ROLE_"+role.getRoleName ()) );
        }
        return authority;
    }

    /**
     * 查询所有拥用户
     * @return
     */
    @Override
    public List<UserInfo> findAll ( ) {
        return userInfoDao.findAll();
    }

    @Override
    public void saveUserInfo (UserInfo userInfo) {
        //利用加密passwordEncoder对象对密码进行加密处理，再赋值给password
        userInfo.setPassword ( passwordEncoder.encode ( userInfo.getPassword () ) );
        userInfoDao.saveUserInfo ( userInfo) ;
    }

    @Override
    public UserInfo findById (String id) {

        return userInfoDao.findById(id);
    }

    //遍历数组插入表users-role，建立用户与角色的关系
    @Override
    public void addRoleToUser (String[] roleIds, String userId) {
        for (String roleId : roleIds) {
            userInfoDao.addRoleToUser(roleId,userId);
        }

    }
}
