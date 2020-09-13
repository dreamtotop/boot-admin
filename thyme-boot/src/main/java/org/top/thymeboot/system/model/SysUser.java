package org.top.thymeboot.system.model;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class SysUser implements Serializable {

    private static final long serialVersionUID = 5387480199703884068L;

    private String id;

    private String name;

    private String password;

    private String nickName;

    private String sex;

    private String mobile;

    private String email;

    private String birthday;

    private String hobby;

    private String liveAddress;

    private Date createTime;

    private Date updateTime;
}
