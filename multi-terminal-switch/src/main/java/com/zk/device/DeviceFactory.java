package com.zk.device;

import javax.servlet.http.HttpServletRequest;

public class DeviceFactory {
    public static Device getDevice(HttpServletRequest req) {
        String userAgent = req.getHeader("user-agent");
        System.out.println(userAgent);
        if (userAgent.contains("Windows NT") || userAgent.contains("Linux x86_64")) {
            return new DesktopDevice();
        } else if (userAgent.contains("iPhone") || userAgent.contains("Android")) {
            return new MobileDevice();
        } else {
            return null;
        }
    }
}
