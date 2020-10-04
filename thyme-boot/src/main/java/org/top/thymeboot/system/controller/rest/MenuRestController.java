package org.top.thymeboot.system.controller.rest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.top.thymeboot.common.base.ApiResponse;
import org.top.thymeboot.common.utils.SecurityUtils;
import org.top.thymeboot.common.utils.UUIDUtils;
import org.top.thymeboot.system.model.SysMenu;
import org.top.thymeboot.system.service.SysMenuRoleService;
import org.top.thymeboot.system.service.SysMenuService;
import org.top.thymeboot.system.vo.*;

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

    @GetMapping("/deleteMenu")
    public ApiResponse deleteMenu(@RequestParam("id") String id){
        JSONObject jsonObject = new JSONObject();
        try{
            if(sysMenuService.deleteMenuById(id)>0){
                jsonObject.put("code",200);
            }
        }
        catch (Exception e){
            jsonObject.put("code",500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/updateMenu")
    public ApiResponse updateMenu(@RequestBody SysMenuNameVO menuVO){
        JSONObject jsonObject = new JSONObject();
        SysMenuVO sysMenu = new SysMenuNameVO();
        BeanUtils.copyProperties(menuVO, sysMenu);
        String menuName = sysMenuService.getByMenuName(menuVO.getMenuNames());
        sysMenu.setMenuName(menuName);
        if(sysMenuService.updateMenu(sysMenu) > 0){
            jsonObject.put("code",200);
        }
        else{
            jsonObject.put("code",500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/addMenu")
    public ApiResponse addMenu(@RequestBody SysMenuNameVO sysMenuNameVO){
        JSONObject jsonObject = new JSONObject();
        SysMenu sysMenu = sysMenuService.getByName(sysMenuNameVO.getMenuNames(), sysMenuNameVO.getMenuCode(), sysMenuNameVO.getMenuHref());
        if(sysMenu == null){
            Authentication authentication = SecurityUtils.getCurrentUserAuthentication();
            String id = UUIDUtils.getUUID();
            SysMenuVO menu = new SysMenuVO();
            BeanUtils.copyProperties(sysMenuNameVO,menu);
            menu.setId(id);
            menu.setCreateBy((String)authentication.getPrincipal());
            try{
                if(sysMenuService.addMenu(menu) > 0) {
                    jsonObject.put("code", 200);
                }
            }
            catch (Exception e){
                jsonObject.put("code",500);
            }
        }
        else{
            jsonObject.put("code",501);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/getMenuLevel")
    public ApiResponse getMenuLevel(){
        JSONObject jsonObject = new JSONObject();
        List<String> menuLevel = sysMenuService.getMenuLevel();
        jsonObject.put("menuLevel",menuLevel);
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/getPreviousMenu")
    public ApiResponse getPreviousMenu(@RequestParam("menuLevel") String menuLevel){
        JSONObject jsonObject = new JSONObject();
        List<MenuNameVO> previousMenu = sysMenuService.getPreviousMenu(String.valueOf((Integer.parseInt(menuLevel) - 1)));
        jsonObject.put("menuNames",previousMenu);
        return ApiResponse.ofSuccess(jsonObject);
    }

}
