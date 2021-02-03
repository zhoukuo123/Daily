package com.zk.mgallery.utils;

import com.zk.mgallery.entity.Painting;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
        try {
            dataFile = URLDecoder.decode(dataFile, "UTF-8");
//            System.out.println(dataFile);
            // 利用Dom4j对XML进行解析
            SAXReader reader = new SAXReader();
            // 1.获取Document文档对象
            Document document = reader.read(dataFile);
            // 2.利用Xpath得到XML节点集合
            List<Node> nodes = document.selectNodes("/root/painting");

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

    public static List<Painting> getRawData() {
        return data;
    }

    public static void main(String[] args) {
//        new XmlDataSource();
//        List<Painting> paintings = XmlDataSource.getRawData();
//        System.out.println(paintings);
    }
}
