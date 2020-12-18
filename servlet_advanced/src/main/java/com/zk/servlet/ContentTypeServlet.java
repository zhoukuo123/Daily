package com.zk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ct")
public class ContentTypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String output = "<h1><a href='http://www.baidu.com'><span>百度</span></a></h1>";
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println(output);
    }
}
