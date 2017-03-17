package com.recruit.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.MessageMapper;
import com.recruit.pojo.Message;
import com.recruit.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageMapper messageMapper;

	public MessageMapper getMessageMapper() {
		return messageMapper;
	}
	public void setMessageMapper(MessageMapper messageMapper) {
		this.messageMapper = messageMapper;
	}
	public Message getMessageById(int id){
		return this.messageMapper.selectByPrimaryKey(id);
	}
	public int insertSelective(Message record) {
		// TODO Auto-generated method stub
		
		return this.messageMapper.insertSelective(record);
	}
	public List<Message> selectAll() {
		// TODO Auto-generated method stub
		return this.messageMapper.selectAll();
	}
	public int deleteMessageById(int id) {
		// TODO Auto-generated method stub
		return this.messageMapper.deleteByPrimaryKey(id);
				
	}

}
