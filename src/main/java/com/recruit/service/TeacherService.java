package com.recruit.service;

import java.util.List;

import com.recruit.pojo.Teacher;

public interface TeacherService {
	public Teacher getTeacherById(int id);
	
	public List<Teacher> selectAll();
	
	public int insertSelective(Teacher record);
	
	public void deleteTeacherById(int id);
}
