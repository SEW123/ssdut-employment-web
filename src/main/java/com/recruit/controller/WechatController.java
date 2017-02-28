package com.recruit.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recruit.service.WeChatService;
import com.recruit.util.WeChatSignUtil;

@Controller
@RequestMapping("wechat")
public class WechatController {
	
	
	
	public static void main(String[] args) {
		
		String full="1";
		int fulls = Integer.parseInt(full);
		System.out.println(fulls);
	}

	@Autowired
	private WeChatService weChatService;

	public WeChatService getWeChatService() {
		return weChatService;
	}

	public void setWeChatService(WeChatService weChatService) {
		this.weChatService = weChatService;
	}


	@RequestMapping(value = "message", method = RequestMethod.GET)
	public @ResponseBody String weChatGet(HttpServletRequest request, HttpServletResponse response) {

		String signature = request.getParameter("signature");

		String timestamp = request.getParameter("timestamp");

		String nonce = request.getParameter("nonce");

		String echostr = request.getParameter("echostr");

		if (WeChatSignUtil.checkSignature(signature, timestamp, nonce)) {
			return echostr;
		}

		return null;
	}

	@RequestMapping(value = "message", method = RequestMethod.POST)
	public @ResponseBody String weChatPost(HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> requestMap =null;
				
		try{
			requestMap=parseXml(request);
		}catch(Exception e){
			
		}
				
		if(requestMap==null){
			
			return "您输入的消息有误";
		}
		
		return weChatService.processRequest(requestMap);
		
	}

	@SuppressWarnings("unchecked")
	private Map<String, String> parseXml(HttpServletRequest request) throws Exception {

		Map<String, String> map = new HashMap<String, String>();

		InputStream inputStream = request.getInputStream();

		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);

		Element root = document.getRootElement();

		List<Element> elementList = root.elements();

		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		inputStream.close();
		inputStream = null;

		return map;
	}

}
