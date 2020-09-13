package org.top.thymeboot.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysMenuVO implements Serializable {

    private static final long serialVersionUID = -5466903607818680157L;

    /**
     * 菜单主键
     */
    public String id;

    /**
     * 菜单父级
     */
    public String parentId;

    /**
     * 菜单名称
     */
    public String menuName;

    /**
     * 菜单别名
     */
    public String menuCode;

    /**
     * 菜单链接
     */
    public String menuHref;

    /**
     * 菜单图标
     */
    public String menuIcon;

    /**
     * 菜单级别
     */
    public String menuLevel;

    /**
     * 菜单权重
     */
    public String menuWeight;

    /**
     * 菜单是否显示
     */
    public String isShow;

    /**
     * 菜单创建时间
     */
    public Date createDate;

    /**
     * 菜单创建人
     */
    public String createBy;
}
