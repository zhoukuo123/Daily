package com.zk.mgallery.utils;

import com.zk.mgallery.entity.Painting;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据源类, 用于将XML文件解析为Java对象
 */
public class XmlDataSource {
    // 通过static关键字保证数据全局唯一
    private static List<Painting> data = new ArrayList<>();
    private static String dataFile;
    static {
        // 得到painting.xml文件完整物理路径, 如果路径中存在特殊字符的话, getPath方法会将特殊字符进行base64转换, 而之后用JavaIO进行读取时会提示找不到路径
        // 所以需要将路径进行转换, 用URLDecoder.decode方法
        // /home/linux/new style/painting.xml
        // /home/linux/new%20style/painting.xml
        dataFile = XmlDataSource.class.getResource("/painting.xml").getPath();
        reload();
    }

    // 重新加载xml文件, 得到data数据
    private static void reload() {
        URLDecoder decoder = new URLDecoder();
        try {
            dataFile = decoder.decode(dataFile, "UTF-8");
//            System.out.println(dataFile);
            // 利用Dom4j对XML进行解析
            SAXReader reader = new SAXReader();
            // 1.获取Document文档对象
            Document document = reader.read(dataFile);
            // 2.利用Xpath得到XML节点集合
            List<Node> nodes = document.selectNodes("/root/painting");
            data.clear();

            for (Node node : nodes) {
                Element element = (Element) node;
                String id = element.attributeValue("id");
                String pname = element.elementText("pname");
                Painting painting = new Painting();
                painting.setId(Integer.parseInt(id));
                painting.setPname(pname);
                painting.setCategory(Integer.parseInt(element.elementText("category")));
                painting.setPrice(Integer.parseInt(element.elementText("price")));
                painting.setPreview(element.elementText("preview"));
                painting.setDescription(element.elementText("description"));
                data.add(painting);
//                System.out.println(id + ":" + pname);
            }
        } catch (UnsupportedEncodingException | DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有油画Painting对象
     * @return Painting List
     */
    public static List<Painting> getRawData() {
        return data;
    }

    /**
     * 追加新的油画数据
     * @param painting Painting实体对象
     */
    public static void append(Painting painting) {
        // 1. 读取XML文档, 得到Document对象
        SAXReader reader = new SAXReader();
        Writer writer = null;
        try {
            Document document = reader.read(dataFile);
            // 2. 创建新的painting节点
            Element root = document.getRootElement();
            Element p = root.addElement("painting");
            // 3. 创建painting节点的各个子节点
            p.addAttribute("id", String.valueOf(data.size() + 1));
            p.addElement("pname").setText(painting.getPname());
            p.addElement("category").setText(painting.getCategory().toString());
            p.addElement("price").setText(painting.getPrice().toString());
            p.addElement("preview").setText(painting.getPreview());
            p.addElement("description").setText(painting.getDescription());
            // 4. 写入XML, 完成追加操作
            writer = new OutputStreamWriter(new FileOutputStream(dataFile), StandardCharsets.UTF_8);
            document.write(writer);
            System.out.println(dataFile);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            reload(); // 内存与文件数据一致
        }
    }

    public static void main(String[] args) {
//        new XmlDataSource();
//        List<Painting> paintings = XmlDataSource.getRawData();
//        System.out.println(paintings);
        Painting painting = new Painting();
        painting.setPname("测试油画");
        painting.setCategory(1);
        painting.setPrice(4000);
        painting.setPreview("/upload/10.jpg");
        painting.setDescription("测试油画描述");
        XmlDataSource.append(painting);
    }
}
