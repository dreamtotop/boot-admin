package org.top.thymeboot.system.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.top.thymeboot.common.utils.SecurityUtils;
import org.top.thymeboot.system.model.SysUser;
import org.top.thymeboot.system.service.SysRoleService;
import org.top.thymeboot.system.service.SysUserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/list")
    public String index(){
        return "module/user/user";
    }

    @GetMapping("/update")
    public String update(String id, Model model){
        SysUser sysUser = sysUserService.getById(id);
        String roleName = sysRoleService.getById(sysUser.getId());
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("roleName", roleName);
        return "module/user/updateUser";
    }

    @GetMapping("/add")
    public String add(){
        return "module/user/addUser";
    }

    @GetMapping("/changePassword")
    public String changePassword(){
        return "module/user/changePassword";
    }

    @GetMapping("/personal")
    public String personal(Model model){
        Authentication authentication = SecurityUtils.getCurrentUserAuthentication();
        String username = (String)authentication.getPrincipal();
        SysUser sysUser = sysUserService.findByName(username);
        String roleName = sysRoleService.getById(sysUser.getId());
        model.addAttribute("sysUser", JSONObject.fromObject(sysUser));
        model.addAttribute("roleName", roleName);
        return "module/user/personal";
    }



}
