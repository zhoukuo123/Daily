package com.zk.regex;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSample {
    public static void main(String[] args) {
        StringBuilder content = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream("/home/linux/IdeaProjects/Daily/regex/src/main/webapp/sample.html");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String lineText = "";
            while ((lineText = bufferedReader.readLine()) != null) {
//                System.out.println(lineText);
                content.append(lineText).append("\n");
            }
            bufferedReader.close();
//            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1. 创建正则表达式对象
        Pattern p = Pattern.compile("<li>([A-Za-z]+)([\\u4e00-\\u9fa5]{2,15})</li>");
        // 2. 匹配正则表达式 Matcher匹配器对象
        Matcher m = p.matcher(content);
        // 3. 查找匹配的结果
        while (m.find()) {
//            System.out.println(m.group(0));
            String english = m.group(1);
            String chinese = m.group(2);
            System.out.println(chinese + "-" + english);
        }
    }
}
