package com.recruit.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.service.SsdutEmpInfoService;

@Service("ssdutEmpInfoQuartz")
public class SsdutEmpInfoQuartz {
	
	@Autowired
	private SsdutEmpInfoService ssdutEmpInfoService;
	
	public SsdutEmpInfoService getSsdutEmpInfoService() {
		return ssdutEmpInfoService;
	}

	public void setSsdutEmpInfoService(SsdutEmpInfoService ssdutEmpInfoService) {
		this.ssdutEmpInfoService = ssdutEmpInfoService;
	}
	
	
	/**
	run the method every five mimutes
	 */
	public void updateInfo(){
		
		System.out.println("stop the service for now");
		
		/*int maxId=ssdutEmpInfoService.getCurrentIndex(1040);
		ssdutEmpInfoService.updateNewsMaxByClass(1040, maxId);
		
		maxId=ssdutEmpInfoService.getCurrentIndex(1041);
		ssdutEmpInfoService.updateNewsMaxByClass(1041, maxId);
		
		maxId=ssdutEmpInfoService.getCurrentIndex(1042);
		ssdutEmpInfoService.updateNewsMaxByClass(1042, maxId);*/
	}
	
}
