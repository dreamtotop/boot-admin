package org.top.thymeboot.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.thymeboot.system.mapper.SysUserRoleMapper;
import org.top.thymeboot.system.model.SysUserRole;
import org.top.thymeboot.system.service.SysUserRoleService;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int insert(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public int deleteByUserId(String userId) {
        return sysUserRoleMapper.deleteByUserId(userId);
    }
}
