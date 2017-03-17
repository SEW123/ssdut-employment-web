package com.recruit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.pojo.SsdutEmpInfo;
import com.recruit.service.SsdutEmpInfoService;

@Controller
@RequestMapping("empinfo")
public class SsdutEmpInfoController {

	@Autowired
	private SsdutEmpInfoService ssdutEmpInfoService;
	
	public SsdutEmpInfoService getSsdutEmpInfoService() {
		return ssdutEmpInfoService;
	}

	public void setSsdutEmpInfoService(SsdutEmpInfoService ssdutEmpInfoService) {
		this.ssdutEmpInfoService = ssdutEmpInfoService;
	}

	@RequestMapping("test")
	public String test(HttpServletRequest request,Model model){
		System.out.println("!!!!!!!!!!");
		model.addAttribute("damy","klaus");
		
		if(null==ssdutEmpInfoService){
			model.addAttribute("number","23");
		}else{
			model.addAttribute("number",ssdutEmpInfoService.getCurrentIndex(10));
		}		
		
		return "a.jsp";
	}
	
	@RequestMapping("aa")
	public String te(HttpServletRequest request,Model model){
		System.out.println("!!!!!!!!!!");
		model.addAttribute("damy","klaus");
		model.addAttribute("number","23");
		return "a.jsp";
	}
	
	
	@RequestMapping("empinfo_list")
	public String getSsdutEmpInfoList(HttpServletRequest request,Model model){

		
		String page=request.getParameter("page");
		
		if(page==null){
			
			return "wrong.jsp";
		}
		
		int temp=0;
		try {

			temp=Integer.parseInt(page);
		} catch (Exception e) {
			
		}
		
		if(temp==0){
			return "wrong.jsp";
		}
		
		
		List<SsdutEmpInfo> list=ssdutEmpInfoService.getNewsByPage(temp);		
		
		model.addAttribute("info_list",list);
		model.addAttribute("page",page);
		
		return "info_list.jsp";
	}
	
	
	@RequestMapping("empinfo_detail")
	public String getSsdutEmpInfoDetail(HttpServletRequest request,Model model){
		
		String id=request.getParameter("id");
		
		if(id==null){
			
			return "wrong.jsp";
		}
		
		int temp=0;
		try {

			temp=Integer.parseInt(id);
		} catch (Exception e) {
			
		}
		
		if(temp==0){
			return "wrong.jsp";
		}
		
		
		SsdutEmpInfo emp=ssdutEmpInfoService.getNewsById(temp);
		
		model.addAttribute("emp_info",emp);
		//model.addAttribute("page",page);
		
		return "info_detail.jsp";
	}
	
}
