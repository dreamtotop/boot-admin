package org.top.thymeboot.system.service;

import org.top.thymeboot.system.model.SysMenuRole;

import java.util.List;

public interface SysMenuRoleService {


    /**
     * 添加角色和菜单的联系
     * @param sysMenuRole 角色和菜单的实例
     * @return 返回值
     */
    int addMenuRole(SysMenuRole sysMenuRole);

    /**
     * 根据角色id删除对应的角色和菜单的联系
     * @param roleId 角色id
     * @return 返回值
     */
    int deleteByRoleId(String roleId);

    /**
     * 根据角色id查询所有菜单id
     * @param roleId 角色id
     * @param parentIds 菜单id
     * @return 所有菜单id
     */
    List<String> getAllMenuId(String roleId, List<String> parentIds);
}
