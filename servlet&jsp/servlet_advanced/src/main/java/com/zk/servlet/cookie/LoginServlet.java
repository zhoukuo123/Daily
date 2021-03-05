package com.zk.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookies/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("用户登陆成功");

        Cookie cookie = new Cookie("user", "admin");
        cookie.setMaxAge(60 * 60 * 24 * 7);
        resp.addCookie(cookie);
        resp.getWriter().println("login success");
    }
}
