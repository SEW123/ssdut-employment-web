package com.recruit.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.Message2studentMapper;
import com.recruit.pojo.Message2student;
import com.recruit.pojo.Message2studentKey;
import com.recruit.service.M2sService;

@Service("m2sService")
public class M2sServiceImpl implements M2sService {
	
	@Autowired
	private Message2studentMapper m2sMapper;

	public Message2studentMapper getM2sMapper() {
		return m2sMapper;
	}

	public void setM2sMapper(Message2studentMapper m2sMapper) {
		this.m2sMapper = m2sMapper;
	}

	public Message2student getMessage2studentById(int mid, int sid) {
		// TODO Auto-generated method stub
		Message2studentKey m2sKey = new Message2studentKey();
		m2sKey.setMessageId(mid);
		m2sKey.setStudentId(sid);
		return m2sMapper.selectByPrimaryKey(m2sKey);
	}

	public List<Message2student> getAllMessage2student() {
		// TODO Auto-generated method stub
		return m2sMapper.selectAll();
	}

	public List<Message2student> getAllStudent(int message_id) {
		// TODO Auto-generated method stub
		return m2sMapper.selectM2sByMid(message_id);
	}

	public List<Message2student> getOfferOwner() {
		// TODO Auto-generated method stub
		return m2sMapper.getOfferOwner();
	}

	public List<Message2student> getOfferOwnerNotBreak() {
		// TODO Auto-generated method stub
		return m2sMapper.getOfferOwnerNotBreak();
	}

	public List<Message2student> getOfferOwnerBreak() {
		// TODO Auto-generated method stub
		return m2sMapper.getOfferOwnerBreak();
	}

	public int acceptTest(int mid, int sid, int flag) {
		// TODO Auto-generated method stub
		return m2sMapper.acceptTest(mid, sid, flag);
	}

	public int acceptInterview(int mid, int sid, int flag) {
		// TODO Auto-generated method stub
		return m2sMapper.acceptInterview(mid, sid, flag);
	}

	public int acceptOffer(int mid, int sid, int flag) {
		// TODO Auto-generated method stub
		return m2sMapper.acceptOffer(mid, sid, flag);
	}

	public void insertMessage2student(Message2student record) {
		// TODO Auto-generated method stub
		m2sMapper.insertSelective(record);
	}
	

}
