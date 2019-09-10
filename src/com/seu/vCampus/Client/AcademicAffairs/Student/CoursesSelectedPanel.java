package com.seu.vCampus.Client.AcademicAffairs.Student;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TableButtonRender;
import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
                data[i][5] = TableUtils.ParseCourseTime(c.getCourseTime());
                data[i][6] = c.getCourseType();
                data[i][7] = c.getCourseCredit();
                if(c.isExam()) data[i][8] = "是";
                else data[i][8] = "否";
            }
            JTable coursesTable = new JTable(data,columnNames);
            coursesTable.setLayout(new BorderLayout());
            coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableUtils.FitTableColumns(coursesTable);
            coursesTable.setFont(new Font("微软雅黑",Font.PLAIN,16));
            coursesTable.setRowHeight(30);
            coursesTable.setDefaultEditor(Object.class, null);
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            this.setVisible(true);

        }

    }

    public CoursesSelectedPanel(String semester,StudentAcademicMainPanel grandPaPanel,CourseSelectionHallPanel fatherPanel) {
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
            String[] columnNames = {"课程编号", "课程名", "教师", "学期", "上课地点", "上课时间", "课程类型",
                    "学分", "是否考试","操作"};
            Object[][] data = new Object[courses.size()][10];
            for(int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                data[i][0] = course.getCourseNumber().split("-")[0];
                data[i][1] = course.getCourseName();
                data[i][2] = course.getCourseLecturer();
                data[i][3] = course.getCourseSemester();
                data[i][4] = course.getCoursePlace();
                data[i][5] = TableUtils.ParseCourseTime(course.getCourseTime());
                data[i][6] = course.getCourseType();
                data[i][7] = course.getCourseCredit();
                System.out.println(course.isExam());
                if(course.isExam()) data[i][8] = "是";
                else data[i][8] = "否";
                if(course.isGradeAdded()) {
                    data[i][9] = "有分";
                }
                else {
                    data[i][9] = "退课";
                }
            }
            JTable coursesTable = new JTable(data,columnNames);
            coursesTable.setLayout(new BorderLayout());
            coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableUtils.FitTableColumns(coursesTable);
            coursesTable.getColumnModel().getColumn(9).setCellRenderer(new TableButtonRender());
            coursesTable.getColumnModel().getColumn(9).setCellEditor(new DeselectCourseButton(coursesTable,grandPaPanel,fatherPanel));
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            coursesTable.setFont(new Font("微软雅黑",Font.PLAIN,16));
            coursesTable.setRowHeight(30);
            coursesTable.setDefaultEditor(Object.class, null);
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            this.setVisible(true);
        }
    }
}
