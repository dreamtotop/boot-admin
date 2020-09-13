package org.top.thymeboot.system.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 6404136730212315703L;

    private String userId;

    private String roleId;


    public SysUserRole(){

    }

    public SysUserRole(String userId, String roleId){
        this.userId = userId;
        this.roleId = roleId;
    }
}
