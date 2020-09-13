package org.top.thymeboot.system.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuNameVO implements Serializable {

    private static final long serialVersionUID = -7972509589274741286L;

    private String id;

    private String menuName;
}
