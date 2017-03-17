package com.recruit.service;

import java.util.List;

import com.recruit.pojo.WeChatNews;

public interface WeChatResponseService {
	
	public String getTextResponse(String toName, String FromName, String respContent);
	
	public String getNewsResponse(String toName, String FromName, List<WeChatNews> newsList);
	
}
