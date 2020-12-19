package com.zk.jstl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jstl")
public class JstlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("score", 78);
        req.setAttribute("grade", "B");

        List<Company> list = new ArrayList<>();
        list.add(new Company("腾讯", "www.tencent.com"));
        list.add(new Company("百度", "www.baidu.com"));
        list.add(new Company("hao123", "www.hao123.com"));

        req.setAttribute("companies", list);

        req.getRequestDispatcher("/core.jsp").forward(req, resp);
    }
}
