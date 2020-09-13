package org.top.thymeboot.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysLog implements Serializable {

    private static final long serialVersionUID = -547654203498651895L;


    /**
     * 主键id
     */
    private String id;

    /**
     * 关联用户
     */
    private String username;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * ip来源
     */
    private String ipSource;

    /**
     * 日志信息
     */
    private String message;

    /**
     * 浏览器名称
     */
    private String browserName;

    /**
     * 设备名称
     */
    private String systemName;

    /**
     * 创建时间
     */
    private Date createDate;
}
