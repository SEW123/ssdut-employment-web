package com.recruit.dao;

import java.util.List;

import com.recruit.pojo.Message;
import com.recruit.pojo.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    List<Message> getAllMessage(int studentId);
    
    Student check(int studentId, String password);
}