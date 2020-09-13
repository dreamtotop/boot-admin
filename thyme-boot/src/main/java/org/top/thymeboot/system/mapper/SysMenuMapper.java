package org.top.thymeboot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.top.thymeboot.system.model.SysMenu;
import org.top.thymeboot.system.vo.MenuNameVO;
import org.top.thymeboot.system.vo.SysMenuVO;

import java.util.List;
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据id删除菜单(若为一级菜单id删除其子菜单)
     * @param id id
     * @return 返回值
     */
    @Delete("delete from sys_menu where id = #{id} or parent_id = #{id}")
    int deleteMenuById(String id);


    /**
     * 根据角色id查询权限集合
     * @param roleId
     * @return
     */
    //@Select("SELECT *  FROM sys_menu as m LEFT JOIN sys_menu_role as r ON m.id = r.menu_id WHERE m.is_show = '1' and r.role_id = #{roleId} ORDER BY m.menu_weight")
    List<SysMenu> findByRoleId(@Param("roleId") String roleId);

    /**
     * 分页查询所有的一级菜单
     * @param page
     * @return
     */
    @Select("select * from sys_menu where menu_level = 1 order by menu_weight")
    IPage<SysMenu> findFirstMenu(Page page);

    /**
     *  根据父菜单id查询菜单集合
     * @param parentId
     * @return
     */
    @Select("select * from sys_menu where parent_id = #{parentId} order by menu_weight")
    List<SysMenu> findByParentId(@Param("parentId") String parentId);


    /**
     * 修改菜单
     * @param sysMenu
     * @return
     */
    int updateMenu(SysMenuVO sysMenu);


    /**
     * 添加菜单
     * @param sysMenu
     * @return
     */
    int addMenu(SysMenuVO sysMenu);

    /**
     * 查询菜单
     * @param menuName
     * @param menuCode
     * @param menuHref
     * @return
     */
    @Select({"<script>select * from sys_menu where menu_name = #{menuName} or menu_code = #{menuCode} " +
            "<when test=\"menuHref != null and menuHref != ''\"> or menu_href = #{menuHref}</when></script>"})
    SysMenu getByName(@Param("menuName")String menuName, @Param("menuCode")String menuCode, @Param("menuHref")String menuHref);

    /**
     * 根据id查询菜单
     * @param id id
     * @return 菜单
     */
    @Select("select * from sys_menu where id = #{id}")
    SysMenu getById(@Param("id")String id);

    /**
     * 获取一级菜单
     * @return 一级菜单
     */
    @Select("SELECT * FROM sys_menu WHERE menu_level = 1 ORDER BY menu_weight")
    List<SysMenu> getFirstMenu();

    /**
     * 获取二级菜单
     */
    @Select("SELECT * FROM sys_menu WHERE menu_level = 2")
    List<SysMenu> getSecondMenu();

    /**
     * 根据角色id查询所有父级菜单id
     * @param roleId 角色id
     * @return 父级菜单id
     */
//    @Select("SELECT id FROM sys_menu sm WHERE sm.menu_level = 1 and id in " +
//            "(select mr.menu_id from sys_menu_role mr left join sys_menu m on mr.menu_id = m.id where mr.role_id = #{roleId})" +
//            " ORDER BY menu_weight")
    List<String> getRoleMenu(@Param("roleId")String roleId);


    /**
     * 获取菜单层级
     * @return 菜单登记
     */
    @Select("select distinct menu_level from sys_menu order by menu_level asc")
    List<String> getMenuLevel();

    /**
     * 查询当前菜单的上级菜单
     * @param menuLevel 上级菜单层级
     * @return 上级菜单名称
     */
    @Select("select id, menu_name from sys_menu where menu_level = #{menuLevel} order by create_date")
    List<MenuNameVO> getPreviousMenu(@Param("menuLevel")String menuLevel);

    /**
     * 根据菜单名称查询菜单id
     * @param menuNames 菜单名称
     * @return 菜单id
     */
    @Select("select id from sys_menu where menu_name = #{menuNames}")
    String getByMenuName(@Param("menuNames")String menuNames);


}
