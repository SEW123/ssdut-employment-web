package com.recruit.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.StudentMapper;
import com.recruit.pojo.Message;
import com.recruit.pojo.Student;
import com.recruit.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentMapper studentMapper;

	public StudentMapper getStudentMapper() {
		return studentMapper;
	}
	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}
	public Student getStudentById(int id){
		return this.studentMapper.selectByPrimaryKey(id);
	}
	public int insertSelective(Student record) {
		// TODO Auto-generated method stub
		
		return this.studentMapper.insertSelective(record);
	}
	
	public void deleteStudentById(int id) {
		// TODO Auto-generated method stub
		this.studentMapper.deleteByPrimaryKey(id);
				
	}
	public List<Message> getAllMessage(int studentId) {
		// TODO Auto-generated method stub
		return this.studentMapper.getAllMessage(studentId);
	}
	public List<Student> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public Student check(int studentId, String password) {
		// TODO Auto-generated method stub
		return this.studentMapper.check(studentId, password);
	}

}
