package com.recruit.service;

import java.util.List;

import com.recruit.pojo.SsdutEmpInfo;

public interface SsdutEmpInfoService {
	
	public int getCurrentIndex(int calory);
	public List<SsdutEmpInfo> getNewsByPage(int page);
	public SsdutEmpInfo getNewsById(int id);
	public int getNewsMaxByClass(int classId);
	public void updateNewsMaxByClass(int classId,int maxId);
}
