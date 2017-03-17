package com.recruit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.recruit.data.StaticData;

@Controller
@RequestMapping("forecast")
public class URPForecastController {
	
	@RequestMapping(value = "forecast", method = RequestMethod.POST)
	public String forecast(HttpServletRequest request,Model model){
		
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		
		if(userName==null||password==null){
			
			model.addAttribute("msg","学号或密码为空");
			return "show_forecast.jsp";
		}
		
		String requestId=System.currentTimeMillis()+"";
		
		Map<String,String> map=new HashMap<String,String>(3);
		map.put("request_id", requestId);
		map.put("username",userName);
		map.put("password", password);
		
		StaticData.URPMessageQuene.add(map);
		
		String responseMsg=null;
		
		int tag=0;
		while(tag<8){
			
			if(StaticData.URPResponseMessage.get(requestId)==null){
				
				try {
					
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				
				tag++;
				
			}else{
				
				responseMsg=StaticData.URPResponseMessage.get(requestId);
				
				tag=20;
			}
			
		}
		
		if(null==responseMsg){
			model.addAttribute("msg","处理结果错误");
			return "show_forecast.jsp";
		}
		
		//do something forecast
		
		return "show_forecast.jsp";
	}
	
	@RequestMapping("urp_message_request")
	public @ResponseBody String getUrpMessageRequest(){
		
		int size=StaticData.URPMessageQuene.size();
		
		if(size==0){
			
			return null;			
		}
		
		Map<String,String> map=StaticData.URPMessageQuene.poll();
		
		JsonObject obj=new JsonObject();

		obj.addProperty("request_id", map.get("request_id"));
		obj.addProperty("username", map.get("username"));
		obj.addProperty("password", map.get("password"));		
		
		return obj.toString();
	}

	@RequestMapping("urp_message_response")
	public @ResponseBody String getUrpMessageResponse(HttpServletRequest request){
		
		int size=StaticData.URPMessageQuene.size();
		
		if(size==0){
			
			return null;			
		}
		
		String request_id=request.getParameter("request_id");
		String return_body=request.getParameter("return_body");
		
		if(return_body==null||request_id==null){
			return null;
		}
		
		StaticData.URPResponseMessage.put(request_id, return_body);
		
		return null;
	}
}
