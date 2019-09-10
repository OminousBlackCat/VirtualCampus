package com.seu.vCampus.Client.AcademicAffairs.Student;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QueryExams extends JPanel {
    private Common studentData;


    public QueryExams() {
        studentData = Common.getInstance();
        Person student = new Person();
        student.setECardNumber(studentData.getUser().getECardNumber());
        student.setType(Message.MESSAGE_TYPE.TYPE_GET_STUDENT_EXAMS_INFO);
        studentData.getIO().SendMessages(student);
        student = (Person) studentData.getIO().ReceiveMessage();
        if (student.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
            ArrayList<Course> courses = new ArrayList<Course>(student.getCourses());
            if(!courses.isEmpty()) {
                String[] columnNames = {"课程编号", "课程名", "教师", "学期", "课程类型",
                        "学分", "考试时间", "考试地点"};
                Object[][] data = new Object[courses.size()][8];
                for (int i = 0; i < courses.size(); i++) {
                    Course c = courses.get(i);
                    data[i][0] = c.getCourseNumber().split("-")[0];
                    data[i][1] = c.getCourseName();
                    data[i][2] = c.getCourseLecturer();
                    data[i][3] = c.getCourseSemester();
                    data[i][4] = c.getCourseType();
                    data[i][5] = c.getCourseCredit();
                    data[i][6] = c.getExamTime();
                    data[i][7] = c.getExamPlace();
                }
                JTable coursesTable = new JTable(data, columnNames);
                coursesTable.setLayout(new BorderLayout());
                coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                TableUtils.FitTableColumns(coursesTable);
                coursesTable.setFont(new Font("微软雅黑",Font.PLAIN,16));
                coursesTable.setRowHeight(30);
               /* coursesTable.setDefaultEditor(Object.class, null);*/
                JScrollPane scrollPane = new JScrollPane(coursesTable);
                setLayout(new BorderLayout());
                add(scrollPane, BorderLayout.CENTER);
                this.setVisible(true);
            }
            else
            {
                this.add(new JLabel("考试安排还没公布呢~"));
                this.setVisible(true);
            }
        }
        else
        {
            this.add(new JLabel("错误！"));
            this.setVisible(true);
        }
    }
}
