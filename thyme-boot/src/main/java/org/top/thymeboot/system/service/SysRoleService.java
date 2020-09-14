package org.top.thymeboot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.top.thymeboot.system.model.SysRole;

import java.util.List;

public interface SysRoleService {

    /**
     * 根据用户id查询对应的角色
     * @param userId
     * @return
     */
    SysRole findByUserId(@Param("userId") String userId);

    /**
     * 查询所有角色
     * @param page 分页数据
     * @return 所有角色集合
     */
    IPage<SysRole> getAll(Page page);

    /**
     * 根据名称查角色
     * @param name 名称
     * @return 角色
     */
    SysRole getByName(String name);

    /**
     * 根据id查角色名称
     * @param id id
     * @return 角色名称
     */
    String getById(String id);

    /**
     * 根据id删除角色
     * @param id id
     * @return 返回值
     */
    int deleteById(String id);

    /**
     * 根据id修改角色
     * @param sysRole 角色
     * @return 返回值
     */
    int updateById(SysRole sysRole);

    /**
     * 保存角色
     * @param sysRole 角色
     * @return 返回值
     */
    int insert(SysRole sysRole);

    /**
     * 获取所有的角色名称
     * @return 所有角色名称
     */
    List<String> getAllRoleName();

    /**
     * 根据角色名称查询角色id
     * @param name 角色名称
     * @return 角色id
     */
    String getIdByName(String name);
}
