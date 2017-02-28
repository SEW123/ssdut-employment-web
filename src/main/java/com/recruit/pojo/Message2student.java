package com.recruit.pojo;

public class Message2student  {
	private Integer messageId;

    private Integer studentId;
    
    private String name;

    private String job;

    private String note;

    private String mail;

    private Integer isSendMail;

    private Integer isAcceptTest;

    private Integer isAcceptInterview;

    private Integer isAcceptOffer;

    private Integer isBreakContact;
    
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public Integer getIsSendMail() {
        return isSendMail;
    }

    public void setIsSendMail(Integer isSendMail) {
        this.isSendMail = isSendMail;
    }

    public Integer getIsAcceptTest() {
        return isAcceptTest;
    }

    public void setIsAcceptTest(Integer isAcceptTest) {
        this.isAcceptTest = isAcceptTest;
    }

    public Integer getIsAcceptInterview() {
        return isAcceptInterview;
    }

    public void setIsAcceptInterview(Integer isAcceptInterview) {
        this.isAcceptInterview = isAcceptInterview;
    }

    public Integer getIsAcceptOffer() {
        return isAcceptOffer;
    }

    public void setIsAcceptOffer(Integer isAcceptOffer) {
        this.isAcceptOffer = isAcceptOffer;
    }

    public Integer getIsBreakContact() {
        return isBreakContact;
    }

    public void setIsBreakContact(Integer isBreakContact) {
        this.isBreakContact = isBreakContact;
    }
    private String judge(int param){
		switch (isAcceptTest) {
		case -1:
			return "默认";
		case 0:
			return "接受";
		case 1:
			return "拒绝";

		default:
			return "未知错误";
		}
	}
	public String returnResult(String type){
		if("笔试通知".equals(type)){
			return judge(isAcceptTest);
		}
		else if("面试通知".equals(type)){
			return judge(isAcceptTest);
		}	
		else if("offer发放".equals(type)){
			return judge(isAcceptTest);
		}
		else
			return "未知错误";
		
	}
}