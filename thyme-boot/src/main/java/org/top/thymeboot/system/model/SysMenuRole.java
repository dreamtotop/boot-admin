package org.top.thymeboot.system.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class SysMenuRole implements Serializable {

    private static final long serialVersionUID = -6849170400827755247L;

    private String menuId;

    private String roleId;

    public SysMenuRole(String menuId, String roleId){
        this.menuId = menuId;
        this.roleId = roleId;
    }

    public SysMenuRole(String menuId){
        this.menuId = menuId;
    }
}
