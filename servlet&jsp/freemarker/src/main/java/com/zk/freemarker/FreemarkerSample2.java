package com.zk.freemarker;

import com.zk.freemarker.entity.Computer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class FreemarkerSample2 {
    public static void main(String[] args) throws IOException, TemplateException {
        // 1. 加载模板
        // 创建核心配置对象
        Configuration config = new Configuration(Configuration.VERSION_2_3_28);
        // 设置加载的目录
        config.setClassForTemplateLoading(FreemarkerSample2.class, "");
        // 得到模板对象
        Template template = config.getTemplate("sample2.ftl");
        // 2. 创建数据
        Map<String, Object> data = new HashMap<>();

        List<Computer> computers = new ArrayList<>();
        computers.add(new Computer("1234567", "ThinkPad X1", 2, null, new Date(), 12999f, new HashMap<>()));
        computers.add(new Computer("1234568", "HP X1", 1, "张三", new Date(), 7500f, new HashMap<>()));
        computers.add(new Computer("1234569", "DELL X1", 3, "李四", new Date(), 8500f, new HashMap<>()));
        computers.add(new Computer("1234570", "ACER X1", 1, "王五", new Date(), 6300f, new HashMap<>()));
        computers.add(new Computer("1234571", "MSI X1", 1, "赵六", new Date(), 9300f, new HashMap<>()));
        data.put("computers", computers);

        Map<String, Computer> computerMap = new LinkedHashMap<>();
        for (Computer c : computers) {
            computerMap.put(c.getSn(), c);
        }

        data.put("computer_map", computerMap);
        // 3. 产生输出
        template.process(data, new OutputStreamWriter(System.out));
    }
}
