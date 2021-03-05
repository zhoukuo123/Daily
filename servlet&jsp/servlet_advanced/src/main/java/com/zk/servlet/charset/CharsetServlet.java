package com.zk.servlet.charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/charset/process")
public class CharsetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 对于Tomcat8及以上的版本, 默认get请求发送中文就是UTF-8的格式, 因此无需转换
        String ename = req.getParameter("ename");
        String address = req.getParameter("address");
        System.out.println(ename + ":" + address);
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println(ename + ":" + address);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // req.setCharacterEncoding方法用于将 请求体 中的字符集转换为UTF-8
        req.setCharacterEncoding("UTF-8");

        String ename = req.getParameter("ename");
        String address = req.getParameter("address");
        System.out.println(ename + ":" + address);

//        String utf8Ename = new String(ename.getBytes("iso-8859-1"),  "utf-8");
//        String utf8Address = new String(address.getBytes("iso-8859-1"),  "utf-8");
//        System.out.println(utf8Ename + ":" + utf8Address);
    }
}
