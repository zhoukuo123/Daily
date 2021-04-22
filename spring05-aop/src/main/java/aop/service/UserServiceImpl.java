package aop.service;

/**
 * @author CoderZk
 */
public class UserServiceImpl implements UserService {
    @Override
    public void createUser() {
        System.out.println("执行创建用户业务逻辑");
    }
}
