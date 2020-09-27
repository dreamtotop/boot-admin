package org.top.thymeboot.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.top.thymeboot.system.model.SysMenu;
import org.top.thymeboot.system.service.SysMenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/list")
    public String index(){
        return "module/menu/menu";
    }

    @GetMapping("/update")
    public String update(String id, Model model){
        SysMenu sysMenu = sysMenuService.getById(id);
        model.addAttribute("sysMenu",sysMenu);
        return "module/menu/updateMenu";
    }

    @GetMapping("/add")
    public String add(){
        return "module/menu/addMenu";
    }

}
