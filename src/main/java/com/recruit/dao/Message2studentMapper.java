package com.recruit.dao;

import java.util.List;

import com.recruit.pojo.Message2student;
import com.recruit.pojo.Message2studentKey;

public interface Message2studentMapper {
    int deleteByPrimaryKey(Message2studentKey key);

    int insert(Message2student record);

    int insertSelective(Message2student record);

    Message2student selectByPrimaryKey(Message2studentKey key);
    
    List<Message2student> selectAll();//
    
    List<Message2student> selectM2sByMid(int mid);//
    
    List<Message2student> getOfferOwner();//
    
    List<Message2student> getOfferOwnerNotBreak();//
    
    List<Message2student> getOfferOwnerBreak();//
    
    int acceptTest(int mid, int sid, int flag);//
    
    int acceptInterview(int mid, int sid, int flag);//
    
    int acceptOffer(int mid, int sid, int flag);//

    int updateByPrimaryKeySelective(Message2student record);

    int updateByPrimaryKey(Message2student record);
}