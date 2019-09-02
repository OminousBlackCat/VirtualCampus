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
    private double GPA;

    public Person(){
        this.Type = MESSAGE_TYPE.TYPE_PERSON;
    }

    public void setSemester(String sem) {
        Course c = new Course();
        c.setCourseSemester(sem);
        courses.add(c);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void calculateGPA() {
        if(courses.isEmpty()) {
            this.GPA = 0.00;
        }
        else {
            double GPSum = 0.00;
            double credSum = 0.00;
            for (Course c : courses) {
                int g = c.getCourseGrade();
                double GP = 0.00;
                double credit = Double.parseDouble( c.getCourseCredit());

                if (g >= 60) {
                    if (g >= 100) {
                        GP = 4.8;
                    }
                    else {
                        GP += (g - 50)/10;
                        int r = g % 10;
                        if(r >= 0 && r < 3) {
                            ;
                        }
                        else if (r >= 3 && r < 6) {
                            GP += 0.5;
                        }
                        else {
                            GP += 0.8;
                        }
                    }
                }
                GPSum += GP * credit;
                credSum += credit;

            }
            this.GPA = GPSum / credSum;
        }
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String getPassWord() { return passWord; }

    public void setPassWord(String passWord) { this.passWord = passWord; }

    public String getSex() { return Sex; }

    public void setSex(String sex) { Sex = sex; }

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
