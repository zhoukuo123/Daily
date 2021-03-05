package com.zk.el;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/info")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacher = req.getParameter("teacher");
        Student stu = new Student();
        stu.setName("子墨");
        stu.setMobile(null);
        String grade = "A";
        req.setAttribute("grade", "B");
        req.getServletContext().setAttribute("grade", "C");
        HttpSession session = req.getSession();
        session.setAttribute("student", stu);
        session.setAttribute("grade", grade);

//        req.setAttribute("student", stu);
//        req.setAttribute("grade", grade);
        req.getRequestDispatcher("/el_info.jsp").forward(req, resp);
    }
}
