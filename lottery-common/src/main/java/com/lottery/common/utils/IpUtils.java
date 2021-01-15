package com.lottery.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: wyg
 * @Date: 2018/4/4 上午11:07
 * @Description:
 */
public class IpUtils {

    private static final long MAX = (1L << 32) - 1;

    private static final String UNKNOWN = "unknown";

    private static final String LOCAL_IPV4 = "127.0.0.1";

    private static final String LOCAL_IPV6 = "0:0:0:0:0:0:0:1";

    /**
     * 获取当前网络ip
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Original-Forwarded-For");

        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (LOCAL_IPV4.equals(ip) || LOCAL_IPV6.equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress ret = null;
                try {
                    ret = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if (ret != null) {
                    ip = ret.getHostAddress();
                }
            }
        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (StringUtils.isNotBlank(ip) && ip.contains(",")) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }
        // 如果都没有按照网关的x-real-ip取
        if (StringUtils.isBlank(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        return ip;
    }

    /**
     * 将ip转换为long
     *
     * @param strIp
     * @return
     */
    public static long ipToLong(String strIp) {
        long[] ip = new long[4];
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3 + 1));
        return toUint((ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3]);
    }

    /**
     * 转化成C语言 uint 类型
     *
     * @param value
     * @return uint
     */
    private static long toUint(long value) {
        return value & MAX;
    }

}
