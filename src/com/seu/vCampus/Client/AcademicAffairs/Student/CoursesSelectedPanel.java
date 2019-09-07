package com.seu.vCampus.Client.AcademicAffairs.Student;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CoursesSelectedPanel extends JPanel {
    private Common studentData;

    public CoursesSelectedPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        studentData = Common.getInstance();
        Person student = new Person();
        student.setECardNumber(studentData.getUser().getECardNumber());
        student.setType(Message.MESSAGE_TYPE.TYPE_GET_COURSES_SELECTED);
        studentData.getIO().SendMessages(student);
        student = (Person) studentData.getIO().ReceiveMessage();
        if(student.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
            ArrayList<Course> courses = new ArrayList<Course>(student.getCourses());
            String[] columnNames = {"课程编号", "课程名", "教师", "学期", "上课地点", "上课时间", "课程类型",
                    "学分", "是否考试"};
            Object[][] data = new Object[courses.size()][9];
            for(int i = 0; i < courses.size(); i++) {
                Course c = courses.get(i);
                data[i][0] = c.getCourseNumber().split("-")[0];
                data[i][1] = c.getCourseName();
                data[i][2] = c.getCourseLecturer();
                data[i][3] = c.getCourseSemester();
                data[i][4] = c.getCoursePlace();
                data[i][5] = c.getCourseTime();
                data[i][6] = c.getCourseType();
                data[i][7] = c.getCourseCredit();
                if(c.isExam()) data[i][8] = "是";
                else data[i][8] = "否";
            }
            JTable coursesTable = new JTable(data,columnNames);
            coursesTable.setAutoResizeMode(5);
            coursesTable.setFocusable(false);
            coursesTable.setRowSelectionAllowed(true);
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            coursesTable.setFillsViewportHeight(true);
            this.add(coursesTable);
        }

    }

    public CoursesSelectedPanel(LayoutManager layout, boolean isDoubleBuffered, String semester) {
        super(layout, isDoubleBuffered);
        studentData = Common.getInstance();
        Person student = new Person();
        student.setECardNumber(studentData.getUser().getECardNumber());
        student.setType(Message.MESSAGE_TYPE.TYPE_GET_COURSES_SELECTED);
        Course c = new Course();
        c.setCourseSemester(semester);
        ArrayList<Course> cs = new ArrayList<Course>();
        cs.add(c);
        student.setCourses(cs);
        studentData.getIO().SendMessages(student);
        student = (Person) studentData.getIO().ReceiveMessage();
        if(student.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
            System.out.println("SUCCESS");
            ArrayList<Course> courses = new ArrayList<Course>(student.getCourses());
            String[] columnNames = {"课程编号", "课程名", "教师", "上课地点", "上课时间", "课程类型", "学分", "是否考试"};
            Object[][] data = new Object[courses.size()][8];
            for(int i = 0; i < courses.size(); i++) {
                Course temp = courses.get(i);
                data[i][0] = temp.getCourseNumber().split("-")[0];
                data[i][1] = temp.getCourseName();
                data[i][2] = temp.getCourseLecturer();
                data[i][3] = temp.getCoursePlace();
                data[i][4] = temp.getCourseTime();
                data[i][5] = temp.getCourseType();
                data[i][6] = temp.getCourseCredit();
                if(temp.isExam()) data[i][7] = "是";
                else data[i][7] = "否";
            }
            JTable coursesTable = new JTable(data,columnNames);
            coursesTable.setAutoResizeMode(5);
            coursesTable.setFocusable(false);
            coursesTable.setRowSelectionAllowed(true);
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            coursesTable.setFillsViewportHeight(true);
            this.add(coursesTable);
        }
    }
}
