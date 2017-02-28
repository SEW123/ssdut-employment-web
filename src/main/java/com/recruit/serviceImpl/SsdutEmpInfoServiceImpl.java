package com.recruit.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.SsdutEmpInfoDAO;
import com.recruit.pojo.SsdutEmpInfo;
import com.recruit.service.SsdutEmpInfoService;
import com.recruit.service.TestService;

@Service("ssdutEmpInfoService")
public class SsdutEmpInfoServiceImpl implements SsdutEmpInfoService {

	@Autowired
	private SsdutEmpInfoDAO ssdutEmpInfoDao;

	@Autowired
	private TestService testService;

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	public SsdutEmpInfoDAO getSsdutEmpInfoDao() {
		return ssdutEmpInfoDao;
	}

	public void setSsdutEmpInfoDao(SsdutEmpInfoDAO ssdutEmpInfoDao) {
		this.ssdutEmpInfoDao = ssdutEmpInfoDao;
	}

	public int getCurrentIndex(int calory) {
		// TODO Auto-generated method stub

		if (null == ssdutEmpInfoDao) {
			return 10;
		} else {
			return ssdutEmpInfoDao.getCurrentIndex(calory) ;
		}
	}

	public List<SsdutEmpInfo> getNewsByPage(int page) {
		// TODO Auto-generated method stub

		List<SsdutEmpInfo> list = null;

		try {

			list = ssdutEmpInfoDao.getPageList((page - 1) * 6);
		} catch (Exception e) {

		}

		return list;
	}

	public SsdutEmpInfo getNewsById(int id) {
		// TODO Auto-generated method stub
		SsdutEmpInfo emp = null;

		try {

			emp = ssdutEmpInfoDao.getEmpInfoDetail(id);
		} catch (Exception e) {

		}

		return emp;
	}

	public int getNewsMaxByClass(int classId) {
		// TODO Auto-generated method stub

		String url = null;

		int maxIndex = 0;

		switch (classId) {

		case 1040:

			url = "http://ssdut.dlut.edu.cn/bkspy/bksgl/zsjy/sxjy/jyzd.htm";

			break;

		case 1041:
			url = "http://ssdut.dlut.edu.cn/bkspy/bksgl/zsjy/sxjy/sxxx.htm";
			break;

		case 1042:

			url = "http://ssdut.dlut.edu.cn/bkspy/bksgl/zsjy/sxjy/jyxx.htm";

			break;
		}

		if (null == url) {

			return 0;
		}

		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch b e.printStackTrace();
		}

		if (doc == null) {
			return 0;
		}

		Elements indexes = doc.select("span[class=\"fl\"]");

		for (int i = 0; i < indexes.size(); i++) {

			int x = Integer
					.parseInt(indexes.get(i).select("a").get(0).attr("href").substring(22).replace(".htm", "").trim());

			if (x > maxIndex) {

				maxIndex = x;

			}

		}

		return maxIndex;

	}

	/**
	 * 
	 */
	public void updateNewsMaxByClass(int classId, int maxId) {
		// TODO Auto-generated method stub

		int currentIndex = ssdutEmpInfoDao.getCurrentIndex(classId);

		while (maxId != currentIndex) {

			int nextId = 0;

			try {

				nextId = updateNewsToDB(classId, maxId);
			} catch (Exception e) {

			}

			if (nextId == 0) {

				return;
			}

			maxId = nextId;

		}
	}

	/**
	 * 
	 * @param classId
	 * @param id
	 * @return
	 * @不仅执行数据库插入操作，而且，返回下一个需要执行的ID
	 */
	private int updateNewsToDB(int classId, int id) throws Exception {

		String url = "http://ssdut.dlut.edu.cn/info/" + classId + "/" + id + ".htm";

		Document doc = Jsoup.connect(url).get();

		Elements titles = doc.select("h1[align=\"center\"]");

		Elements times = doc.select("div[align=\"center\"]");

		Elements links = doc.select("p[align=\"right\"]");

		Elements body = doc.select("form[name=\"_newscontent_fromname\"]");

		// title
		String title = titles.get(0).html();

		int x = times.get(0).html().indexOf(";点击");

		// time
		String time = "";

		if (x != -1) {

			time = times.get(0).html().substring(0, x - 1);

		}

		String strB = body.get(0).html();

		int s = strB.indexOf("hr");
		int e = strB.indexOf("上一条");

		if (e == -1) {

			e = strB.indexOf("下一条");

		}

		strB = strB.substring(s, e);

		Document doca = Jsoup.parse(strB);

		Elements scripts = doca.select("div");

		// content
		String content = scripts.get(0).html();

		int nextIndex = 0;

		for (int i = 0; i < links.size(); i++) {

			String str = links.get(i).html();

			if (str.contains("下一条")) {

				int y = str.indexOf("下一条");

				String str1 = str.substring(y);

				int z = str1.indexOf("htm");

				nextIndex = Integer.parseInt(str1.substring(13, z - 1));

			}

		}

		SsdutEmpInfo emp = new SsdutEmpInfo();
		emp.setCalory(classId);
		emp.setContent(content);
		emp.setHtmlurl(url);
		emp.setId(id);
		emp.setPtime(time);
		emp.setTitle(title);

		ssdutEmpInfoDao.insertEmpInfo(emp);

		return nextIndex;

	}

}
