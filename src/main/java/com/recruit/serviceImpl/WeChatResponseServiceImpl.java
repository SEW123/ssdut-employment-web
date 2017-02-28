package com.recruit.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.springframework.stereotype.Service;

import com.recruit.pojo.WeChatNews;
import com.recruit.service.WeChatResponseService;

@Service("weChatResponseService")
public class WeChatResponseServiceImpl implements WeChatResponseService {

	/**
	 return the text message to the user
	 */
	public String getTextResponse(String toName, String FromName, String respContent) {

		String returnStr = "";
		StringBuilder buffer = new StringBuilder();
		buffer.append(respContent);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		Element rootXML = new Element("xml");
		rootXML.addContent(new Element("ToUserName").setText(toName));
		rootXML.addContent(new Element("FromUserName").setText(FromName));
		rootXML.addContent(new Element("CreateTime").setText(times));
		rootXML.addContent(new Element("MsgType").setText("text"));
		rootXML.addContent(new Element("Content").setText(buffer.toString()));
		Document doc = new Document(rootXML);
		XMLOutputter XMLOut = new XMLOutputter();
		returnStr = XMLOut.outputString(doc);
		return returnStr;
		
	}

	/**
	 return the news message to the user,pic and the text 
	 */
	public String getNewsResponse(String toName, String FromName, List<WeChatNews> newsList) {

		int length=newsList.size();
		if(length>10){
			
			length=10;
		}
		
		String returnStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		Element rootXML = new Element("xml");
		rootXML.addContent(new Element("ToUserName").setText(toName));
		rootXML.addContent(new Element("FromUserName").setText(FromName));
		rootXML.addContent(new Element("CreateTime").setText(times));
		rootXML.addContent(new Element("MsgType").setText("news"));
		rootXML.addContent(new Element("ArticleCount").setText("7"));

		Element Articles = new Element("Articles");

		for (int i = 0; i < length; i++) {

			WeChatNews news=newsList.get(i);
			
			Element item = new Element("item");
			item.addContent(new Element("Title").setText(news.getTitle()));
			item.addContent(new Element("Description").setText(news.getDescription()));
			item.addContent(new Element("PicUrl").setText(news.getPicUrl()));
			item.addContent(new Element("Url").setText(news.getUrl()));

			Articles.addContent(item);

		}

		rootXML.addContent(Articles);
		Document doc = new Document(rootXML);
		XMLOutputter XMLOut = new XMLOutputter();
		returnStr = XMLOut.outputString(doc);
		return returnStr;
		
	}
	
}
