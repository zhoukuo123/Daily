package com.zk.json;

import com.alibaba.fastjson.JSON;

import java.util.Calendar;

public class FastJsonSample1 {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setEmpno(4488);
        employee.setEname("王晓东");
        employee.setJob("客户经理");
        employee.setSalary(10000f);
        employee.setDname("市场部");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 0, 30, 0, 0, 0);
        employee.setHdate(calendar.getTime());
        // FastJSON中提供了JSON对象, 完成对象与JSON字符串的互相转换
        String json = JSON.toJSONString(employee);
        System.out.println(json);
        Employee emp = JSON.parseObject(json, Employee.class);
        System.out.println(emp.getEname());
    }
}
