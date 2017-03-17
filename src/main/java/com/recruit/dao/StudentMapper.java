package com.recruit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select("select student_id from student where uid=#{uid};")
    public int getStudentIdByUId(String uid);
  
    @Select("SELECT times_of_break_contact from student where student_id = #{studentId};")
    public int getTimesOfBreakContact(int studentId);
    
    @Update("update student set times_of_break_contact = 1 + times_of_break_contact where student_id = #{studentId};")
    public int addTimesOfBreakContact(int studentId);
	
}