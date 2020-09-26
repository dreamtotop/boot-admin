package org.top.thymeboot.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.top.thymeboot.system.mapper.SysMenuRoleMapper;
import org.top.thymeboot.system.model.SysMenuRole;
import org.top.thymeboot.system.service.SysMenuRoleService;

import java.util.List;
@Service
public class SysMenuRoleServiceImpl implements SysMenuRoleService {

    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;

    @Override
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public int addMenuRole(SysMenuRole sysMenuRole) {
        return sysMenuRoleMapper.addMenuRole(sysMenuRole);
    }

    @Override
    public int deleteByRoleId(String roleId) {
        return sysMenuRoleMapper.deleteByRoleId(roleId);
    }

    @Override
    public List<String> getAllMenuId(String roleId, List<String> parentIds) {
        return sysMenuRoleMapper.getAllMenuId(roleId,parentIds);
    }
}
