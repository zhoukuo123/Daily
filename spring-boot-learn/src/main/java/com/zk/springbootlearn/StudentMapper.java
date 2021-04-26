package com.zk.springbootlearn;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author CoderZk
 * 学生Mapper
 */
@Mapper
@Repository
public interface StudentMapper {
    @Select("select * from students where id = #{id}")
    Student findById(Integer id);
}
