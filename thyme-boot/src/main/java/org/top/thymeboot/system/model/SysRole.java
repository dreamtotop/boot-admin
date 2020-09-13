package org.top.thymeboot.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole implements Serializable {

    private static final long serialVersionUID = -3102485717739903039L;

    private String id;

    private String authority;

    private String name;

    private Date createTime;
}
