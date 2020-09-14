package org.top.thymeboot.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.thymeboot.common.utils.IPUtils;
import org.top.thymeboot.common.utils.UUIDUtils;
import org.top.thymeboot.system.mapper.SysLogMapper;
import org.top.thymeboot.system.model.SysLog;
import org.top.thymeboot.system.service.SysLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int saveLoginLog(HttpServletRequest request, String message, String name) {
        try{
            String ipAddress = IPUtils.getIpAddr(request);
            String browserName = IPUtils.getBrowser(request);
            String systemName = IPUtils.getSystemName(request);
            String ipSource = IPUtils.getipSource(ipAddress);
            SysLog sysLog = SysLog.builder()
                    .username(name)
                    .ipAddress(ipAddress)
                    .browserName(browserName)
                    .systemName(systemName)
                    .ipSource(ipSource)
                    .createDate(new Date())
                    .message(message)
                    .id(UUIDUtils.getUUID())
                    .build();
            return sysLogMapper.insert(sysLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return 0;
    }

    @Override
    public IPage<SysLog> findSysLogPage(Page page) {
        return sysLogMapper.findSysLogPage(page);
    }
}
