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

    public SelectCoursesPanel(String sem, StudentAcademicMainPanel fatherPanel) {
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
            Vector<java.io.Serializable> rowData = new Vector<>();
            Vector<Vector<java.io.Serializable>> rowVec = new Vector<>();
            courseList = student.getCourses();
            Vector<String> columnNames = new Vector<>();
            columnNames.add("课程编号");
            columnNames.add("课程名");
            columnNames.add("教师");
            columnNames.add("学期");
            columnNames.add("上课地点");
            columnNames.add("上课时间");
            columnNames.add("课程类型");
            columnNames.add("学分");
            columnNames.add("已选人数");
            columnNames.add("上限");
            columnNames.add("操作");
            for (Course c : courseList) {
                rowData.clear();
                rowData.add(c.getCourseNumber().split("-")[0]);
                rowData.add(c.getCourseName());
                rowData.add(c.getCourseLecturer());
                rowData.add(c.getCourseSemester());
                rowData.add(c.getCoursePlace());
                rowData.add(c.getCourseTime());
                rowData.add(c.getCourseType());
                rowData.add(c.getCourseCredit());
                rowData.add(c.getEnrolledStudents());
                rowData.add(c.getMaximumStudents());
                if (c.getEnrolledStudents() >= c.getMaximumStudents()) {
                    rowData.add("已满");

                } else if (c.isConflict()) {
                    rowData.add("冲突");
                } else {
                    rowData.add("选课");
                }
                rowVec.add(rowData);
            }

            DefaultTableModel defaultModel = new DefaultTableModel(rowVec, columnNames);
            JTable coursesTable = new JTable(rowVec,columnNames);
            coursesTable.getColumnModel().getColumn(10).setCellRenderer(new TableButtonRender());
            coursesTable.getColumnModel().getColumn(10).setCellEditor(new SelectCourseButton(coursesTable,fatherPanel));
            coursesTable.setRowSelectionAllowed(false);
            coursesTable.setLayout(new BorderLayout());
            coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableUtils.FitTableColumns(coursesTable);
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            coursesTable.setFont(new Font("楷体",Font.PLAIN,14));
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            this.setVisible(true);
        }
        else {
            add(new JTextField("暂无可选课程"),BorderLayout.CENTER);
        }
    }
}
