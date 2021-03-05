package com.zk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// 使用注解在过滤链中是按照类名进行排序, 不推荐
@WebFilter(filterName = "FilterA", urlPatterns = "/*")
public class FilterA implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("I'm Filter A");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
