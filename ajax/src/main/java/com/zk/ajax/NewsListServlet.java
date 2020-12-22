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
        String type = req.getParameter("t");
        List<News> list = new ArrayList<>();
        if (type != null && type.equals("pypl")) {
            list.add(new News("PYPL:2020年5月份全球编程语言排行榜", "2020-5-1", "PYPL", "..."));
            list.add(new News("PYPL:2020年6月份全球编程语言排行榜", "2020-6-1", "PYPL", "..."));
            list.add(new News("PYPL:2020年7月份全球编程语言排行榜", "2020-7-1", "PYPL", "..."));
            list.add(new News("PYPL:2020年8月份全球编程语言排行榜", "2020-8-1", "PYPL", "..."));
        } else if (type == null || type.equals("tiobe")) {
            list.add(new News("TIOBE:2020年5月份全球编程语言排行榜", "2020-5-1", "TIOBE", "..."));
            list.add(new News("TIOBE:2020年6月份全球编程语言排行榜", "2020-6-1", "TIOBE", "..."));
            list.add(new News("TIOBE:2020年7月份全球编程语言排行榜", "2020-7-1", "TIOBE", "..."));
            list.add(new News("TIOBE:2020年8月份全球编程语言排行榜", "2020-8-1", "TIOBE", "..."));
        }

        String jsonString = JSON.toJSONString(list);
//        System.out.println(json);
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(jsonString);
    }
}
