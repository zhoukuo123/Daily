package com.zk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//@WebServlet(urlPatterns = "unused-import", loadOnStartup = 0)
public class ImportServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("正在导入数据");
    }
}
