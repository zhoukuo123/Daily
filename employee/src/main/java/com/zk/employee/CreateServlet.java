package com.zk.employee;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("unchecked")
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String empno = req.getParameter("empno");
        String ename = req.getParameter("ename");
        String department = req.getParameter("department");
        String job = req.getParameter("job");
        String salary = req.getParameter("salary");
        Employee emp = new Employee(Integer.parseInt(empno), ename, department, job, Float.parseFloat(salary));
        ServletContext context = req.getServletContext();
        List<Employee> employees = (List<Employee>) context.getAttribute("employees");
        employees.add(emp);
        context.setAttribute("employees", employees);
        req.getRequestDispatcher("/employee.jsp").forward(req, resp);
    }
}
