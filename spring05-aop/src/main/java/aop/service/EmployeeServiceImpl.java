package aop.service;

/**
 * @author CoderZk
 */
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void createEmployee() {
        System.out.println("执行创建员工业务逻辑");
    }
}
