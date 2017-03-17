package com.recruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.Message.RecipientType;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recruit.pojo.M2sList;
import com.recruit.pojo.Message;
import com.recruit.pojo.Message2student;
import com.recruit.pojo.Student;
import com.recruit.pojo.Teacher;
import com.recruit.service.M2sService;
import com.recruit.service.MessageService;
import com.recruit.service.StudentService;
import com.recruit.service.TeacherService;
import com.recruit.util.CheckSession;
import com.recruit.util.MailUtil;

@Controller
@RequestMapping("/teacher")
public class TeacherSideController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private M2sService m2sService;
	@Autowired
	private StudentService studentService;

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

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

	@RequestMapping("/tlogin")
	public String login(String teacherId, String password, HttpSession session, HttpServletRequest request, Model model,
			HttpServletResponse response) {

		Teacher teacher = teacherService.getTeacherById(Integer.parseInt(teacherId));
		PrintWriter out;
		try {
			out = response.getWriter();
			if (teacher == null) {
				out.print("<script language='javascript'>alert('无该用户！');" + "window.location='tlogin.jsp';</script>");
				return null;
			} else if (!password.equals(teacher.getPassword().trim())) {
				out.print("<script language='javascript'>alert('密码错误！');" + "window.location='tlogin.jsp';</script>");
				return null;
			} else if (password.equals(teacher.getPassword().trim())) {
				session.setAttribute("teacherId", teacherId);
				return "infolist.do";
			} else {
				out.print("<script language='javascript'>alert('未知错误！');" + "window.location='tlogin.jsp';</script>");
			}
			return "tlogin.jsp";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getWriter失败！");
			return "tlogin.jsp";
		}

	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "tlogin.jsp";
	}

	@RequestMapping("/infolist")
	public String infolist(HttpServletRequest request, HttpSession session, HttpServletResponse response, Model model) {
		// if(checkSession.checkSession(session, response)){
		// List<Message> mList = messageService.selectAll();
		// model.addAttribute("messagelist", mList);
		// int count = 10;
		// if (request.getParameter("count") != null) {
		// // System.out.println(request.getParameter("count") + "!!!");
		// // 确保count为进一的整十
		// count = (Integer.parseInt(request.getParameter("count").trim()) + 9)
		// / 10 * 10;
		// String type = request.getParameter("type");
		// // System.out.println(type);
		// // System.out.println(("1".equals(type)));
		// if ("-1".equals(type))
		// count = count - 10;
		// if ("1".equals(type))
		// count = count + 10;
		// }
		// if (count >= mList.size()) {
		// count = mList.size();
		// model.addAttribute("full", -1);
		// }
		// // System.out.println(count);
		// model.addAttribute("count", count);
		// return "infolist.jsp";
		// }
		// else {
		// return null;
		// }
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
		return "infolist.jsp";
	}

	@RequestMapping("/infodetails")
	public String infodetails(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			Model model) {
		int messageId = Integer.parseInt(request.getParameter("messageId"));

		Message message = messageService.getMessageById(messageId);

		List<Message2student> infoViewList = m2sService.getAllStudent(messageId);

		model.addAttribute("message", message);
		model.addAttribute("viewlist", infoViewList);

		return "infodetails.jsp";

	}

	@RequestMapping("/infofeedback")
	public String infofeedback(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			Model model) {
		int messageId = Integer.parseInt(request.getParameter("messageId"));

		Message message = messageService.getMessageById(messageId);

		List<Message2student> infoViewList = m2sService.getAllStudent(messageId);

		model.addAttribute("message", message);
		model.addAttribute("viewlist", infoViewList);

		return "infofeedback.jsp";

	}

	@RequestMapping("/newInform")
	public @ResponseBody String newInform(Message message, M2sList infoViewList, HttpSession session, HttpServletResponse response) {
		
		PrintWriter out;
		try {
			out = response.getWriter();
			
			for(Message2student view : infoViewList.getInfoViewList()){
				//判断该学生毁约次数是否大于两次
				int timeOfBreakContact = 0;
				try {
					timeOfBreakContact = studentService.getTimesOfBreakContact(view.getStudentId());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("检索"+view.getStudentId()+"的毁约次数失败！");
					return null;
				}
				if(timeOfBreakContact >= 2 ){
					
					out.print("<script language='javascript'>alert('学号为"+view.getStudentId()+"的同学已毁约两次！');" + "window.location='newInform.jsp';</script>");
					return null;
				}
			}
			
			// 获取当前日期时间并加入到message中
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String curTime = format.format(date);
			message.setReleaseTime(curTime);
			
			
			int teacherId = Integer.parseInt(session.getAttribute("teacherId").toString());
			message.setIssuer(teacherId);
			messageService.insertSelective(message);
			int messageId = message.getMessageId();
			System.out.println("teacherId:" + teacherId);
			
			for (Message2student view : infoViewList.getInfoViewList()) {
				
				view.setMessageId(messageId);
				if (view.getMail() == ""){
					Student student = studentService.getStudentById(view.getStudentId());
					if (student != null){
						view.setMail(student.getMail().trim());
					}
						
				}

				try {
					System.out.println(view.getMail().trim());
					InternetAddress to = new InternetAddress(view.getMail().trim());
					MimeMessage mail = new MailUtil().SendMail();

					mail.setRecipient(RecipientType.TO, to);

					// 设置主题
					mail.setSubject(message.getType());

					// 设置邮件内容
					mail.setContent("请于" + message.getDeadline() + view.getNote() + "到" + message.getPlace(),
							"text/html;charset=UTF-8");
					Transport.send(mail);
					System.out.println("发送邮件成功");
					view.setIsSendMail(1);
					// 发送邮件成功

				} catch (Exception e) {
					// TODO Auto-generated catch block
					view.setIsSendMail(0); // 发送邮件失败
					// e.printStackTrace();
					System.out.println("发送邮件失败");
				} finally {
					// TODO: handle finally clause
					System.out.println("!!!"+view.getStudentId());
					m2sService.insertMessage2student(view);
					System.out.println("插入成功");
				}
			}
			return "infolist.do";
//			return "infodetails.do?messageId=" + messageId;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.out.println("getWriter失败！");
			return "newInform.jsp";
		}
		

	}
}
