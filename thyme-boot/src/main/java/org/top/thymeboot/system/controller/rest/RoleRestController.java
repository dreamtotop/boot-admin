package org.top.thymeboot.system.controller.rest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.top.thymeboot.common.base.ApiResponse;
import org.top.thymeboot.common.utils.UUIDUtils;
import org.top.thymeboot.system.model.SysMenu;
import org.top.thymeboot.system.model.SysMenuRole;
import org.top.thymeboot.system.model.SysRole;
import org.top.thymeboot.system.service.SysMenuRoleService;
import org.top.thymeboot.system.service.SysMenuService;
import org.top.thymeboot.system.service.SysRoleService;
import org.top.thymeboot.system.vo.MenuListVO;
import org.top.thymeboot.system.vo.RoleVO;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleRestController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysMenuRoleService menuRoleService;

    @GetMapping("/getRoleInfo")
    public ApiResponse getRoleInfo(@RequestParam("page") int page,
                                   @RequestParam("page_size") int pageSize){
        JSONObject jsonObject = new JSONObject();
        IPage<SysRole> sysRoleList = roleService.getAll(new Page(page, pageSize));
        jsonObject.put("total",sysRoleList.getTotal());
        jsonObject.put("page",sysRoleList.getCurrent());
        jsonObject.put("page_size",sysRoleList.getSize());
        jsonObject.put("sysRoleList",sysRoleList.getRecords());
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/delete")
    public ApiResponse deleteRole(@RequestParam("id") String id){
        JSONObject jsonObject = new JSONObject();
        try {
            roleService.deleteById(id);
            menuRoleService.deleteByRoleId(id);
            jsonObject.put("code",200);
        }
        catch (Exception e){
            jsonObject.put("code",500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/update")
    public ApiResponse updateRole(@RequestBody RoleVO roleVO){
        JSONObject jsonObject = new JSONObject();
        try {
            menuRoleService.deleteByRoleId(roleVO.getId());
            for (String menuId : roleVO.getIds()) {
                SysMenuRole sysMenuRole = new SysMenuRole(menuId, roleVO.getId());
                menuRoleService.addMenuRole(sysMenuRole);
            }
            SysRole sysRole = new SysRole();
            BeanUtils.copyProperties(roleVO, sysRole);
            roleService.updateById(sysRole);
            jsonObject.put("code",200);
        }
        catch (Exception e){
            jsonObject.put("code",500);
        }
        return  ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/addRole")
    public ApiResponse addRole(@RequestBody RoleVO roleVO) {
        JSONObject jsonObject = new JSONObject();
        try {
            SysRole role = roleService.getByName(roleVO.getName());
            if (role == null) {
                String id = UUIDUtils.getUUID();
                roleVO.setId(id);
                for (String menuId : roleVO.getIds()) {
                    SysMenuRole sysMenuRole = new SysMenuRole(menuId, roleVO.getId());
                    menuRoleService.addMenuRole(sysMenuRole);
                }
                SysRole sysRole = new SysRole();
                BeanUtils.copyProperties(roleVO, sysRole);
                roleService.insert(sysRole);
                jsonObject.put("code", 200);
            }
            else{
                // 角色已存在
                jsonObject.put("code", 501);
            }
        }
        catch (Exception e){
            jsonObject.put("code",500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/getData")
    public ApiResponse getData(){
        JSONObject jsonObject = new JSONObject();
        List<MenuListVO> listVoList = getMenu();
        jsonObject.put("menuList",listVoList);
        return ApiResponse.ofSuccess(jsonObject);
    }


    @GetMapping("/getRoleMenu")
    public ApiResponse getRoleMenu(@RequestParam("roleId")String roleId){
        JSONObject jsonObject = new JSONObject();
        List<MenuListVO> listVoList = getMenu();
        List<String> parentIds = menuService.getRoleMenu(roleId);
        List<String> roleMenuIds = menuRoleService.getAllMenuId(roleId, parentIds);
        jsonObject.put("ids", roleMenuIds);
        jsonObject.put("parentIds", parentIds);
        jsonObject.put("menuList",listVoList);
        return ApiResponse.ofSuccess(jsonObject);
    }

    private List<MenuListVO> getMenu(){
        List<MenuListVO> menuListVOList = new LinkedList<>();
        List<SysMenu> firstMenuList = menuService.getFirstMenu();
        //数据拼接
        for(SysMenu sysMenu : firstMenuList){
            List<SysMenu> secondMenuList = menuService.findByParentId(sysMenu.getId());
            menuListVOList.add(MenuListVO.builder()
            .id(sysMenu.getId())
            .children(secondMenuList)
            .isShow(sysMenu.getIsShow())
            .menuCode(sysMenu.getMenuCode())
            .menuHref(sysMenu.getMenuHref())
            .menuName(sysMenu.getMenuName())
            .menuIcon(sysMenu.getMenuIcon())
            .menuLevel(sysMenu.getMenuLevel())
            .menuWeight(sysMenu.getMenuWeight())
            .build());
        }
        return menuListVOList;
    }

}
