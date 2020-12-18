package com.zk.servlet.direct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/direct/check")
public class CheckLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("用户登陆成功");

        // 实现了请求转发的功能
//        req.getRequestDispatcher("/direct/index").forward(req, resp);

        // 响应重定向需要增加contextPath
        resp.sendRedirect("/servlet_advanced/direct/index");
    }
}
