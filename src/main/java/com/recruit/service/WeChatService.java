package com.recruit.service;

import java.util.Map;

public interface WeChatService {
	
	public String processRequest(Map<String, String> requestMap);
}
