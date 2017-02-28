package com.recruit.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckSession {
	public Boolean checkSession(HttpSession session,HttpServletResponse response){
		PrintWriter out;
		try {
			out = response.getWriter();
			try {
				String teacherId = session.getAttribute("teacherId").toString();
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				out.print(
						"<script language='javascript'>alert('请先登录！');" + "window.location='tlogin.jsp';</script>");
				return false;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getWriter失败！");
			return false;
		}
	}
}
