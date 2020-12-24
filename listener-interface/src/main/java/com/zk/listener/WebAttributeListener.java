package com.zk.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class WebAttributeListener implements ServletContextAttributeListener, HttpSessionAttributeListener, ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        String attrName = event.getName();
        Object attrValue = event.getValue();
        System.out.println("ServletContext新增属性: " + attrName + ":" + attrValue);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String attrName = event.getName();
        Object attrValue = event.getValue();
        System.out.println("Session新增属性: " + attrName + ":" + attrValue);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        String attrName = srae.getName();
        Object attrValue = srae.getValue();
        System.out.println("HttpServletRequest新增属性: " + attrName + ":" + attrValue);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {

    }
}
