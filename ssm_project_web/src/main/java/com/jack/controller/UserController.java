package com.jack.controller;


import com.jack.domain.Role;
import com.jack.domain.UserInfo;
import com.jack.service.IRoleService;
import com.jack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private IRoleService iRoleService;

    /**
     * 查询用户信息
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll ( ) throws Exception {
        ModelAndView modelAndView = new ModelAndView ();
        List<UserInfo> list = userService.findAll ();
        modelAndView.addObject ( "userList", list );
        modelAndView.setViewName ( "user-list" );
        return modelAndView;

    }

    /**
     * 保存用户信息
     *
     * @param userInfo
     * @return
     */
    @RequestMapping("/save.do")
    public String save (UserInfo userInfo) throws Exception {
        ModelAndView modelAndView = new ModelAndView ();
        userService.saveUserInfo ( userInfo );

        return "redirect:findAll.do";

    }


    /**
     * 根据用户id查询用户详情信息--包含角色--包含角色关联的权限信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById (String id) throws Exception  {

        ModelAndView modelAndView = new ModelAndView ();

        UserInfo userInfo = userService.findById ( id );

        modelAndView.addObject ( "user", userInfo );
        modelAndView.setViewName ( "user-show" );

        return modelAndView;

    }


    /**
     * 给指定用户添加角色--添加不包含的角色
     *
     * @param userId
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole (@RequestParam(name = "id", required = true) String userId) throws Exception {

        ModelAndView modelAndView = new ModelAndView ();

        //根据id查找用户信息
        UserInfo user = userService.findById ( userId );

        //根据用户的id通过中间表查询真个用户不包含的角色信息
        List<Role> roleList = iRoleService.findByUserId ( userId );

        modelAndView.addObject ( "user", user );
        modelAndView.addObject ( "roleList", roleList );
        modelAndView.setViewName ( "user-role-add" );

        return modelAndView;

    }


    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser (@RequestParam(name = "ids", required = true) String[] roleIds, @RequestParam(name = "userId", required = true) String userId) throws Exception {

        userService.addRoleToUser ( roleIds, userId );

        return "redirect:findAll.do";

    }
}
