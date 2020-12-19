package com.zk.json;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Employee {
    private Integer empno;
    private String ename;
    private String job;
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JSONField(name = "hiredate", format = "yyyy-MM-dd")
    private Date hdate;
    private Float salary;
    @JSONField(serialize = false)
    private String dname;

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getHdata() {
        return hdate;
    }

    public void setHdate(Date hdate) {
        this.hdate = hdate;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
