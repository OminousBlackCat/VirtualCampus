package com.seu.vCampus.Client.AcademicAffairs.Admin;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TableButtonRender;
import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddExamsPanel extends JPanel {
    private Common common;
    private Person person;
    private Course course;
    private ArrayList<Course> courses;

    public AddExamsPanel(AdminMainPanel amP){
        Common messenger = Common.getInstance();
        Person admin = new Person();
        admin.setType(Message.MESSAGE_TYPE.TYPE_QUERY_EXAMINABLE_COURSES);
        messenger.getIO().SendMessages(admin);
        admin = (Person) messenger.getIO().ReceiveMessage();
        if(admin.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS && !admin.getCourses().isEmpty()) {
            ArrayList<Course> cs = admin.getCourses();
            String[] columnNames = {"课程编号", "课程名", "学期", "考试时间","考试地点","操作"};
            Object[][] data = new Object[cs.size()][14];
            for(int i = 0; i < cs.size(); i++) {
                Course c = cs.get(i);
                data[i][0] = c.getCourseNumber().split("-")[0];
                data[i][1] = c.getCourseName();
                data[i][2] = c.getCourseSemester();
                data[i][3] = c.getExamTime();
                data[i][4] = c.getExamPlace();
                data[i][5] = "提交";
            }
            JTable coursesTable = new JTable(data,columnNames);
            coursesTable.setLayout(new BorderLayout());
            coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableUtils.FitTableColumns(coursesTable);
            coursesTable.setFont(new Font("微软雅黑",Font.PLAIN,16));
            coursesTable.getColumnModel().getColumn(5).setCellRenderer(new TableButtonRender());
            coursesTable.getColumnModel().getColumn(5).setCellEditor(new AddExamButton(coursesTable,
                    amP));
            coursesTable.setRowHeight(40);
            JScrollPane scrollPane = new JScrollPane(coursesTable);
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            this.setVisible(true);
        }
        else add(new JLabel("暂无课程"));
    }
}
