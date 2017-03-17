package com.recruit.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.data.StaticData;
import com.recruit.data.WeChatMessage;
import com.recruit.pojo.Message;
import com.recruit.pojo.SsdutEmpInfo;
import com.recruit.pojo.WeChatNews;
import com.recruit.service.SsdutEmpInfoService;
import com.recruit.service.StudentService;
import com.recruit.service.WeChatResponseService;
import com.recruit.service.WeChatService;

@Service("weChatService")
public class WeChatServiceImpl implements WeChatService {

	@Autowired
	private WeChatResponseService weChatResponseService;

	@Autowired
	private SsdutEmpInfoService ssdutEmpInfoService;

	@Autowired
	private StudentService studentService;

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public WeChatResponseService getWeChatResponseService() {
		return weChatResponseService;
	}

	public void setWeChatResponseService(WeChatResponseService weChatResponseService) {
		this.weChatResponseService = weChatResponseService;
	}

	public SsdutEmpInfoService getSsdutEmpInfoService() {
		return ssdutEmpInfoService;
	}

	public void setSsdutEmpInfoService(SsdutEmpInfoService ssdutEmpInfoService) {
		this.ssdutEmpInfoService = ssdutEmpInfoService;
	}

	public String processRequest(Map<String, String> requestMap) {
		// TODO Auto-generated method stub

		String respContent = null;

		String fromUserName = null, toUserName = null, msgType = null;

		try {

			fromUserName = requestMap.get("FromUserName");
			toUserName = requestMap.get("ToUserName");
			msgType = requestMap.get("MsgType");
		} catch (Exception e) {

		}

		if (fromUserName == null || toUserName == null || msgType == null) {

			return weChatResponseService.getTextResponse(fromUserName, toUserName, "您输入的消息有误");
		}

		// 文字消息
		if (msgType.equals(WeChatMessage.REQ_MESSAGE_TYPE_TEXT)) {

			// respContent = "您发送的是文字消息！";

			String content = requestMap.get("Content");

			if ("实习就业".equals(content)) {

				return getSsdutList(fromUserName, toUserName);

			}

			else if ("信息绑定".equals(content)) {

				respContent = infomationBind(fromUserName);

			} else if ("我的通知".equals(content)) {

				respContent = getNotice(fromUserName, toUserName, 0);
				if (respContent != null) {

					return respContent;
				}

				String uri = StaticData.IPAddress + "student/getUid.do";

				respContent = "<a href=\"" + uri + "?uid=" + fromUserName + "\">点击绑定个人信息</a>";

			} else if ("所有通知".equals(content)) {

				respContent = getNotice(fromUserName, toUserName, 1);

				if (respContent != null) {

					return respContent;
				}

				String uri = StaticData.IPAddress + "student/getUid.do";

				respContent = "<a href=\"" + uri + "?uid=" + fromUserName + "\">点击绑定个人信息</a>";

			} else {

				respContent = "您的消息我们收到了～";
			}

		}
		// 图片消息
		else if (msgType.equals(WeChatMessage.REQ_MESSAGE_TYPE_IMAGE)) {

			respContent = "您发送的是图片消息！";
		}
		// 地理位置消息
		else if (msgType.equals(WeChatMessage.REQ_MESSAGE_TYPE_LOCATION)) {

			respContent = "您发送的是地理位置消息！";
		}
		// 链接消息
		else if (msgType.equals(WeChatMessage.REQ_MESSAGE_TYPE_LINK)) {

			respContent = "您发送的是链接消息！";
		}
		// 音频消息
		else if (msgType.equals(WeChatMessage.REQ_MESSAGE_TYPE_VOICE)) {

			respContent = "您发送的是音频消息！";
		}
		// 事件推送
		else if (msgType.equals(WeChatMessage.REQ_MESSAGE_TYPE_EVENT)) {
			// 事件类型
			String eventType = requestMap.get("Event");
			// 订阅
			if (eventType.equals(WeChatMessage.EVENT_TYPE_SUBSCRIBE)) {
				respContent = "谢谢您的关注！";
			}
			// 取消订阅
			else if (eventType.equals(WeChatMessage.EVENT_TYPE_UNSUBSCRIBE)) {
				// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
			}
			// 自定义菜单点击事件
			else if (eventType.equals(WeChatMessage.EVENT_TYPE_CLICK)) {
				// TODO 自定义菜单权没有开放，暂不处理该类消息
			}
		}

		if (respContent == null) {

			return weChatResponseService.getTextResponse(fromUserName, toUserName, "您输入的消息有误");
		}

		return weChatResponseService.getTextResponse(fromUserName, toUserName, respContent);

	}

