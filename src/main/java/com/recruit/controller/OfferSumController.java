package com.recruit.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.exportExcel.OfferOwnerService;
import com.recruit.pojo.Message2student;
import com.recruit.service.M2sService;

@Controller
@RequestMapping("/teacher")
public class OfferSumController {

	@Autowired
	private M2sService m2sService;

	public M2sService getM2sService() {
		return m2sService;
	}

	public void setM2sService(M2sService m2sService) {
		this.m2sService = m2sService;
	}

	@RequestMapping("/offerowner")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String sheetName = "";
		List<Message2student> infoViewList = null;
		if ("-1".equals(type)) {
			infoViewList = m2sService.getOfferOwner();
			response.setHeader("Content-disposition", "attachment;filename=AllOfferOwner.xls");
			sheetName = "AllOfferOwner";
		} else if ("0".equals(type)) {
			infoViewList = m2sService.getOfferOwnerBreak();
			response.setHeader("Content-disposition", "attachment;filename=OfferOwnerBreak.xls");
			sheetName = "OfferOwnerBreak";
		} else if ("1".equals(type)) {
			infoViewList = m2sService.getOfferOwnerNotBreak();
			response.setHeader("Content-disposition", "attachment;filename=AllOfferOwnerNotBreak.xls");
			sheetName = "OfferOwnerNotBreak";
		} else {
			return;
		}

		HSSFWorkbook wb = OfferOwnerService.export(infoViewList, sheetName);
		response.setContentType("application/vnd.ms-excel");

		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}
