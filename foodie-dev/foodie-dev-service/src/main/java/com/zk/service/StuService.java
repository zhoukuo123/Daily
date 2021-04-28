package com.zk.service;

import com.zk.pojo.Stu;

/**
 * @author CoderZk
 */
public interface StuService {
    Stu getStuInfo(int id);

    void saveStu();

    void updateStu(int id);

    void deleteStu(int id);

    void saveParent();

    void saveChildren();
}
