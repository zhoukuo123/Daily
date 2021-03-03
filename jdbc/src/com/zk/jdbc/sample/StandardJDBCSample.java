package com.zk.jdbc.sample;

import java.sql.*;

/**
 * 标准JDBC操作五步骤
 */
public class StandardJDBCSample {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // 1. 加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 创建数据库连接
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serveTimezone=Asia/Shanghai",
                    "root", "zhoukuo"
            );
            // 3. 创建Statement对象
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee where dname='研发部'");
            // 4. 遍历查询结果
            while (resultSet.next()) {
                int eno = resultSet.getInt(1);// eno
                String ename = resultSet.getString("ename");
                float salary = resultSet.getFloat("salary");
                String dname = resultSet.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 5. 关闭连接, 释放资源
                if (connection != null && connection.isClosed() == false) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
