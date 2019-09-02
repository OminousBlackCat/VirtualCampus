package com.seu.vCampus.util;

import java.util.ArrayList;

public class Person extends Message{
    private String Name;
    private String StudentNumber;
    private String Sex;
    private short AuthorityLevel;
    private short LendBooksNumber;
    private short ECardBalance;
    private String passWord;
    private ArrayList<Course> courses;

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String getPassWord() { return passWord; }

    public void setPassWord(String passWord) { this.passWord = passWord; }

    public String getSex() { return Sex; }

    public void setSex(String sex) { Sex = sex; }

    public Person(){
        this.Type = MESSAGE_TYPE.TYPE_PERSON;
    }

    public String getName() {
        return Name;
    }

    public String getStudentNumber() {
        return StudentNumber;
    }

    public short getAuthorityLevel() {
        return AuthorityLevel;
    }

    public short getLendBooksNumber() {
        return LendBooksNumber;
    }

    public short getECardBalance() {
        return ECardBalance;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setStudentNumber(String studentNumber) {
        StudentNumber = studentNumber;
    }

    public void setAuthorityLevel(short authorityLevel) {
        AuthorityLevel = authorityLevel;
    }

    public void setLendBooksNumber(short lendBooksNumber) {
        LendBooksNumber = lendBooksNumber;
    }

    public void setECardBalance(short ECardBalance) {
        this.ECardBalance = ECardBalance;
    }
}
