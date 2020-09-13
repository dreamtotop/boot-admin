package org.top.thymeboot.system.vo;

import lombok.Data;
import org.top.thymeboot.system.model.SysUser;

@Data
public class UserVO extends SysUser {

    private String userRole;

    public UserVO() {

    }

    public UserVO(String userRole){
        this.userRole = userRole;
    }
}
