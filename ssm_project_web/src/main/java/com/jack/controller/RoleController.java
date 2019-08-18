package com.jack.controller;

import com.jack.domain.Permission;
import com.jack.domain.Role;
import com.jack.service.IPermissionService;
import com.jack.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll ( ) {

        ModelAndView mv = new ModelAndView ();
        List<Role> list = iRoleService.findAll ();
        mv.addObject ( "roleList", list );
        mv.setViewName ( "role-list" );

        return mv;
    }


    @RequestMapping("/save.do")
    public String save (Role role) {


        iRoleService.save ( role );

        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission (@RequestParam(name = "id", required = true) String roleId) {

        ModelAndView mv = new ModelAndView ();

        Role role = iRoleService.findByRoleId ( roleId );
        List<Permission> permissionList = iPermissionService.findExcludePermissionByRoleId ( roleId );

        mv.addObject ( "role", role );
        mv.addObject ( "permissionList", permissionList );
        mv.setViewName ( "role-permission-add" );

        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String  addPermissionToRole (@RequestParam(name = "ids", required = true) String[] permissionIds, @RequestParam(name = "roleId", required = true) String roleId) {

        iRoleService.addPermissionToRole ( permissionIds, roleId );

        return "redirect:findAll.do";

    }



}
