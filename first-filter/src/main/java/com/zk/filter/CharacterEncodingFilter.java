package com.zk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 字符集过滤器
 */
@WebFilter(filterName = "characterEncodingFilter", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8"),
                @WebInitParam(name = "p1", value = "v1"),
                @WebInitParam(name = "p2", value = "v2")
        })
public class CharacterEncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        System.out.println("encoding: " + encoding);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        // 解决post请求中的中文乱码问题
        req.setCharacterEncoding(encoding);
        HttpServletResponse resp = (HttpServletResponse) response;
        // 解决响应中的中文乱码问题
        resp.setContentType("text/html; charset=" + encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
