package com.zk.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletcontext/init")
public class ServletContextInitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ServletContext 为Web应用程序全局对象
        ServletContext context = req.getServletContext();

        // 设置自定义属性
        context.setAttribute("copyright", "©2020 Baidu (京)-经营性-2017-0020 京公网安备11000002000001号 京ICP证030173号");
        context.setAttribute("title", "百度一下, 你就知道");
        resp.getWriter().println("init success");
    }
}
