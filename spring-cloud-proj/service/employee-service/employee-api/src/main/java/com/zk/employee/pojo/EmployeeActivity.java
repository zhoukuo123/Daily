package com.zk.employee.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author CoderZk
 */
@Data
public class EmployeeActivity {
    private Long id;
    private Long employeeId;
    private ActivityType activityType;
    private Long resourceId;
    private Date startTime;
    private Date endTime;
    private boolean active;
}
