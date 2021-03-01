package com.zk.mgallery.controller;

import com.zk.mgallery.service.PaintingService;
import com.zk.mgallery.utils.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台管理功能Controller
 */
@WebServlet("/management")
public class ManagementController extends HttpServlet {
    private PaintingService paintingService = new PaintingService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String method = request.getParameter("method");
        if (method.equals("list")) {
            list(request, response);
        } else if (method.equals("delete")) {
            
        } else if (method.equals("show_create")) {
            showCreatePage(request, response);          
        } else if (method.equals("create")) {
            create(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p = request.getParameter("p");
        String r = request.getParameter("r");
        if (p == null) {
            p = "1";
        }
        if (r == null) {
            r = "6";
        }
        PageModel pageModel = paintingService.pagination(Integer.parseInt(p), Integer.parseInt(r));
        request.setAttribute("pageModel", pageModel);
        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
    }

    // 显示新增页面
    private void showCreatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(request, response);
    }

    // 新增油画方法
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 文件上传时的数据处理与标准表单完全不同
        String pname = request.getParameter("pname");
        System.out.println(pname);

//        request.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(request, response);
    }
}
