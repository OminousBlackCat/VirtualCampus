package com.seu.vCampus.Client.AcademicAffairs.Student;

import com.seu.vCampus.Client.AcademicAffairs.Utils.DeselectCourseButton;
import com.seu.vCampus.Client.AcademicAffairs.Utils.SelectCourseButton;
import com.seu.vCampus.Client.AcademicAffairs.Utils.TableButtonRender;
import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class SelectCoursesPanel extends JPanel {

    public SelectCoursesPanel(String sem, StudentAcademicMainPanel grandPaPanel, CourseSelectionHallPanel fatherPanel) {
        Person student = new Person();
        Common studentData = Common.getInstance();
        Course course = new Course();
        course.setCourseSemester(sem);
        ArrayList<Course> courseList = new ArrayList<Course>();
        courseList.add(course);
        student.setCourses(courseList);
        student.setECardNumber(studentData.getUser().getECardNumber());
        student.setType(Message.MESSAGE_TYPE.TYPE_GET_COURSES_AVAILABLE);
        studentData.getIO().SendMessages(student);
        student = (Person) studentData.getIO().ReceiveMessage();

        if (student.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
            String[] columnNames = {"课程编号","课程名","教师","学期","上课地点","上课时间","课程类型","学分",
                    "是否考试","已选人数","上限","操作"};
            courseList = student.getCourses();
            Object[][] data = new Object[courseList.size()][12];
            for(int i = 0; i < courseList.size(); i++) {
                Course c = courseList.get(i);
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
                data[i][9] = c.getEnrolledStudents();
                data[i][10] = c.getMaximumStudents();
                if (c.getEnrolledStudents() >= c.getMaximumStudents()) {
                    data[i][11] = "已满";

                } else if (c.isConflict()) {
                    data[i][11] = "冲突";
                } else {
                    data[i][11] = "选课";
                }
            }
            JTable coursesTable = new JTable(data, columnNames);
            coursesTable.getColumnModel().getColumn(11).setCellRenderer(new TableButtonRender());
            coursesTable.getColumnModel().getColumn(11).setCellEditor(new SelectCourseButton(coursesTable, grandPaPanel, fatherPanel));
            coursesTable.setRowSelectionAllowed(false);
            coursesTable.setLayout(new BorderLayout());
            coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableUtils.FitTableColumns(coursesTable);
            coursesTable.setLocation(100,100);
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            coursesTable.setFont(new Font("微软雅黑",Font.PLAIN,16));
            coursesTable.setRowHeight(30);
            coursesTable.setDefaultEditor(Object.class, null);
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            this.setVisible(true);
        }
        else {
            add(new JTextField("暂无可选课程"),BorderLayout.CENTER);
        }
    }
}
