package com.recruit.service;

import java.util.List;

import com.recruit.pojo.Message2student;

public interface M2sService {
	public Message2student getMessage2studentById(int mid, int sid);
	
	public List<Message2student> getAllMessage2student();
	
	public List<Message2student> getAllStudent(int message_id);
	
	public List<Message2student> getOfferOwner();
	
	public List<Message2student> getOfferOwnerNotBreak();
	
	public List<Message2student> getOfferOwnerBreak();
	
	public int acceptTest(int mid, int sid, int flag);
	
	public int acceptInterview(int mid, int sid, int flag);
	
	public int acceptOffer(int mid, int sid, int flag);
	
	public void insertMessage2student(Message2student record);
	
	
}
