package com.zk.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器的特性:
 * 过滤器对象在Web应用启动时被创建且全局唯一
 * 唯一的过滤器对象在并发环境中采用多线程提供服务即单例多线程
 */
public class MyFirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化成功");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器已生效");

        // 将请求对象和响应对象随着过滤链依次向后传递
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器已被销毁");
    }
}
