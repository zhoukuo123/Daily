package com.zk.restful.controller;

import com.zk.restful.entity.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CoderZk
 */
@RestController
@RequestMapping("/restful")
@CrossOrigin(origins = {"http://localhost:8080"})
public class RestfulController {
    @GetMapping("/request")
    public String doGetRequest() {
        return "{\"message\":\"返回查询结果\"}";
    }

    // POST /article/1
    // POST /restful/request/100
    @PostMapping("/request/{rid}")
    public String doPostRequest(@PathVariable("rid") Integer requestId, Person person) {
        System.out.println(person.getName() + ":" + person.getAge());
        return "{\"message\":\"数据新建成功\", \"id\":" + requestId + "}";
    }

    @PutMapping("/request")
    public String doPutRequest(Person person) {
        System.out.println(person.getName() + ":" + person.getAge());
        return "{\"message\":\"数据更新成功\"}";
    }

    @DeleteMapping("/request")
    public String doDeleteRequest() {
        return "{\"message\":\"数据删除成功\"}";
    }

    @GetMapping("/person")
    public Person findByPersonId(Integer id) {
        Person person = new Person();
        if (id == 1) {
            person.setName("lily");
            person.setAge(23);
        } else if (id == 2) {
            person.setName("smith");
            person.setAge(22);
        }
        return person;
    }

    @GetMapping("persons")
    public List<Person> findPersons() {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person();
        p1.setName("lily");
        p1.setAge(23);
        p1.setBirthday(new Date());

        Person p2 = new Person();
        p2.setName("smith");
        p2.setAge(22);
        p2.setBirthday(new Date());
        list.add(p1);
        list.add(p2);
        return list;
    }
}
