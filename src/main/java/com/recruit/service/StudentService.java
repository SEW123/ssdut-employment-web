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
	
	public int getStudentIdByUId(String uid);
	
	public List<Message> getPublicMessage(int page);
	
	public List<Message> getPrivateMessage(int page,int stuid);
	
	public int getNumOfMessage();
	
	public int getNumOfMessagePrivate(int studentId);
	
	public int getTimesOfBreakContact(int studentId);
	
	public int addTimesOfBreakContact(int studentId);
}
