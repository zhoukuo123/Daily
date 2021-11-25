package com.zk.employee.api;

import com.zk.employee.pojo.EmployeeActivity;

/**
 * @author CoderZk
 */
public interface IEmployeeActivityService {
    EmployeeActivity useToilet(Long employeeId);

    EmployeeActivity restoreToilet(Long employeeId);
}
