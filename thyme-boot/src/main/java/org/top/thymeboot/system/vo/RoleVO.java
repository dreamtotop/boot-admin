package org.top.thymeboot.system.vo;

import lombok.Data;
import org.top.thymeboot.system.model.SysRole;

import java.io.Serializable;

@Data
public class RoleVO extends SysRole implements Serializable {

    private String[] ids;
}
