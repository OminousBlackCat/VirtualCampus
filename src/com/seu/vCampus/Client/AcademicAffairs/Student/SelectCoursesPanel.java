package com.seu.vCampus.Client.AcademicAffairs.Student;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;
import org.hsqldb.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class SelectCoursesPanel extends JPanel {
    private JScrollPane sp;
    private Vector<java.io.Serializable> rowData;
    private Vector<Vector<java.io.Serializable>> rowVec;

    public SelectCoursesPanel(String sem) {
        Person student = new Person();
        Common studentData = Common.getInstance();
        Course course = new Course();
        course.setCourseSemester(sem);
        ArrayList<Course> courseList = new ArrayList<Course>();
        courseList.add(course);
        student.setCourses(courseList);
        student.setType(Message.MESSAGE_TYPE.TYPE_GET_COURSES_AVAILABLE);
        studentData.getIO().SendMessages(student);
        student = (Person) studentData.getIO().ReceiveMessage();

        if (student.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
            courseList = student.getCourses();
            sp.setLayout(new BorderLayout());
            Vector<String> columnNames = new Vector<>();
            columnNames.add("课程号");
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
                    JButton courseFull = new JButton("已满");
                    rowData.add(courseFull);
                    courseFull.setEnabled(false);

                } else if (c.isConflict()) {
                    JButton courseConflict = new JButton("冲突");
                    rowData.add(courseConflict);
                    courseConflict.setEnabled(false);
                } else {
                    JButton selectThisCourse = new JButton("选课");
                    rowData.add(selectThisCourse);
                    selectThisCourse.setEnabled(true);
                }
                rowVec.add(rowData);
            }
            DefaultTableModel defaultModel = new DefaultTableModel(rowVec, columnNames);
            JTable table = new JTable(defaultModel);
            TableUtils.FitTableColumns(table);
            table.setFont(new Font("楷体", Font.PLAIN, 14));
            sp.add(table, BorderLayout.CENTER);
            add(sp,BorderLayout.CENTER);
        }
        else {
            add(new JTextField("暂无可选课程"),BorderLayout.CENTER);
        }
    }
}
