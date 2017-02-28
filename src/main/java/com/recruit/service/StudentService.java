package com.recruit.service;

import java.util.List;

import com.recruit.pojo.Message;
import com.recruit.pojo.Student;

public interface StudentService {
	public Student getStudentById(int id);
	
	public List<Student> selectAll();
	
	public int insertSelective(Student record);
	
	public void deleteStudentById(int id);
	
	public List<Message> getAllMessage(int studentId);
	
	public Student check(int studentId, String password);
}
