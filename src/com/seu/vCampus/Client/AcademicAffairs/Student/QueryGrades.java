package com.seu.vCampus.Client.AcademicAffairs.Student;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QueryGrades extends JPanel {
    private Common studentData;

    public QueryGrades() {
        studentData = Common.getInstance();
        Person student = new Person();
        student.setECardNumber(studentData.getUser().getECardNumber());
        student.setType(Message.MESSAGE_TYPE.TYPE_GET_GRADES);
        studentData.getIO().SendMessages(student);
        student = (Person) studentData.getIO().ReceiveMessage();
        if (student.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
            ArrayList<Course> courses = new ArrayList<Course>(student.getCourses());
            if(!courses.isEmpty()) {
                String[] columnNames = {"课程编号", "课程名", "教师", "学期", "课程类型",
                        "学分", "成绩" };
                Object[][] data = new Object[courses.size()][7];
                for (int i = 0; i < courses.size(); i++) {
                    Course c = courses.get(i);
                    data[i][0] = c.getCourseNumber().split("-")[0];
                    data[i][1] = c.getCourseName();
                    data[i][2] = c.getCourseLecturer();
                    data[i][3] = c.getCourseSemester();
                    data[i][4] = c.getCourseType();
                    data[i][5] = c.getCourseCredit();
                    data[i][6] = c.getCourseGrade();
                }
                JTable coursesTable = new JTable(data, columnNames);
                coursesTable.setLayout(new BorderLayout());
                coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                TableUtils.FitTableColumns(coursesTable);
                coursesTable.setFont(new Font("楷体", Font.PLAIN, 14));
                JScrollPane scrollPane = new JScrollPane(coursesTable);
                setLayout(new BorderLayout());
                add(scrollPane, BorderLayout.CENTER);
                this.setVisible(true);
            }
            else
            {
                this.add(new JTextField("暂无"));
                this.setVisible(true);
            }
        }
        else
        {
            this.add(new JTextField("错误"));
            this.setVisible(true);
        }
    }

}
