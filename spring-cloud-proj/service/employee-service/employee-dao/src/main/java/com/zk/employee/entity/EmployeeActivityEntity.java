package com.zk.employee.entity;

import com.zk.employee.pojo.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author CoderZk
 */
@Data
@Entity
@Table(name = "employee_activity")
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @Enumerated(EnumType.ORDINAL)
    private ActivityType activityType;


    private Long resourceId;

    @Column(name = "start_time")
    @CreatedDate
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "active")
    private boolean active;
}
