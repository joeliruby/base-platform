package com.matariky.utils;

import java.io.File;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

public class IPUtil {
    public static String getCityInfo(String ip) {

        // db
        String dbPath = IPUtil.class.getResource("/ip2region.db").getPath();

        File file = new File(dbPath);
        if (file.exists() == false) {
            System.out.println("Error: Invalid ip2region.db file");
        }

        // Query algorithm
        int algorithm = DbSearcher.BTREE_ALGORITHM; // B-tree
        // DbSearcher.BINARY_ALGORITHM //Binary
        // DbSearcher.MEMORY_ALGORITYM //Memory
        try {
            org.lionsoul.ip2region.DbConfig config = new org.lionsoul.ip2region.DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);

            // define the method
            Method method = null;
            switch (algorithm) {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }

            DataBlock dataBlock = null;
            if (Util.isIpAddress(ip) == false) {
                System.out.println("Error: Invalid ip address");
            }

            if (method != null) {
                dataBlock = (DataBlock) method.invoke(searcher, ip);

                return dataBlock.getRegion();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        // X-Forwarded-For：Squid Service acting
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // Proxy-Client-IP：apache Service acting
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // WL-Proxy-Client-IP：weblogic Service acting
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // HTTP_CLIENT_IP：Some agents Server
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // X-Real-IP：nginx Service acting
            ipAddresses = request.getHeader("X-Real-IP");
        }

        // Some networks passing multi -layer agents, then there will be many IPs to
        // RETRIEVE. Generally, they are divided through comma (,), and the one IP is
        // the client’s
        // RealIP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        // Still can't getrieve, and finally pass it again request.getRemoteAddr();
        // Retrieve
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
