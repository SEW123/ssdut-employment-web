package com.recruit.pojo;

public class Student {
    private Integer studentId;

    private String name;

    private String mail;

    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getTimesOfBreakContact() {
        return timesOfBreakContact;
    }

    public void setTimesOfBreakContact(Integer timesOfBreakContact) {
        this.timesOfBreakContact = timesOfBreakContact;
    }
}