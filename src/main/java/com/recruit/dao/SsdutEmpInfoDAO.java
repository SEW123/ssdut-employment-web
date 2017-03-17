package com.recruit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.recruit.pojo.SsdutEmpInfo;


public interface SsdutEmpInfoDAO {
	@Insert("insert into ssdutempinfo(id,calory,title,ptime,htmlurl,content) values (#{id},#{calory},#{title},#{ptime},#{htmlurl},#{content});")
	public void insertEmpInfo(SsdutEmpInfo info);
	
	@Select("select max(id) from ssdutempinfo where calory=#{calory};")
	public int getCurrentIndex(@Param("calory")int calory);
	
	@Select("SELECT a.id,a.title from ssdutempinfo a join (SELECT id FROM `ssdutempinfo` ORDER BY id DESC LIMIT #{page},10) b on a.id=b.id;")
	public List<SsdutEmpInfo> getPageList(int page);
	
	@Select("select id,title,ptime,content from ssdutempinfo where id=#{id};")
	public SsdutEmpInfo getEmpInfoDetail(@Param("id")int id);

}
