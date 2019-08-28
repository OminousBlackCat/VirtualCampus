package com.seu.vCampus.util;

/**
 * @className Course
 * @author Jamie
 * @version 1.0
 * @createdTime 8/26/2019 9:36 PM
 * @description TODO
 */

public class Course extends Message{
    private String courseNumber;
    private String courseName;
    private String courseSemester;
    private String courseLecturer;
    private String coursePlace;
    private String courseTime;
    private String courseCredit;
    private int maximumStudents;
    private int enrolledStudents;

    public Course(){
        this.Type = MESSAGE_TYPE.TYPE_NULL; //默认类型为NULL
    }
}
