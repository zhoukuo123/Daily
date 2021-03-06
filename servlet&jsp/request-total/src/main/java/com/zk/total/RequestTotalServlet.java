package com.zk.total;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@SuppressWarnings("unchecked")
@WebServlet("/rt")
public class RequestTotalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        Map<String, Integer> totalMap = (Map<String, Integer>) servletContext.getAttribute("totalMap");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
//        totalMap.forEach((String time, Integer value) -> {
//            out.println(time + "->" + value);
//        });
        List<String> timeList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        totalMap.forEach((String time, Integer value) -> {
            timeList.add(time);
            valueList.add(value);
        });
        Map<String, Object> map = new HashMap<>();
        map.put("timeList", timeList);
        map.put("valueList", valueList);
        String jsonString = JSON.toJSONString(map);
//        System.out.println(jsonString);
        out.println(jsonString);
    }
}
