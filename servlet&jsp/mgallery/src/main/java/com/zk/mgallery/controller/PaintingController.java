package com.zk.mgallery.controller;

import com.zk.mgallery.service.PaintingService;
import com.zk.mgallery.utils.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page")
public class PaintingController extends HttpServlet {
    private PaintingService paintingService = new PaintingService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收Http数据
        String page = request.getParameter("p"); // 页号
        String rows = request.getParameter("r"); // 每页记录数
        String category = request.getParameter("c"); // 类型
        if (page == null) { // page设置默认值
            page = "1";
        }
        if (rows == null) { // rows设置默认值
            rows = "6";
        }

        // 2. 调用Service方法, 得到处理结果
        PageModel pageModel = paintingService.pagination(Integer.parseInt(page), Integer.parseInt(rows), category);
        request.setAttribute("pageModel", pageModel);

        // 3. 请求转发至对应JSP(view)进行数据展现
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }
}
