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
        String copyright = context.getInitParameter("copyright");
        String title = context.getInitParameter("title");
        // 设置自定义属性
        context.setAttribute("copyright", copyright);
        context.setAttribute("title", title);
        resp.getWriter().println("init success");
    }
}
