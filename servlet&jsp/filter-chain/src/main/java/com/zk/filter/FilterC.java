package com.zk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "FilterC", urlPatterns = "/*")
public class FilterC implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("I'm Filter C");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
