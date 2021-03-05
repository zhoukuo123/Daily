package com.zk.json;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class FastJsonSample2 {
    public static void main(String[] args) {
        List<Employee> emplist = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Employee employee = new Employee();
            employee.setEmpno(4488 + i);
            employee.setEname("员工" + i);
            emplist.add(employee);
        }
        String json = JSON.toJSONString(emplist);
        System.out.println(json);
        List<Employee> employees = JSON.parseArray(json, Employee.class);
        for (Employee employee : employees) {
            System.out.println(employee.getEmpno() + ":" + employee.getEname());
        }
    }
}
