package com.gokulrajvel.placementmonitoring.data.dto;

public class Student {
    private String name;
    private String email;
    private String contactNo;
    private String[] skill;
    private String batch;
    public Student(){}
    public void  settName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public String getContactNo() {
        return contactNo;
    }
    public void setSkill(String[] skill) {
        this.skill = skill;
    }
    public String[] getSkill() {
        return skill;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }
    public String getBatch() {
        return batch;
    }
}
