package com.zk.filter;

import javax.servlet.*;
import java.io.IOException;

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
