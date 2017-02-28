package com.recruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	@RequestMapping("/login")
	public String login(HttpServletResponse response, HttpSession session, String studentId, String password,
			Model model) throws IOException {
		Student student = studentService.check(Integer.parseInt(studentId), password);
		PrintWriter out = response.getWriter();
		if (student == null) {
			out.print("<script language='javascript'>alert('用户名或密码错误!!!');window.location='../slogin.jsp';</script>");
			return null;
		}
		session.setAttribute("studentId", studentId);
		return "index";
	}

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpSession session, Model model) {
		List<Message> list = messageService.selectAll();
		// System.out.println("-----------------------------");
		// System.out.println(list.size());
		model.addAttribute("list", list);
		int count = 10;
		if (request.getParameter("count") != null) {
			// 确保count为进一的整十
			count = (Integer.parseInt(request.getParameter("count").trim()) + 9) / 10 * 10;
			String type = request.getParameter("type");
			//System.out.println(type);
			//System.out.println(("1".equals(type)));
			if ("-1".equals(type))
				count = count - 10;
			if ("1".equals(type))
				count = count + 10;
		}
		if (count >= list.size()) {
			count = list.size();
			model.addAttribute("full", -1);
		}
		//System.out.println(count);
		model.addAttribute("count", count);
		return "sgetNotices.jsp";
	}

	@RequestMapping("/getSelf")
	public String getSelf(HttpServletRequest request, HttpSession session, Model model) {
		String studentId = (String) session.getAttribute("studentId");
		// System.out.println(studentId);
		List<Message> list = studentService.getAllMessage(Integer.parseInt(studentId));
		model.addAttribute("list", list);
		int count = 10;
		if (request.getParameter("count") != null) {
			// 确保count为进一的整十
			count = (Integer.parseInt(request.getParameter("count").trim()) + 9) / 10 * 10;
			String type = request.getParameter("type");
			System.out.println(type);
			System.out.println(("1".equals(type)));
			if ("-1".equals(type))
				count = count - 10;
			if ("1".equals(type))
				count = count + 10;
		}
		if (count >= list.size()) {
			count = list.size();
			model.addAttribute("full", -1);
		}
		System.out.println(count);
		model.addAttribute("count", count);
		return "sgetSelfInformation.jsp";
	}

	@RequestMapping("/godetails")
	public String godetails(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws Exception {
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		Message message = messageService.getMessageById(messageId);
		int studentId = Integer.parseInt((String) session.getAttribute("studentId"));
		Message2student message2student = m2sService.getMessage2studentById(messageId, studentId);
		if (message.getIsPrivate() == 1 && message2student == null) {
			PrintWriter out = response.getWriter();
			out.print("<script language='javascript'>alert('您没有访问权限！');window.location='index.do';</script>");
			out.flush();
			out.close();
			return null;
		}
		if (message2student != null && (message2student.getIsAcceptInterview() == -1
				&& message2student.getIsAcceptOffer() == -1 && message2student.getIsAcceptTest() == -1)) {
			model.addAttribute("has", 1);
		}
		List<Message2student> list = m2sService.getAllStudent(messageId);
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "sinterviewInformation.jsp";
	}

	@RequestMapping("/accept")
	public String accept(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws Exception {
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		Message message = messageService.getMessageById(messageId);
		int studentId = Integer.parseInt((String) session.getAttribute("studentId"));
		if ("笔试通知".equals(message.getType().trim())) {
			m2sService.acceptTest(messageId, studentId, 0);
		} else if ("面试通知".equals(message.getType().trim())) {
			m2sService.acceptInterview(messageId, studentId, 0);
		} else {
			m2sService.acceptOffer(messageId, studentId, 0);
		}
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>alert('您已成功接受此通知，请做好准备！');window.location='getSelf.do';</script>");
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping("/refuse")
	public String refuse(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws Exception {
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		Message message = messageService.getMessageById(messageId);
		int studentId = Integer.parseInt((String) session.getAttribute("studentId"));
		if ("笔试通知".equals(message.getType().trim())) {
			m2sService.acceptTest(messageId, studentId, 1);
		} else if ("面试通知".equals(message.getType().trim())) {
			m2sService.acceptInterview(messageId, studentId, 1);
		} else {
			m2sService.acceptOffer(messageId, studentId, 1);
		}
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>alert('您已成功拒绝此通知！');window.location='getSelf.do';</script>");
		out.flush();
		out.close();
		return null;
	}

}
