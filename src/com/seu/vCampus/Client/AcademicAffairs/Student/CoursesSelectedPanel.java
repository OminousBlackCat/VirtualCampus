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
            String[] columnNames = {"课程编号", "课程名", "学期", "上课地点", "上课时间", "课程类型", "学分", "是否考试"};
            Object[][] data = new Object[courses.size()][8];
            for(int i = 0; i < courses.size(); i++) {
                Course c = courses.get(i);
                data[i][0] = c.getCourseNumber().split("-")[0];
                data[i][1] = c.getCourseNumber();
                data[i][2] = c.getCourseSemester();
                data[i][3] = c.getCoursePlace();
                data[i][4] = c.getCourseTime();
                data[i][5] = c.getCourseType();
                data[i][6] = c.getCourseCredit();
                data[i][7] = c.isExam();
            }
            JTable coursesTable = new JTable(data,columnNames);
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            coursesTable.setFillsViewportHeight(true);
            this.add(coursesTable);
        }

    }
}
