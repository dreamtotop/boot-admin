package org.top.thymeboot.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.thymeboot.system.mapper.SysRoleMapper;
import org.top.thymeboot.system.model.SysRole;
import org.top.thymeboot.system.service.SysRoleService;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole findByUserId(String userId) {
        return sysRoleMapper.findByUserId(userId);
    }

    @Override
    public IPage<SysRole> getAll(Page page) {
        return sysRoleMapper.getAll(page);
    }

    @Override
    public SysRole getByName(String name) {
        return sysRoleMapper.getByName(name);
    }

    @Override
    public String getById(String id) {
        return sysRoleMapper.getById(id);
    }

    @Override
    public int deleteById(String id) {
        return sysRoleMapper.deleteById(id);
    }

    @Override
    public int updateById(SysRole sysRole) {
        return sysRoleMapper.updateById(sysRole);
    }

    @Override
    public int insert(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public List<String> getAllRoleName() {
        return sysRoleMapper.getAllRoleName();
    }

    @Override
    public String getIdByName(String name) {
        return sysRoleMapper.getIdByName(name);
    }
}
