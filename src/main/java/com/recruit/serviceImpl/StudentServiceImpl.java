package com.recruit.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.MessageMapper;
import com.recruit.dao.StudentMapper;
import com.recruit.pojo.Message;
import com.recruit.pojo.Student;
import com.recruit.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private MessageMapper messageMapper;

	public MessageMapper getMessageMapper() {
		return messageMapper;
	}

	public void setMessageMapper(MessageMapper messageMapper) {
		this.messageMapper = messageMapper;
	}

	public StudentMapper getStudentMapper() {
		return studentMapper;
	}

	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}

	public Student getStudentById(int id) {
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

	public int getStudentIdByUId(String uid) {
		// TODO Auto-generated method stub
		int stuid = 0;

		try {

			stuid = studentMapper.getStudentIdByUId(uid);
		} catch (Exception e) {

		}

		if (stuid == 0) {
			return 0;
		}

		return stuid;
	}

	public List<Message> getPublicMessage(int page) {
		// TODO Auto-generated method stub

		List<Message> list = null;

		try {

			list = messageMapper.getPageList((page - 1) * 10);
		} catch (Exception e) {

		}

		return list;

	}

	public List<Message> getPrivateMessage(int page, int stuid) {

		// TODO Auto-generated method stub
		List<Message> list = null;

		try {

			list = messageMapper.getPageListPrivate((page - 1) * 10, stuid);
		} catch (Exception e) {

		}

		return list;
	}

	public int getNumOfMessage() {
		// TODO Auto-generated method stub
		return messageMapper.getNumOfMessage();
	}

	public int getNumOfMessagePrivate(int studentId) {
		// TODO Auto-generated method stub
		return messageMapper.getNumOfMessagePrivate(studentId);
	}

	public int getTimesOfBreakContact(int studentId) {
		// TODO Auto-generated method stub
		return studentMapper.getTimesOfBreakContact(studentId);
	}

	public int updateTimesOfBreakContact(int studentId) {
		// TODO Auto-generated method stub
		return studentMapper.addTimesOfBreakContact(studentId);
	}

	public int addTimesOfBreakContact(int studentId) {
		// TODO Auto-generated method stub
		return studentMapper.addTimesOfBreakContact(studentId);
	}
}
