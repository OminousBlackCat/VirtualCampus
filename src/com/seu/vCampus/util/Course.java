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
    private int courseGrade;
    private boolean isConflict;
    private boolean isExam;
    private boolean gradeAdded;
    private String examTime;
    private String examPlace;
    private String studentName;

    /**
     * Constructor for querying courses that have exams.
     * @param courseNumber Course Number
     * @param courseName Course Name
     * @param courseSemester Course Semester
     * @param courseLecturer Course Lecturer
     */
    public Course(String courseNumber, String courseName, String courseSemester, String courseLecturer) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.courseLecturer = courseLecturer;
    }

    /**
     *Initialize Message type as null, since the specific action to be performed is unknown.
     */
    public Course(){
        this.Type = MESSAGE_TYPE.TYPE_NULL; //Default type
    }

    public Course(String ecn, String courseNumber, int grade) {
        this.setECardNumber(ecn);
        this.setCourseNumber(courseNumber);
        this.setCourseGrade(grade);
    }

    public Course(String semester) {
        this.setCourseSemester(semester);
    }

    /**
     * Constructor for courses query use.
     * @param courseNumber Course Number
     * @param courseName Course Name
     * @param courseSemester Semester of this course
     * @param courseLecturer Lecturer
     * @param coursePlace Classroom or lecture hall of course
     * @param courseTime Time for course, should be formatted like '19-20-1'
     * @param courseCredit Course credit
     * @param courseType Type of course
     * @param maximumStudents Maximum student capacity
     * @param enrolledStudents Students enrolled
     */
    public Course(String courseNumber, String courseName, String courseSemester,
                  String courseLecturer, String coursePlace, String courseTime,
                  String courseCredit, String courseType, int maximumStudents,
                  int enrolledStudents, boolean isExam) {
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
        this.isExam = isExam;
    }

    /**
     * Constructor for grade use.
     * @param courseNumber Course Number
     * @param courseName Name of Course
     * @param courseSemester Semester of course
     * @param courseLecturer Lecturer
     * @param courseCredit Credit
     * @param courseType Type of course, to calculate GPA
     * @param courseGrade Grade
     */
    public Course(String courseNumber, String courseName, String courseSemester, String courseLecturer,
                  String courseCredit, String courseType, int courseGrade) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.courseLecturer = courseLecturer;
        this.courseCredit = courseCredit;
        this.courseType = courseType;
        this.courseGrade = courseGrade;
    }

    /**
     * Constructor for adding a new course
     * @param courseNumber Course Number
     * @param courseName Course Name
     * @param courseSemester Semester of this course
     * @param courseLecturer Lecturer
     * @param coursePlace Classroom or lecture hall of course
     * @param courseTime Time for course, should be formatted like '19-20-1'
     * @param courseCredit Course credit
     * @param courseType Type of course
     * @param maximumStudents Maximum student capacity
     * @param isExam Whether there will be an exam
     */
    public Course(String courseNumber, String courseName, String courseSemester, String courseLecturer,
                  String coursePlace, String courseTime, String courseCredit, String courseType,
                  int maximumStudents, boolean isExam) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.courseLecturer = courseLecturer;
        this.coursePlace = coursePlace;
        this.courseTime = courseTime;
        this.courseCredit = courseCredit;
        this.courseType = courseType;
        this.maximumStudents = maximumStudents;
        this.isExam = isExam;
    }

    /**
     * Constructor for querying courses for lecturers.
     */
    public Course(String courseNumber, String courseName, String courseSemester, String courseLecturer,
                  String coursePlace, String courseTime, String courseCredit, String courseType,
                  int maximumStudents, int enrolledStudents, boolean isExam, boolean gradeAdded) {
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
        this.isExam = isExam;
        this.gradeAdded = gradeAdded;
    }

    /**
     * The following methods are just basic setters and getters.
     */

    public String getCourseType() { return courseType; }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

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

    public int getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(int courseGrade) {
        this.courseGrade = courseGrade;
    }

    public boolean isConflict() {
        return isConflict;
    }

    public void setConflict(boolean conflict) {
        isConflict = conflict;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public boolean isExam() {
        return isExam;
    }

    public void setExam(boolean exam) {
        isExam = exam;
    }

    public String getExamPlace() {
        return examPlace;
    }

    public void setExamPlace(String examPlace) {
        this.examPlace = examPlace;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
