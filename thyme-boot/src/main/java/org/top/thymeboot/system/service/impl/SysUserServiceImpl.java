package org.top.thymeboot.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.top.thymeboot.system.mapper.SysUserMapper;
import org.top.thymeboot.system.model.SysUser;
import org.top.thymeboot.system.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser findByName(String name) {
        return sysUserMapper.findByName(name);
    }

    @Override
    public IPage<SysUser> getAll(Page page) {
        return sysUserMapper.getAll(page);
    }

    @Override
    public SysUser getById(String id) {
        return sysUserMapper.getById(id);
    }

    @Override
    public int deleteById(String id) {
        return sysUserMapper.deleteById(id);
    }

    @Override
    public int updateById(SysUser sysUser) {
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public int insert(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public int updatePasswordById(String password, String id) {
        return sysUserMapper.updatePasswordById(password,id);
    }
}
