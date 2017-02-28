package com.recruit.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.TeacherMapper;
import com.recruit.pojo.Teacher;
import com.recruit.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherMapper teacherMapper;

	public TeacherMapper getTeacherMapper() {
		return teacherMapper;
	}

	public void setTeacherMapper(TeacherMapper teacherMapper) {
		this.teacherMapper = teacherMapper;
	}

	public Teacher getTeacherById(int id) {
		// TODO Auto-generated method stub
		return teacherMapper.selectByPrimaryKey(id);
	}

	public List<Teacher> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertSelective(Teacher record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteTeacherById(int id) {
		// TODO Auto-generated method stub
		
	}
	

}
