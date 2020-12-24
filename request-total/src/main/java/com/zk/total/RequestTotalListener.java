package com.zk.total;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unchecked")
public class RequestTotalListener implements ServletContextListener, ServletRequestListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        List<String> timeList = new ArrayList<>();
//        List<Integer> valueList = new ArrayList<>();

//        sce.getServletContext().setAttribute("timeList", timeList);
//        sce.getServletContext().setAttribute("valueList", valueList);

        Map<String, Integer> totalMap = new LinkedHashMap<>();
        sce.getServletContext().setAttribute("totalMap", totalMap);
        System.out.println("请求分析统计已初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
//        List<String> timeList = (List<String>) sre.getServletContext().getAttribute("timeList");
//        List<Integer> valueList = (List<Integer>) sre.getServletContext().getAttribute("valueList");
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        String url = req.getRequestURL().toString();
        if (url.endsWith("/rt")) {
            return;
        }
        Map<String, Integer> totalMap = (Map<String, Integer>) sre.getServletContext().getAttribute("totalMap");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String time = simpleDateFormat.format(date);
        System.out.println(time);
        if (totalMap.containsKey(time)) {
            Integer value = totalMap.get(time);
            totalMap.put(time, ++value);
        } else {
            totalMap.put(time, 1);
        }
        sre.getServletContext().setAttribute("totalMap", totalMap);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }
}
