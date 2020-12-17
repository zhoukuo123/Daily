package com.zk.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接受请求发来的参数
        String name = req.getParameter("name");
        String html = "<h1 style='color:red'>h1," + name + "!</h1><hr/>";
        System.out.println("返回给浏览器的响应数据为: " + html);
        PrintWriter out = resp.getWriter();
        out.println(html); // 将html发送回浏览器
    }
}
