package org.top.thymeboot.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public int deleteById(String id) {
        return sysUserMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public int updateById(SysUser sysUser) {
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public int insert(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public int updatePasswordById(String password, String id) {
        return sysUserMapper.updatePasswordById(password,id);
    }
}
