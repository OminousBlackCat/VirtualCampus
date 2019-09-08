package com.seu.vCampus.Client.AcademicAffairs.Student;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class CoursesSelectedPanel extends JPanel {
    private Common studentData;

    public CoursesSelectedPanel() {
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
            coursesTable.setLayout(new BorderLayout());
            coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableUtils.FitTableColumns(coursesTable);
            coursesTable.setFont(new Font("楷体",Font.PLAIN,14));
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            this.setVisible(true);
        }

    }

    public CoursesSelectedPanel(String semester) {
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
            coursesTable.setLayout(new BorderLayout());
            coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableUtils.FitTableColumns(coursesTable);
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            coursesTable.setFont(new Font("楷体",Font.PLAIN,14));
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            this.setVisible(true);
        }
    }
}
