package com.recruit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.recruit.pojo.Message;

public interface MessageMapper {
	
    int deleteByPrimaryKey(Integer messageId);

    int insert(Message record);

    int insertSelective(Message record);
    
    List<Message> selectAll();

    Message selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    
    public List<Message> getPageList(int page);

   	public List<Message> getPageListPrivate(@Param("page")int page,@Param("studentid")int studentid);
   	
	@Select("SELECT count(message_id) from message ")
   	public int getNumOfMessage();
   	
   	@Select("select count(message_id) from message natural join (select * from message2student where student_id = #{studentId}) as b")
   	public int getNumOfMessagePrivate(int studentId);
   	
}