package com.zk.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;

public class XPathTestor {
    public void xpath(String xpathExp) {
        String file = "/home/linux/IdeaProjects/Daily/xml/src/hr.xml";

        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);

            // selectNodes方法 返回 XPath 所查询到的节点
            List<Node> nodes = document.selectNodes(xpathExp);
            for (Node node : nodes) {
                Element emp = (Element) node;

                System.out.println(emp.attributeValue("no"));
                System.out.println(emp.element("name").getText());
                System.out.println(emp.elementText("age"));
                System.out.println(emp.elementText("salary"));
                System.out.println("--------------------------------");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        XPathTestor testor = new XPathTestor();
//        testor.xpath("/hr/employee");
//        testor.xpath("//employee");
//        testor.xpath("//employee[salary > 3000]");
//        testor.xpath("//employee[name = '李铁柱']");
//        testor.xpath("//employee[@no = 3310]");
//        testor.xpath("//employee[1]");
//        testor.xpath("//employee[last()]");
//        testor.xpath("//employee[position() < 3]");
        testor.xpath("//employee[3] | //employee[1]");
    }
}
