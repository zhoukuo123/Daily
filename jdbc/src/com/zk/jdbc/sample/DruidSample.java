package com.zk.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zk.jdbc.common.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Druid连接池配置与使用
 */
public class DruidSample {
    public static void main(String[] args) {
        // 1. 加载属性文件
        Properties properties = new Properties();
        String propertyFile = DruidSample.class.getResource("/druid-config.properties").getPath();
        try {
            propertyFile = new URLDecoder().decode(propertyFile, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 2. 获取DataSource数据源对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            // 3. 创建数据库连接
            for (int i = 0; i < 20; i++) {
                conn = dataSource.getConnection();
            }
            pstmt = conn.prepareStatement("select * from employee limit 0, 10");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int eno = rs.getInt("eno");
                String ename = rs.getString("ename");
                float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /**
             * 不使用连接池: conn.close()关闭连接
             * 使用连接池: conn.close()将连接回收至连接池
             */
            DbUtils.closeConnection(rs, pstmt, conn);
        }
    }
}
