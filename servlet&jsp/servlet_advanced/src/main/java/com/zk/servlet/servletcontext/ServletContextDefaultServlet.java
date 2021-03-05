package com.zk.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletcontext/default")
public class ServletContextDefaultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        String copyright = (String) context.getAttribute("copyright");
        String title = (String) context.getAttribute("title");
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println("<h1>" + title + "</h1>" + copyright);
    }
}
