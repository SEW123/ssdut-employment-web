package com.recruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	public String login(@Param("uid") String uid, Model model){
		
		model.addAttribute("uid", uid);
		return "sregister.jsp";
	}
	@RequestMapping("/sregister")
	public String register(@Param("name") String name,@Param("studentId") String studentId, 
			@Param("email") String email, @Param("uid") String uid,
			HttpServletResponse response, HttpSession session){
		Student student = studentService.getStudentById(Integer.parseInt(studentId));
		if(student != null){
			PrintWriter out;
			
			try {
				out = response.getWriter();
				if(student.getUid().equals(uid)){
					out.print("<script language='javascript'>alert('您已注册！');window.location='sregister.jsp';</script>");

				}
				else{
					out.print("<script language='javascript'>alert('该学号已被注册！');window.location='sregister.jsp';</script>");
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
		session.setAttribute("studentId", studentId);
		return "index.do";
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
