package com.zk.employee.service;

import com.zk.employee.api.IEmployeeActivityService;
import com.zk.employee.dao.EmployeeActivityDao;
import com.zk.employee.entity.EmployeeActivityEntity;
import com.zk.employee.pojo.ActivityType;
import com.zk.employee.pojo.EmployeeActivity;
import com.zk.restroom.pojo.Toilet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

/**
 * @author CoderZk
 */
@Slf4j
@RestController
@RequestMapping("employee")
public class EmployeeService implements IEmployeeActivityService {

    @Autowired
    private EmployeeActivityDao employeeActivityDao;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional
    @PostMapping("/toilet-break")
    public EmployeeActivity useToilet(Long employeeId) {
        int count = employeeActivityDao.countByEmployeeIdAndActivityTypeAndActive(employeeId, ActivityType.TOILET_BREAK, true);
        if (count > 0) {
            throw new RuntimeException("you are in a toilet, can't use another toilet");
        }

        Toilet[] toilets = restTemplate.getForObject("http://restroom-service/toilet-serivce/checkAvailable/", Toilet[].class);
        if (ArrayUtils.isEmpty(toilets)) {
            throw new RuntimeException("shit in urinal");
        }

        MultiValueMap<String, Object> args = new LinkedMultiValueMap<>();
        args.add("id", toilets[0]);
        Toilet toilet = restTemplate.postForObject("http://restroom-service/toilet-serivce/occupy", args, Toilet.class);

        // 保存记录
        EmployeeActivityEntity toiletBreak = EmployeeActivityEntity.builder()
                .employeeId(employeeId)
                .active(true)
                .activityType(ActivityType.TOILET_BREAK)
                .resourceId(toilet.getId())
                .build();

        employeeActivityDao.save(toiletBreak);

        EmployeeActivity result = new EmployeeActivity();
        BeanUtils.copyProperties(toiletBreak, result);

        return result;
    }

    @Override
    public EmployeeActivity restoreToilet(Long employeeId) {
        return null;
    }
}
