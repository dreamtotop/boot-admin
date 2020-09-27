package org.top.thymeboot.system.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class SysMenuMapperTest {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Test
    void getRoleMenu() {
        List<String> menu = sysMenuMapper.getRoleMenu("811d784a392ad816");
        System.out.println(menu);
    }
}