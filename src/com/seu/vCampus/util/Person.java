package com.seu.vCampus.util;

import java.util.ArrayList;

public class Person extends Message{
    private String Name;
    private String StudentNumber;
    private String Sex;
    private USER_GROUP AuthorityLevel;
    private short LendBooksNumber;
    private double ECardBalance;
    private String passWord;
    private ArrayList<Course> courses;

    private double GPA;


    public Person(){
        this.Type = MESSAGE_TYPE.TYPE_QUERY_PERSON;
        this.AuthorityLevel = USER_GROUP.GROUP_STUDENT;
    }

    public enum USER_GROUP{
        GROUP_STUDENT(0),
        GROUP_TEACHER(1),
        GROUP_SHOP_MANAGER(2),
        GROUP_LIBRARY_MANAGER(3),
        GROUP_USER_MANAGER(4),
        ;

        private final int value;
        USER_GROUP(int v){
            this.value = v;
        }

        public int valueOf(){
            return value;
        }


    }

    public Person(String lecturerECN) {
        this.setECardNumber(lecturerECN);
    }

    public Person(String lecturerECN, String semester) {
        this.setECardNumber(lecturerECN);
        courses = new ArrayList<Course>();
        courses.add(new Course(semester));
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
                if(c.getCourseType().equals("必修")) {
                    int g = c.getCourseGrade();
                    double GP = 0.00;
                    double credit = Double.parseDouble( c.getCourseCredit());
                    if (g >= 60) {
                        if (g >= 100) {
                            GP = 4.8;
                        } else {
                            GP += (g - 50) / 10;
                            int r = g % 10;
                            if (r >= 0 && r < 3) {
                                ;
                            } else if (r >= 3 && r < 6) {
                                GP += 0.5;
                            } else {
                                GP += 0.8;
                            }
                        }
                    }

                    GPSum += GP * credit;
                    credSum += credit;
                }
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

    public USER_GROUP getAuthorityLevel() {
        return AuthorityLevel;
    }

    public short getLendBooksNumber() {
        return LendBooksNumber;
    }

    public double getECardBalance() {
        return ECardBalance;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setStudentNumber(String studentNumber) {
        StudentNumber = studentNumber;
    }

    public void setAuthorityLevel(USER_GROUP authorityLevel) {
        AuthorityLevel = authorityLevel;
    }
    public void setAuthorityLevel(int value){
        switch (value){
            case 0:
                AuthorityLevel = USER_GROUP.GROUP_STUDENT;
                break;
            case 1:
                AuthorityLevel = USER_GROUP.GROUP_TEACHER;
                break;
            case 2:
                AuthorityLevel = USER_GROUP.GROUP_SHOP_MANAGER;
                break;
            case 3:
                AuthorityLevel = USER_GROUP.GROUP_LIBRARY_MANAGER;
                break;
            case 4:
                AuthorityLevel = USER_GROUP.GROUP_USER_MANAGER;
                break;
        }
    }

    public void setLendBooksNumber(short lendBooksNumber) {
        LendBooksNumber = lendBooksNumber;
    }

    public void setECardBalance(double ECardBalance) {
        this.ECardBalance = ECardBalance;
    }

    public double getGPA() {
        return GPA;
    }
}
