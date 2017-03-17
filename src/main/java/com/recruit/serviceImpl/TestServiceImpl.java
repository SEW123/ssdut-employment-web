package com.recruit.serviceImpl;

import org.springframework.stereotype.Service;

import com.recruit.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService{

	public int test() {
		// TODO Auto-generated method stub
		
		System.out.println("hahahha");
		
		return 1000;
	}
	
	public void haha() {
		// TODO Auto-generated method stub
		
		System.out.println("damykikidada");

	}

}
