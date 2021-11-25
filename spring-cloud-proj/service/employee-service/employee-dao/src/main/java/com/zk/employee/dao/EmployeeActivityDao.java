package com.zk.employee.dao;

import com.zk.employee.entity.EmployeeActivityEntity;
import com.zk.employee.pojo.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author CoderZk
 */
public interface EmployeeActivityDao extends JpaRepository<EmployeeActivityEntity, Long> {
    int countByEmployeeIdAndActivityTypeAndActive(Long employeeId, ActivityType activityType, boolean active);
}
