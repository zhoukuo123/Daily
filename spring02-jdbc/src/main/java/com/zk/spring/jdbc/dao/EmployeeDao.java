package com.zk.spring.jdbc.dao;

import com.zk.spring.jdbc.entity.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author CoderZk
 */
@Repository
public class EmployeeDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public Employee findById(Integer eno) {
        String sql = "select * from employee where eno = ?";
        // 查询单条数据
        Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{eno}, new BeanPropertyRowMapper<>(Employee.class));
        return employee;
    }

    public List<Employee> findByDname(String dname) {
        String sql = "select * from employee where dname = ?";
        // 查询复合数据
        List<Employee> list = jdbcTemplate.query(sql, new Object[]{dname}, new BeanPropertyRowMapper<>(Employee.class));
        return list;
    }

    public List<Map<String, Object>> findMapByDname(String dname) {
        String sql = "select eno as empno, salary as s from employee where dname = ?";
        // 将查询结果作为Map进行封装
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, new Object[]{dname});
        return maps;
    }

    public void insert(Employee employee) {
        String sql = "insert into employee values(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{employee.getEno(), employee.getEname(), employee.getSalary(), employee.getDname(), employee.getHiredate()});
    }

    public int update(Employee employee) {
        String sql = "update employee set ename = ?, salary = ?, dname = ?, hiredate = ? where eno = ?";
        int count = jdbcTemplate.update(sql, new Object[]{employee.getEname(), employee.getSalary(), employee.getDname(), employee.getHiredate(), employee.getEno()});
        return count;
    }

    public int delete(Integer eno) {
        String sql = "delete from employee where eno = ?";
        int count = jdbcTemplate.update(sql, new Object[]{eno});
        return count;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