	private String infomationBind(String uid) {

		int stuid = studentService.getStudentIdByUId(uid);

		System.out.println(stuid);

		if (stuid == 0) {

			String uri = StaticData.IPAddress + "student/getUid.do";

			return "<a href=\"" + uri + "?uid=" + uid + "\">点击绑定个人信息</a>";
		}

		return "您已经将微信与学号" + stuid + "进行了绑定";

	}

	/**
	 * 
	 * 微信平台中 获得实习就业信息的最新的列表
	 */
	private String getSsdutList(String fromUserName, String toUserName) {

		List<SsdutEmpInfo> list = ssdutEmpInfoService.getNewsByPage(1);

		if (list != null) {

			if (list.size() > 0) {
				
				int len=list.size();
				
				if(len>6){
					
					len=6;
				}

				List<WeChatNews> newsList = new ArrayList<WeChatNews>(len+1);

				for (int i = 0; i < len; i++) {

					WeChatNews news = new WeChatNews();
					news.setDescription("");
					
					if(i==0){
						news.setPicUrl("http://p1.bqimg.com/4851/784f76e3f95bc50f.png");
					}else{
						news.setPicUrl("http://p1.bqimg.com/4851/dfde5cc789664dfc.png");
					}
					
					
					news.setTitle(list.get(i).getTitle());
					news.setUrl(StaticData.IPAddress + "empinfo/empinfo_detail?id=" + list.get(i).getId());

					newsList.add(news);
				}

				WeChatNews news = new WeChatNews();
				news.setDescription("");
				news.setPicUrl("http://p1.bqimg.com/4851/dfde5cc789664dfc.png");
				news.setTitle("点击加载更多");
				news.setUrl(StaticData.IPAddress + "empinfo/empinfo_list?page=1");

				newsList.add(news);

				return weChatResponseService.getNewsResponse(fromUserName, toUserName, newsList);

			}

		}

		return null;

	}

	private String getNotice(String fromUserName, String toUserName, int type) {

		int stuid = studentService.getStudentIdByUId(fromUserName);

		if (stuid == 0) {

			return null;
		}

		List<Message> list = null;

		// 表示获取本人的通知
		if (type == 0) {
			list = studentService.getPrivateMessage(1, stuid);
		}

		// 表示这个通知是获取所有的的通知
		if (type == 1) {
			list = studentService.getPublicMessage(1);
		}

		if (list != null) {

			if (list.size() > 0) {
				
				int len=list.size();
				
				if(len>6){
					
					len=6;
				}

				List<WeChatNews> newsList = new ArrayList<WeChatNews>(len+1);

				for (int i = 0; i < len; i++) {

					WeChatNews news = new WeChatNews();
					news.setDescription("");
					//news.setPicUrl("http://pic.qiantucdn.com/58pic/14/12/04/58k58PICPwX_1024.jpg");
					
					if(i==0){
						news.setPicUrl("http://p1.bqimg.com/4851/784f76e3f95bc50f.png");
					}else{
						news.setPicUrl("http://p1.bqimg.com/4851/dfde5cc789664dfc.png");
					}
					
					news.setTitle(list.get(i).getTitle());
					news.setUrl(StaticData.IPAddress + "student/godetails?messageId=" + list.get(i).getMessageId()
							+ "&studentId=" + stuid);

					newsList.add(news);
				}

				WeChatNews news = new WeChatNews();
				news.setDescription("");
				news.setPicUrl("http://p1.bqimg.com/4851/dfde5cc789664dfc.png");
				news.setTitle("点击加载更多");

				// 表示获取本人的通知
				if (type == 0) {
					news.setUrl(StaticData.IPAddress + "student/getSelf?studentId=" + stuid + "&page=1");
				}

				// 表示这个通知是获取所有的的通知
				if (type == 1) {
					news.setUrl(StaticData.IPAddress + "student/index?studentId=" + stuid + "&page=1");
				}

				newsList.add(news);

				return weChatResponseService.getNewsResponse(fromUserName, toUserName, newsList);

			}

			List<WeChatNews> newsList = new ArrayList<WeChatNews>(1);

			WeChatNews news = new WeChatNews();
			news.setDescription("");
			news.setPicUrl("http://p1.bqimg.com/4851/784f76e3f95bc50f.png");
			news.setTitle("暂时没有通知");

			return weChatResponseService.getNewsResponse(fromUserName, toUserName, newsList);

		} else {

			List<WeChatNews> newsList = new ArrayList<WeChatNews>(1);

			WeChatNews news = new WeChatNews();
			news.setDescription("");
			news.setPicUrl("http://p1.bqimg.com/4851/784f76e3f95bc50f.png");
			news.setTitle("暂时没有通知");

			return weChatResponseService.getNewsResponse(fromUserName, toUserName, newsList);

		}
	}

}
