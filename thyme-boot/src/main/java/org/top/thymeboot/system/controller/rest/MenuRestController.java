package org.top.thymeboot.system.controller.rest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.top.thymeboot.common.base.ApiResponse;
import org.top.thymeboot.common.utils.SecurityUtils;
import org.top.thymeboot.system.model.SysMenu;
import org.top.thymeboot.system.service.SysMenuRoleService;
import org.top.thymeboot.system.service.SysMenuService;
import org.top.thymeboot.system.vo.MenuListVO;
import org.top.thymeboot.system.vo.MenuVO;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuRestController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    @GetMapping("getMenuList")
    public ApiResponse getMenuList(){
        //获取当前登录用户
        Authentication userAuthentication = SecurityUtils.getCurrentUserAuthentication();
        String name = userAuthentication.getName();
        List<MenuVO> menuVoList = sysMenuService.getMenuByUser(name);
        JSONObject data = new JSONObject(16);
        data.put("name",name);
        data.put("menuList",menuVoList);
        return ApiResponse.ofSuccess(data);
    }

    @GetMapping("/getMenuInfo")
    public ApiResponse getMenuInfo(@RequestParam("page") int page,
                                   @RequestParam("page_size") int pageSize){
        JSONObject jsonObject = new JSONObject();
        List<MenuListVO> listVoList = new LinkedList<>();
        IPage<SysMenu> firstMenu = sysMenuService.findFirstMenu(new Page(page, pageSize));
        //组装数据
        List<SysMenu> firstMenuRecords = firstMenu.getRecords();
        for(SysMenu menu : firstMenuRecords){
            List<SysMenu> secondMenuList = sysMenuService.findByParentId(menu.getId());
            listVoList.add(MenuListVO.builder()
                    .id(menu.getId())
                    .children(secondMenuList)
                    .menuName(menu.getMenuName())
                    .menuIcon(menu.getMenuIcon())
                    .menuHref(menu.getMenuHref())
                    .menuCode(menu.getMenuCode())
                    .menuWeight(menu.getMenuWeight())
                    .isShow(menu.getIsShow())
                    .menuLevel(menu.getMenuLevel()).build());
        }
        jsonObject.put("total",firstMenu.getTotal());
        jsonObject.put("page",firstMenu.getCurrent());
        jsonObject.put("page_size",firstMenu.getSize());
        jsonObject.put("menuList",listVoList);
        return ApiResponse.ofSuccess(jsonObject);
    }
}
