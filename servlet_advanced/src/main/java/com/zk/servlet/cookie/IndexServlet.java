package com.zk.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookies/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // req.getCookies() 用于获取所有的Cookie信息
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            resp.getWriter().println("user not login");
            return;
        }
        String user = null;
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + ":" + cookie.getValue());
            if (cookie.getName().equals("user")) {
                user = cookie.getValue();
                break;
            }
        }

        if (user == null) {
            resp.getWriter().println("user not login");
        } else {
            resp.getWriter().println("user:" + user);
        }
    }
}
