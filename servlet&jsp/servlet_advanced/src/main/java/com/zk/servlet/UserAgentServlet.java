package com.zk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ua")
public class UserAgentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userAgent = req.getHeader("User-Agent");

        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println(userAgent);
        String output  = "";
        if (userAgent.indexOf("Windows NT") != -1) {
            output = "<h1>这是PC Windows端首页</h1>";
        } else if (userAgent.indexOf("iPhone") != -1 || userAgent.indexOf("Android") != -1) {
            output = "<h1>这是移动端首页</h1>";
        } else if (userAgent.indexOf("Linux x86_64") != -1) {
            output = "<h1>这是PC Linux端首页</h1>";
        }
        resp.getWriter().println(output);
    }
}
