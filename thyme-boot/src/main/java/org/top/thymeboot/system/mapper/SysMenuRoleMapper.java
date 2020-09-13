package org.top.thymeboot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.top.thymeboot.system.model.SysMenuRole;

import java.util.List;

public interface SysMenuRoleMapper extends BaseMapper<SysMenuRole> {

    /**
     * 添加角色和菜单的联系
     * @param sysMenuRole 角色和菜单的实例
     * @return 返回值
     */
    @Insert("insert into sys_menu_role(menu_id,role_id) values(#{menuId},#{roleId}")
    int addMenuRole(SysMenuRole sysMenuRole);


    /**
     * 根据角色id删除对应的角色和菜单的联系
     * @param roleId 角色id
     * @return 返回值
     */
    @Delete("delete from sys_menu_role where role_id = #{role_id}")
    int deleteByRoleId(String roleId);


    /**
     * 根据角色id查询所有菜单id
     * @param roleId 角色id
     * @param parentIds 菜单id
     * @return 所有菜单id
     */
    List<String> getAllMenuId(@Param("roleId")String roleId, @Param("parentIds")List<String> parentIds);
}
