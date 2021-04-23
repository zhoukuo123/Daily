package com.zk.spring.jdbc.service;

import com.zk.spring.jdbc.dao.EmployeeDao;
import com.zk.spring.jdbc.entity.Employee;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author CoderZk
 */
@Service
public class EmployeeService {
    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private DataSourceTransactionManager transactionManager;

    public void batchImport() {
        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setEno(8000 + i);
            employee.setEname("员工" + i);
            employee.setSalary(4000f);
            employee.setDname("研发部");
            employee.setHiredate(new Date());
            employeeDao.insert(employee);
        }
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public DataSourceTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
