package com.recruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.pojo.Message;
import com.recruit.pojo.Message2student;
import com.recruit.pojo.Student;
import com.recruit.service.M2sService;
import com.recruit.service.MessageService;
import com.recruit.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentSideController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private M2sService m2sService;

	@Autowired
	private StudentService studentService;

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public M2sService getM2sService() {
		return m2sService;
	}

	public void setM2sService(M2sService m2sService) {
		this.m2sService = m2sService;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping("/getUid")
	public String login(@Param("uid") String uid, Model model) {

		model.addAttribute("uid", uid);
		return "sregister.jsp";
	}

	@RequestMapping("/sregister")
	public String register(@Param("name") String name, @Param("studentId") String studentId,
			@Param("email") String email, @Param("uid") String uid, HttpServletResponse response) {
		Student student = studentService.getStudentById(Integer.parseInt(studentId));
		if (student != null) {
			PrintWriter out;

			try {
				out = response.getWriter();
				if (student.getUid().equals(uid)) {
					out.print("<script language='javascript'>alert('您已注册！');window.location='sregister.jsp';</script>");

				} else {
					out.print(
							"<script language='javascript'>alert('该学号已被注册！');window.location='sregister.jsp';</script>");
				}
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("获取PrintWriter失败！");
				return "sregister.jsp";
			}

		}
		student = new Student();
		student.setMail(email);
		System.out.println(email);
		student.setName(name);
		student.setStudentId(Integer.parseInt(studentId));
		student.setUid(uid);
		try {
			studentService.insertSelective(student);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("插入学生信息失败");
			e.printStackTrace();
			return "sregister.jsp";
		}
		return "index.do";
	}

	@RequestMapping("/index")
	public String index(HttpServletRequest request, String studentId, Model model) {

		int page = 1;
		int pageTotal = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		try {
			pageTotal = studentService.getNumOfMessage() / 10 + 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("获取message条数失败");
			e.printStackTrace();
		}
		List<Message> list = studentService.getPublicMessage(page);
		model.addAttribute("page", page);
		System.out.println("page" + page);
		model.addAttribute("pagetotal", pageTotal);
		System.out.println("pageTotal" + pageTotal);
		model.addAttribute("list", list);
		model.addAttribute("studentId", studentId);
		System.out.println("studentId" + studentId);
		return "sgetNotices.jsp";

	}

	@RequestMapping("/getSelf")
	public String getSelf(HttpServletRequest request, String studentId, Model model) {
		int sid = Integer.parseInt(studentId);
		int page = 1;
		int pageTotal = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		try {
			pageTotal = studentService.getNumOfMessagePrivate(sid) / 10 + 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("获取message条数失败");
			e.printStackTrace();
		}
		List<Message> list = studentService.getPrivateMessage(page, sid);
		model.addAttribute("page", page);
		System.out.println("page" + page);
		model.addAttribute("pagetotal", pageTotal);
		System.out.println("pageTotal" + pageTotal);
		model.addAttribute("list", list);
		model.addAttribute("studentId", studentId);
		return "sgetSelfInformation.jsp";
	}

	@RequestMapping("/godetails")
	public String godetails(HttpServletRequest request, HttpServletResponse response, String page, String studentId,
			Model model) throws Exception {
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		Message message = messageService.getMessageById(messageId);
		int sid = Integer.parseInt(studentId);
		Message2student message2student = m2sService.getMessage2studentById(messageId, sid);
		if (message.getIsPrivate() == 1 && message2student == null) {
			PrintWriter out = response.getWriter();
			out.print("<script language='javascript'>alert('您没有访问权限！');window.location='index.do?studentId=" + studentId
					+ "&page=" + page + "';</script>");
			out.flush();
			out.close();
			return null;
		}
		if (message2student != null) {
			if (message2student.getIsAcceptInterview() == -1 && message2student.getIsAcceptOffer() == -1
					&& message2student.getIsAcceptTest() == -1) {
				model.addAttribute("has", 1);
			} else {

				String result = "";
				if ("笔试通知".equals(message.getType().trim())) {
					if (message2student.getIsAcceptTest() == 0) {
						result = "已接受";
					} else {
						result = "已拒绝";
					}
				} else if ("面试通知".equals(message.getType().trim())) {
					if (message2student.getIsAcceptInterview() == 0) {
						result = "已接受";
					} else {
						result = "已拒绝";
					}
				} else {
					if (message2student.getIsAcceptOffer() == 0) {
						result = "已接受";
					} else {
						result = "已拒绝";
					}
				}
				model.addAttribute("has", -1);
				model.addAttribute("state", result);
			}
		}
		List<Message2student> list = m2sService.getAllStudent(messageId);
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		model.addAttribute("pp", request.getParameter("pp"));
		model.addAttribute("studentId", studentId);
		model.addAttribute("page", page);
		System.out.println("getdetails:page" + page);
		return "sinterviewInformation.jsp";
	}

	@RequestMapping("/accept")
	public String accept(HttpServletRequest request, HttpServletResponse response, String studentId, Model model)
			throws Exception {
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		Message message = messageService.getMessageById(messageId);
		int sid = Integer.parseInt(studentId);
		if ("笔试通知".equals(message.getType().trim())) {
			m2sService.acceptTest(messageId, sid, 0);
		} else if ("面试通知".equals(message.getType().trim())) {
			m2sService.acceptInterview(messageId, sid, 0);
		} else {
			m2sService.acceptOffer(messageId, sid, 0);
		}
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>alert('您已成功接受此通知，请做好准备！');window.location='getSelf.do?studentId="
				+ studentId + "';</script>");
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping("/refuse")
	public String refuse(HttpServletRequest request, HttpServletResponse response, String studentId, Model model)
			throws Exception {
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		Message message = messageService.getMessageById(messageId);
		int sid = Integer.parseInt(studentId);
		System.out.println("refuse sid:"+sid);
		if ("笔试通知".equals(message.getType().trim())) {
			m2sService.acceptTest(messageId, sid, 1);
		} else if ("面试通知".equals(message.getType().trim())) {
			m2sService.acceptInterview(messageId, sid, 1);
		} else {
			studentService.addTimesOfBreakContact(sid);
			m2sService.acceptOffer(messageId, sid, 1);
		}
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>alert('您已成功拒绝此通知！');window.location='getSelf.do?studentId=" + studentId
				+ "';</script>");
		out.flush();
		out.close();
		return null;
	}

}
