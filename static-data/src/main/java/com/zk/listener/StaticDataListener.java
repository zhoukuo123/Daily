package com.zk.listener;

import com.zk.listener.entity.Channel;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

public class StaticDataListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<Channel> list = new ArrayList<>();
        list.add(new Channel("新闻", "http://www.baidu.com/1"));
        list.add(new Channel("地图", "http://www.baidu.com/1"));
        list.add(new Channel("视频", "http://www.baidu.com/1"));
        list.add(new Channel("贴吧", "http://www.baidu.com/1"));
        list.add(new Channel("学术", "http://www.baidu.com/1"));
        list.add(new Channel("更多", "http://www.baidu.com/1"));
        sce.getServletContext().setAttribute("channelList", list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
