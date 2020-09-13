package org.top.thymeboot.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 65637828598626318L;

    /**
     * 菜单主键
     */
    private String id;

    /**
     * 菜单父级
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单别名
     */
    private String menuCode;

    /**
     * 菜单链接
     */
    private String menuHref;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单级别
     */
    private String menuLevel;

    /**
     * 菜单权重
     */
    private int menuWeight;

    /**
     * 菜单是否显示
     */
    private Boolean isShow;

    /**
     * 菜单创建时间
     */
    private Date createDate;

    /**
     * 菜单创建人
     */
    private String createBy;
}
