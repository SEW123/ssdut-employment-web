package com.recruit.pojo;

public class Student {
    private Integer studentId;

    private String name;

    private String mail;

    private String uid;

    private Integer timesOfBreakContact;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getTimesOfBreakContact() {
        return timesOfBreakContact;
    }

    public void setTimesOfBreakContact(Integer timesOfBreakContact) {
        this.timesOfBreakContact = timesOfBreakContact;
    }
}