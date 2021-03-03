package com.zk.jdbc.hrapp.command;

import java.sql.*;
import java.util.Scanner;

public class PstmtQueryCommand implements Command {
    @Override
    public void execute() {
        System.out.println("请输入部门名称:");
        Scanner in = new Scanner(System.in);
        String pdname = in.next();
        Connection connection = null;
//        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 1. 加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 创建数据库连接
            String url = "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
            connection = DriverManager.getConnection(url, "root", "zhoukuo");
            // 3. 创建PreparedStatement对象
            String sql = "select * from employee where dname = ? and eno > ?"; // 使用参数化的SQL语句
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, pdname); // 注意: 参数索引从1开始, 会将传入的参数进行敏感字符转移后放入?位置
            pstmt.setInt(2, 3500);

            // 结果集
            rs = pstmt.executeQuery();
            // 4. 遍历查询结果
            // rs.next()返回布尔值, 代表是否存在下一条记录
            // 如果有, 返回true, 同时结果集提取下一条记录
            // 如果没有, 返回false, 循环就会停止
            while (rs.next()) {
                int eno = rs.getInt(1); // JDBC中字段索引从1开始, 而非0
                String ename = rs.getString("ename");
                float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 5. 关闭连接, 释放资源
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
