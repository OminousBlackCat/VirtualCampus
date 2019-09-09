package com.seu.vCampus.Client.AcademicAffairs.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMainPanel extends JPanel {
    private QueryAllMyCoursesPanel allMyCoursesPanel;
    private TeacherScheduleTable teacherScheduleTable;
    private JTabbedPane switchPane;
    public TeacherMainPanel(){
        this.setLayout(new BorderLayout());
        allMyCoursesPanel = new QueryAllMyCoursesPanel();
        teacherScheduleTable = new TeacherScheduleTable();
        switchPane = new JTabbedPane();
        switchPane.addTab("我的课程",allMyCoursesPanel);
        switchPane.addTab("教师课表",teacherScheduleTable);
        add(switchPane);
        switchPane.setTabPlacement(JTabbedPane.TOP);
    }

}
