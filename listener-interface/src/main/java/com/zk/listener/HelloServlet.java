package com.zk.listener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Hello World!");
        req.getServletContext().setAttribute("sc-attr1", "sc-attr-value1");
        req.getSession().setAttribute("session-attr1", "session-attr-value1");
        req.setAttribute("request-attr1", "request-attr-value1");

    }
}
