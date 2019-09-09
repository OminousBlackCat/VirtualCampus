package com.seu.vCampus.Client.AcademicAffairs.Teacher;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TableButtonRender;
import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QuerySemesterCourses extends JPanel {
    public QuerySemesterCourses(String semester) {
        Common teacherMessage = Common.getInstance();
        Course tempCourse = new Course();
        tempCourse.setCourseSemester(semester);
        ArrayList<Course> courses = new ArrayList<Course>();
        courses.add(tempCourse);
        Person teacher = new Person();
        teacher.setECardNumber(teacherMessage.getUser().getECardNumber());
        teacher.setCourses(courses);
        teacher.setType(Message.MESSAGE_TYPE.TYPE_GET_LECTURER_COURSES);
        teacherMessage.getIO().SendMessages(teacher);
        courses =  ( (Person) teacherMessage.getIO().ReceiveMessage()).getCourses();
        if(!courses.isEmpty()) {
            String[] columnNames = {"课程编号", "课程名", "学期", "上课地点", "上课时间", "课程类型",
                    "学分", "是否考试","人数上限","现有学生数","操作","操作"};
            Object[][] data = new Object[courses.size()][12];

            for(int i = 0; i < courses.size(); i++) {
                Course c = courses.get(i);
                data[i][0] = c.getCourseNumber().split("-")[0];
                data[i][1] = c.getCourseName();
                data[i][2] = c.getCourseSemester();
                data[i][3] = c.getCoursePlace();
                data[i][4] = TableUtils.ParseCourseTime(c.getCourseTime());
                data[i][5] = c.getCourseType();
                data[i][6] = c.getCourseCredit();
                if(c.isExam()) data[i][7] = "是";
                else data[i][7] = "否";
                data[i][8] = c.getMaximumStudents();
                data[i][9] = c.getEnrolledStudents();
                data[i][10] = "查看学生";
                if(c.isGradeAdded()) {
                    data[i][11] = "成绩已录入";
                }
                else {
                    data[i][11] = "录入成绩";
                }
            }

            JTable coursesTable = new JTable(data, columnNames);
            coursesTable.setLayout(new BorderLayout());
            coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableUtils.FitTableColumns(coursesTable);
            coursesTable.setFont(new Font("微软雅黑",Font.PLAIN,16));
            coursesTable.getColumnModel().getColumn(10).setCellRenderer(new TableButtonRender());
            coursesTable.getColumnModel().getColumn(11).setCellRenderer(new TableButtonRender());
            coursesTable.setRowHeight(25);
            coursesTable.setDefaultEditor(Object.class, null);
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            this.setVisible(true);
        }
        else {
            JTextField noCourses = new JTextField("老师，您本学期没有课呢~");
            add(noCourses, BorderLayout.CENTER);
        }
    }
}
