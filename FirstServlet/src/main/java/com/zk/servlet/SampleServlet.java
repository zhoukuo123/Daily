package com.zk.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SampleServlet extends HttpServlet {
    // service是请求处理的核心方法, 无论是get或者post都会被service()方法处理
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();

        String name = req.getParameter("name");
        String mobile = req.getParameter("mobile");
        String sex = req.getParameter("sex");
        String[] specs = req.getParameterValues("spec");

        PrintWriter out = resp.getWriter(); // 向浏览器输出的数据流

        out.println("<h1>method:" + method + "</h1>");
        out.println("<h1>name:" + name + "</h1>");
        out.println("<h1>mobile:" + mobile + "</h1>");
        out.println("<h1>sex:" + sex + "</h1>");
        for (String spec : specs) {
            out.println("<h2>spec:" + spec + "</h2>");
        }
    }
}
