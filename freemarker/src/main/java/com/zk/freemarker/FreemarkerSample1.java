package com.zk.freemarker;

import com.zk.freemarker.entity.Computer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerSample1 {
    public static void main(String[] args) throws IOException, TemplateException {
        // 1. 加载模板
        // 创建核心配置对象
        Configuration config = new Configuration(Configuration.VERSION_2_3_28);
        // 设置加载的目录
        config.setClassForTemplateLoading(FreemarkerSample1.class, "/template");
        // 得到模板对象
        Template template = config.getTemplate("sample1.ftl");
        // 2. 创建数据
        Map<String, Object> data = new HashMap<>();
        data.put("site", "百度");
        data.put("url", "http://www.baidu.com");
        data.put("date", new Date());
        data.put("number", 837183.883217);

        Map<Object, Object> info = new HashMap<>();
        info.put("cpu", "i5");
        Computer c1 = new Computer("1234567", "ThinkPad", 1, "李四", new Date(), 12900f, info);

        data.put("computer", c1);
        // 3. 产生输出
        template.process(data, new OutputStreamWriter(System.out));
    }
}
