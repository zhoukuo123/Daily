package com.zk.jdbc.hrapp.command;

import com.sun.jndi.dns.DnsUrl;
import com.zk.jdbc.common.DbUtils;
import com.zk.jdbc.hrapp.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 分页查询员工数据
 */
public class PaginationCommand implements Command {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入页号:"); // 每页默认显示10条数据
        int page = in.nextInt();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Employee> list = new ArrayList<>();

        try {
            conn = DbUtils.getConnection();
            String sql = "select * from employee limit ?,10"; // mysql limit关键字的起始位置为0
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, (page - 1) * 10);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int eno = rs.getInt("eno");
                String ename = rs.getString("ename");
                float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");

                Employee emp = new Employee();
                emp.setEno(eno);
                emp.setEname(ename);
                emp.setSalary(salary);
                emp.setDname(dname);

                list.add(emp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(rs, pstmt, conn);
        }
    }
}
