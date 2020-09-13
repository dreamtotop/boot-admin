package org.top.thymeboot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import org.top.thymeboot.system.model.SysLog;

public interface SysLogMapper extends BaseMapper<SysLog> {

    @Select("select * from sys_log order by create_date desc")
    IPage<SysLog>  findSysLogPage(Page page);
}
