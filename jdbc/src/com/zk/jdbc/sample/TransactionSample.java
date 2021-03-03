package com.zk.jdbc.sample;

import com.zk.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC中的事务控制
 */
public class TransactionSample {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtils.getConnection();
            // JDBC默认使用自动提交模式
            conn.setAutoCommit(false); // 关闭自动提交

            String sql = "insert into employee(eno, ename, salary, dname) values(?,?,?,?)";
            for (int i = 1000; i < 2000; i++) {
                if (i == 1005) {
                    // throw new RuntimeException("插入失败");
                }
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, i);
                pstmt.setString(2, "员工" + i);
                pstmt.setFloat(3, 4000);
                pstmt.setString(4, "市场部");
                pstmt.executeUpdate();
            }
            conn.commit(); // 提交事务区中的数据
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback(); // 回滚数据
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            DbUtils.closeConnection(null, pstmt, conn);
        }
    }
}
