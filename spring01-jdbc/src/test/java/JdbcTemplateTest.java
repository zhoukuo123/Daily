import com.zk.spring.jdbc.dao.EmployeeDao;
import com.zk.spring.jdbc.entity.Employee;
import com.zk.spring.jdbc.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author CoderZk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class JdbcTemplateTest {
    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private EmployeeService employeeService;

    @Test
    public void testFindById() {
        Employee employee = employeeDao.findById(1000);
        System.out.println(employee);
    }

    @Test
    public void testFindByDname() {
        List<Employee> list = employeeDao.findByDname("市场部");
        System.out.println(list);
    }

    @Test
    public void testFindMapByDname() {
        List<Map<String, Object>> list = employeeDao.findMapByDname("市场部");
        System.out.println(list);
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employee.setEno(8888);
        employee.setEname("CoderZk");
        employee.setSalary(6666f);
        employee.setDname("研发部");
        employee.setHiredate(new Date());
        employeeDao.insert(employee);
    }

    @Test
    public void testUpdate() {
        Employee employee = employeeDao.findById(8888);
        employee.setSalary(employee.getSalary() + 1000);
        int count = employeeDao.update(employee);
        System.out.println("本次更新" + count + "条数据");
    }

    @Test
    public void testDelete() {
        int count = employeeDao.delete(8888);
        System.out.println("本次删除" + count + "条数据");
    }

    @Test
    public void testBatchImport() {
        employeeService.batchImport();
        System.out.println("批量导入成功");
    }
}
