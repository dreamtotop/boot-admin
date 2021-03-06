package org.top.thymeboot.common.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.UserAgent;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.top.thymeboot.common.base.Constants;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtils {

    /**
     * 获取客户端IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (Constants.LOCAL_HOST.equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }

        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = Constants.LOCAL_HOST;
        }

        return ip;
    }


    /**
     * 获取客户端主机名称
     */
    public static String getHostName(){

        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            return hostName;
        } catch (UnknownHostException e) {

        }
        return "unknown";
    }

    /**
     * 获取浏览器名称
     */
    public static String getBrowser(HttpServletRequest request){
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

    /**
     * 获取系统名称
     */
    public static String getSystemName(HttpServletRequest request){
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        return userAgent.getOperatingSystem().getName();
    }


    /**
     * 获取ip地址
     */
    public static String getipSource(String ip) throws Exception{
        DbConfig config = new DbConfig();
        String path = "config/ip2region.db";
        String name = "ip2region.db";
        File file = FileUtils.inputStreamToFile(new ClassPathResource(path).getStream(), name);
        DbSearcher searcher = new DbSearcher(config, file.getPath());
        DataBlock dataBlock = searcher.btreeSearch(ip);
        String address = dataBlock.getRegion().replace("0|","");
        if(address.charAt(address.length()-1) == '|'){
            address = address.substring(0,address.length() - 1);
        }
        return address.equals(Constants.REGION) ? Constants.INTRANET_IP : address;
    }

}
