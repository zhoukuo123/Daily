package com.zk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "MyAnnotationFilter", urlPatterns = "/*")
public class MyAnnotationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("注解形式过滤器已生效");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
