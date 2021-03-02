package com.zk.mgallery.controller;

import com.zk.mgallery.entity.Painting;
import com.zk.mgallery.service.PaintingService;
import com.zk.mgallery.utils.PageModel;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
        } else if (method.equals("show_update")) {
            showUpdatePage(request, response);
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
//        String pname = request.getParameter("pname");
//        System.out.println(pname);

        // 1. 初始化FileUpload组件
        /**
         * FileItemFactory 用于将前端表单的数据转换为一个个FileItem对象
         * ServletFileUpload 则是为FileUpload组件提供Java web的Http请求解析
         */
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sf = new ServletFileUpload(factory);
        // 2. 遍历所有FileItem
        try {
            List<FileItem> formData = sf.parseRequest(request);
            Painting painting = new Painting();
            for (FileItem fi : formData) {
                if (fi.isFormField()) {
                    System.out.println("普通输入项:" + fi.getFieldName() + ":" + fi.getString("UTF-8"));
                    switch (fi.getFieldName()) {
                        case "pname":
                            painting.setPname(fi.getString("UTF-8"));
                            break;
                        case "category":
                            painting.setCategory(Integer.parseInt(fi.getString("UTF-8")));
                            break;
                        case "price":
                            painting.setPrice(Integer.parseInt(fi.getString("UTF-8")));
                            break;
                        case "description":
                            painting.setDescription((fi.getString("UTF-8")));
                            break;
                    }
                } else {
                    // 3. 文件保存到服务器目录
                    String path = request.getServletContext().getRealPath("/upload");
                    String fileName = UUID.randomUUID().toString();
                    // fi.getName()得到原始文件名, 截取最后一个.后所有字符串, 得到扩展名 wxml.jpg -> .jpg
                    String suffix = fi.getName().substring(fi.getName().lastIndexOf("."));

                    // fi.write()写入目标文件
                    fi.write(new File(path, fileName + suffix));
                    painting.setPreview("/upload/" + fileName + suffix);
                }
            }
            paintingService.create(painting); // 新增功能
            response.sendRedirect("/management?method=list"); // 返回列表页
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示更新页面
     */
    private void showUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Painting painting = paintingService.findById(Integer.parseInt(id));
        request.setAttribute("painting", painting);
        request.getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(request, response);
    }
}
