package org.top.thymeboot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.top.thymeboot.system.model.SysUserRole;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 根据用户id删除用户和角色的联系
     * @param userId 用户id
     * @return 返回值
     */
    @Delete("delete from sys_user_role where user_id = #{userId}")
    int deleteByUserId(String userId);
}
