package com.zk.spring.ioc.context;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CoderZk
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
    private Map<String, Object> iocContainer = new HashMap<>();

    public ClassPathXmlApplicationContext() {
        try {
            String filePath = this.getClass().getResource("/applicationContext.xml").getPath();
            filePath = URLDecoder.decode(filePath, "UTF-8");
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(filePath));
            List<Node> beans = document.getRootElement().selectNodes("bean");
            for (Node bean : beans) {
                Element element = (Element) bean;
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                Class<?> c = Class.forName(className);
                Object obj = c.newInstance();
                List<Node> properties = element.selectNodes("property");
                for (Node property : properties) {
                    Element ele = (Element) property;
                    String propName = ele.attributeValue("name");
                    String propValue = ele.attributeValue("value");
                    String setMethodName = "set" + propName.substring(0, 1).toUpperCase() + propName.substring(1);
                    System.out.println("准备执行" + setMethodName + "方法注入数据");
                    Method setMethod = c.getMethod(setMethodName, String.class);
                    // 通过setter方法注入数据
                    setMethod.invoke(obj, propValue);
                }
                iocContainer.put(id, obj);
            }
            System.out.println("IoC容器初始化完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanId) {
        return iocContainer.get(beanId);
    }
}
