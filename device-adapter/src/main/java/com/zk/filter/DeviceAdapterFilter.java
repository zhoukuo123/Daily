package com.zk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设备适配过滤器
 */
//@WebFilter(filterName = "DeviceAdapterFilter", urlPatterns = "/*")
public class DeviceAdapterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        System.out.println("URI: " + uri);
        if (uri.startsWith("/desktop") || uri.startsWith("/mobile")) {
            chain.doFilter(request, response);
        } else {
            String userAgent = req.getHeader("user-agent");
            String targetURI = "";
            if (userAgent.contains("iPhone") || userAgent.contains("Android")) {
                targetURI = "/mobile" + uri;
                System.out.println("移动端设备正在访问, 重新跳转URI: " + targetURI);
                resp.sendRedirect(targetURI);
            } else {
                targetURI = "/desktop" + uri;
                System.out.println("PC端设备正在访问, 重新跳转URI: " + targetURI);
                resp.sendRedirect(targetURI);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
