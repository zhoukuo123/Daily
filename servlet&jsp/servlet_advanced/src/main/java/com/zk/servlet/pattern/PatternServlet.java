package com.zk.servlet.pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/pattern/*")
public class PatternServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询员工的基本信息
        // 获取当前访问的URL
        String url = req.getRequestURL().toString();
        System.out.println(url);

        String id = url.substring(url.lastIndexOf("/") + 1);
        int eid = Integer.parseInt(id);

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println(id);
        if (eid == 1) {
            out.println("张三");
        } else if (eid == 2) {
            out.println("李四");
        } else {
            out.println("其他员工");
        }
    }
}
