package com.zk.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class HrWriter {
    public void writeXML() {
        String file = "/home/linux/IdeaProjects/Daily/xml/src/hr.xml";
        SAXReader reader = new SAXReader();

        try {
            Document document = reader.read(file);
            Element root = document.getRootElement();
            Element employee = root.addElement("employee");
            employee.addAttribute("no", "3311");
            Element name = employee.addElement("name");
            name.setText("李铁柱");
            employee.addElement("age").setText("37");
            employee.addElement("salary").setText("3600");
            Element department = employee.addElement("department");
            department.addElement("dname").setText("人事部");
            department.addElement("address").setText("XX大厦-B105");

            Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            document.write(writer);
            writer.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HrWriter hrWriter = new HrWriter();
        hrWriter.writeXML();
    }
}
