package org.top.thymeboot.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.thymeboot.system.mapper.SysMenuMapper;
import org.top.thymeboot.system.mapper.SysRoleMapper;
import org.top.thymeboot.system.model.SysMenu;
import org.top.thymeboot.system.model.SysRole;
import org.top.thymeboot.system.service.SysMenuService;
import org.top.thymeboot.system.vo.MenuNameVO;
import org.top.thymeboot.system.vo.MenuVO;
import org.top.thymeboot.system.vo.SysMenuVO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<MenuVO> getMenuByUser(String username) {
        SysRole role = sysRoleMapper.findByName(username);
        List<SysMenu> sysMenus = sysMenuMapper.findByRoleId(role.getId());
        List<MenuVO> menuVoList = new ArrayList<>();
        //取出一级菜单
        List<SysMenu> firstMenus = sysMenus.stream().filter(sysMenu -> sysMenu.getParentId() == null).collect(Collectors.toList());
        //拼接二级菜单
        for(SysMenu sysMenu : firstMenus){
            List<SysMenu> secondMenus = new LinkedList<>();
            for(SysMenu menu : sysMenus){
                if(StringUtils.equals(menu.getParentId(),sysMenu.getId())){
                    secondMenus.add(menu);
                }
            }
            menuVoList.add(MenuVO.builder()
                    .name(sysMenu.getMenuName())
                    .code(sysMenu.getMenuCode())
                    .icon(sysMenu.getMenuIcon())
                    .sysMenus(secondMenus)
                    .build());
        }
        return menuVoList;
    }

    @Override
    public List<SysMenu> findMenuListByUser(String username) {
        //获取用户对应角色
        SysRole role = sysRoleMapper.findByName(username);
        return sysMenuMapper.findByRoleId(role.getId());
    }

    @Override
    public IPage<SysMenu> findFirstMenu(Page page) {
        return sysMenuMapper.findFirstMenu(page);
    }

    @Override
    public List<SysMenu> findByParentId(String parentId) {
        return sysMenuMapper.findByParentId(parentId);
    }

    @Override
    public int updateMenu(SysMenuVO sysMenu) {
        return sysMenuMapper.updateMenu(sysMenu);
    }

    @Override
    public int addMenu(SysMenuVO sysMenu) {
        return sysMenuMapper.addMenu(sysMenu);
    }

    @Override
    public SysMenu getByName(String menuName, String menuCode, String menuHref) {
        return sysMenuMapper.getByName(menuName,menuCode,menuHref);
    }

    @Override
    public SysMenu getById(String id) {
        return sysMenuMapper.getById(id);
    }

    @Override
    public List<SysMenu> getFirstMenu() {
        return sysMenuMapper.getFirstMenu();
    }

    @Override
    public List<SysMenu> getSecondMenu() {
        return sysMenuMapper.getSecondMenu();
    }

    @Override
    public List<String> getRoleMenu(String roleId) {
        return sysMenuMapper.getRoleMenu(roleId);
    }

    @Override
    public List<String> getMenuLevel() {
        return sysMenuMapper.getMenuLevel();
    }

    @Override
    public List<MenuNameVO> getPreviousMenu(String menuLevel) {
        return sysMenuMapper.getPreviousMenu(menuLevel);
    }

    @Override
    public String getByMenuName(String menuNames) {
        return sysMenuMapper.getByMenuName(menuNames);
    }

    @Override
    public int deleteMenuById(String id) {
        return sysMenuMapper.deleteMenuById(id);
    }
}
