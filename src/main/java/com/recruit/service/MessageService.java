package com.recruit.service;

import java.util.List;

import com.recruit.pojo.Message;

public interface MessageService {
	public Message getMessageById(int id);
	
	public List<Message> selectAll();
	
	public int insertSelective(Message record);
	
	public void deleteMessageById(int id);
}
