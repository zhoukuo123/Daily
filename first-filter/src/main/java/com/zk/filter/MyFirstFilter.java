package com.zk.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器已生效");

        // 将请求对象和响应对象随着过滤链依次向后传递
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
