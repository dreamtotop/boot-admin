package org.top.thymeboot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.top.thymeboot.system.model.SysLog;

import javax.servlet.http.HttpServletRequest;

public interface SysLogService {

    int saveLoginLog(HttpServletRequest request, String message, String name);

    IPage<SysLog> findSysLogPage(Page page);
}
