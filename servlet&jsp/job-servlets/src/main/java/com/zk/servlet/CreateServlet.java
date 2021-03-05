package com.zk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//@WebServlet(urlPatterns = "unused-create", loadOnStartup = 0)
public class CreateServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("正在创建数据库");
    }
}
