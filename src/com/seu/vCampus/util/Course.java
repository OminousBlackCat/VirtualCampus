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
    private String courseType;
    private int maximumStudents;
    private int enrolledStudents;

    public Course(String courseNumber, String courseName, String courseSemester,
                  String courseLecturer, String coursePlace, String courseTime,
                  String courseCredit, String courseType, int maximumStudents,
                  int enrolledStudents) {
        this.Type = MESSAGE_TYPE.TYPE_NULL;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.courseLecturer = courseLecturer;
        this.coursePlace = coursePlace;
        this.courseTime = courseTime;
        this.courseCredit = courseCredit;
        this.courseType = courseType;
        this.maximumStudents = maximumStudents;
        this.enrolledStudents = enrolledStudents;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    /**
     *Initialize Message type as null, since the specific action to be performed is unknown.
     */
    public Course(){
        this.Type = MESSAGE_TYPE.TYPE_NULL; //Default type
    }

    /**
     * The following methods are just basic setters and getters.
     */

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(String courseSemester) {
        this.courseSemester = courseSemester;
    }

    public String getCourseLecturer() {
        return courseLecturer;
    }

    public void setCourseLecturer(String courseLecturer) {
        this.courseLecturer = courseLecturer;
    }

    public String getCoursePlace() {
        return coursePlace;
    }

    public void setCoursePlace(String coursePlace) {
        this.coursePlace = coursePlace;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public int getMaximumStudents() {
        return maximumStudents;
    }

    public void setMaximumStudents(int maximumStudents) {
        this.maximumStudents = maximumStudents;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(int enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }
}
