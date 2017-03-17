package com.recruit.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class StaticData {

	private StaticData(){}
	
	public static Map<String,String> URPResponseMessage=new ConcurrentHashMap<String, String>();
	public static ConcurrentLinkedQueue<Map<String,String>> URPMessageQuene=new ConcurrentLinkedQueue<Map<String,String>>();
	
	//public static final String IPAddress="http://localhost:8080/ssdut-employment-web/";
	public static final String IPAddress="http://120.27.117.232/ssdut-employment-web/";
}
