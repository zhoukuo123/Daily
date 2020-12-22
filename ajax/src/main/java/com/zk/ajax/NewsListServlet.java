package com.zk.ajax;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/news_list")
public class NewsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<News> list = new ArrayList<>();
        list.add(new News("TIOBE:2018年5月份全球编程语言排行榜", "2018-5-1", "TIOBE", "..."));
        list.add(new News("TIOBE:2018年6月份全球编程语言排行榜", "2018-6-1", "TIOBE", "..."));
        list.add(new News("TIOBE:2018年7月份全球编程语言排行榜", "2018-7-1", "TIOBE", "..."));
        list.add(new News("TIOBE:2018年8月份全球编程语言排行榜", "2018-8-1", "TIOBE", "..."));
        String json = JSON.toJSONString(list);
        System.out.println(json);
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(json);
    }
}
