package com.seu.vCampus.Client.AcademicAffairs.Admin;
import com.seu.vCampus.Client.AcademicAffairs.Utils.TableButtonRender;
import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class QueryCoursesPanel extends JPanel {

    public QueryCoursesPanel(AdminMainPanel amP) {
        Common messenger = Common.getInstance();
        Person admin = new Person();
        admin.setType(Message.MESSAGE_TYPE.TYPE_QUERY_ALL_COURSES);
        messenger.getIO().SendMessages(admin);
        admin = (Person) messenger.getIO().ReceiveMessage();
        if(admin.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS && !admin.getCourses().isEmpty()) {
            ArrayList<Course> cs = admin.getCourses();
            String[] columnNames = {"课程编号", "课程名", "学期", "教师一卡通号", "教师姓名",
                    "上课时间", "上课地点", "课程类型", "学分", "是否考试", "人数上限","现有学生数","查看","操作"};
            Object[][] data = new Object[cs.size()][14];
            for(int i = 0; i < cs.size(); i++) {
                Course c = cs.get(i);
                data[i][0] = c.getCourseNumber().split("-")[0];
                data[i][1] = c.getCourseName();
                data[i][2] = c.getCourseSemester();
                data[i][3] = c.getLecturerECardNumber();
                data[i][4] = c.getCourseLecturer();
                data[i][5] = c.getCourseTime();
                data[i][6] = c.getCoursePlace();
                data[i][7] = c.getCourseType();
                data[i][8] = c.getCourseCredit();
                if(c.isExam()) data[i][9] = "是";
                else data[i][9] = "否";
                data[i][10] = c.getMaximumStudents();
                data[i][11] = c.getEnrolledStudents();
                if(c.isGradeAdded()) {
                    data[i][12] = "查看成绩册";
                    data[i][13] = "无法修改";
                }
                else {
                    data[i][12] = "查看学生册";
                    data[i][13] = "修改信息";
                }
            }
            JTable coursesTable = new JTable(data,columnNames);
            coursesTable.setLayout(new BorderLayout());
            coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableUtils.FitTableColumns(coursesTable);
            coursesTable.setFont(new Font("微软雅黑",Font.PLAIN,16));
            coursesTable.getColumnModel().getColumn(12).setCellRenderer(new TableButtonRender());
            coursesTable.getColumnModel().getColumn(12).setCellEditor(new AdminGradesButton(coursesTable,
                    amP));
            coursesTable.getColumnModel().getColumn(13).setCellRenderer(new TableButtonRender());

            coursesTable.setRowHeight(40);
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            this.setVisible(true);
        }
        else add(new JLabel("暂无课程"));
    }
}
