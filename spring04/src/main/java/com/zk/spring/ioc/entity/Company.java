package com.zk.spring.ioc.entity;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author CoderZk
 */
public class Company {
    private List<String> rooms;
    private Map<String, Computer> computers;
    private Properties info;

    public Company() {

    }

    public Company(List<String> rooms, Map<String, Computer> computers, Properties info) {
        this.rooms = rooms;
        this.computers = computers;
        this.info = info;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public Map<String, Computer> getComputers() {
        return computers;
    }

    public void setComputers(Map<String, Computer> computers) {
        this.computers = computers;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Company{" +
                "rooms=" + rooms +
                ", computers=" + computers +
                ", info=" + info +
                '}';
    }
}
