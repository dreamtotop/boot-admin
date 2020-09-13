package org.top.thymeboot.system.vo;

import lombok.Builder;
import lombok.Data;
import org.top.thymeboot.system.model.SysMenu;

import java.util.List;

@Data
@Builder
public class MenuVO {

    private String name;

    private String icon;

    private String code;

    private List<SysMenu> sysMenus;
}
