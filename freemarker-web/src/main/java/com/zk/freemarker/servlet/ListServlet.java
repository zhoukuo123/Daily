package com.zk.freemarker.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(7731, "张三", "市场部", "客户代表", 8000f));
        list.add(new Employee(8871, "李四", "研发部", "运维工程师", 7000f));
        req.setAttribute("employee_list", list);
        req.getRequestDispatcher("/employee.ftl").forward(req, resp);
    }
}
