package com.zk.spring.jdbc.service;

import com.zk.spring.jdbc.dao.EmployeeDao;
import com.zk.spring.jdbc.entity.Employee;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
        // 定义了事务默认的标准配置
        TransactionDefinition definition = new DefaultTransactionDefinition();
        // 开始一个事务, 返回事务状态, 事务状态说明当前事务的执行阶段
        TransactionStatus status = transactionManager.getTransaction(definition);

        try {
            for (int i = 1; i <= 10; i++) {
                Employee employee = new Employee();
                employee.setEno(8000 + i);
                employee.setEname("员工" + i);
                employee.setSalary(4000f);
                employee.setDname("研发部");
                employee.setHiredate(new Date());
                employeeDao.insert(employee);
            }
            // 提交事务
            transactionManager.commit(status);
        } catch (RuntimeException e) {
            // 回滚事务
            transactionManager.rollback(status);
            e.printStackTrace();
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
