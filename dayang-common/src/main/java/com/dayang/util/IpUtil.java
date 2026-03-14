package com.dayang.util;


import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author zhuxi
 * @apiNote IP工具类
 */

public class IpUtil {

    private static final String UNKNOWN = "unknown";



    public static String getIpAddress(HttpServletRequest request){
        if (request == null){
            return UNKNOWN;
        }

        String ip = request.getHeader("X-Forwarded-For");

        if (StrUtil.isBlank(ip) || UNKNOWN.equals(ip)){
            ip = request.getHeader("X-Real-IP");
        }

        if (StrUtil.isBlank(ip) || UNKNOWN.equals(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || UNKNOWN.equals(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || UNKNOWN.equals(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StrUtil.isBlank(ip) || UNKNOWN.equals(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        // 上述都没有，则证明未通过nginx， 直接从java后端中获取
        if (StrUtil.isBlank(ip) || UNKNOWN.equals(ip)){
            ip = request.getRemoteAddr();
        }

        // 多级反向代理情况下
        // 获取第一个非unknown的ip 才是真正的Ip
        if (StrUtil.isNotBlank(ip) && ip.contains( ",")){
            String[] ips = ip.split(",");
            for (String strIp : ips){
                if (StrUtil.isNotBlank(strIp) && !UNKNOWN.equalsIgnoreCase(strIp.trim())){
                    ip = strIp.trim();
                    break;
                }
            }
        }

        // 转换为127.0.0.1，方便看日志，方便调试
        // TODO： 上线后 理应去除下述逻辑
        if ("0:0:0:0:0:0:0:1".equals(ip) || "::1".equals( ip)){
            ip = "127.0.0.1";
        }

        return ip;
    }
}
