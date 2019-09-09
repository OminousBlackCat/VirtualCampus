package com.seu.vCampus.Client.AcademicAffairs.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMainPanel extends JPanel {
    public QueryAllMyCoursesPanel getAllMyCoursesPanel() {
        return allMyCoursesPanel;
    }

    private QueryAllMyCoursesPanel allMyCoursesPanel;
    private TeacherScheduleTable teacherScheduleTable;
    private JTabbedPane switchPane;
    public TeacherMainPanel(){
        this.setLayout(new BorderLayout());
        allMyCoursesPanel = new QueryAllMyCoursesPanel(this);
        teacherScheduleTable = new TeacherScheduleTable();
        switchPane = new JTabbedPane();
        switchPane.addTab("我的课程",allMyCoursesPanel);
        switchPane.addTab("教师课表",teacherScheduleTable);
        add(switchPane);
        switchPane.setTabPlacement(JTabbedPane.TOP);
    }
    public void refresh() {
        this.setLayout(new BorderLayout());
        allMyCoursesPanel.setVisible(false);
        teacherScheduleTable.setVisible(false);
        allMyCoursesPanel = new QueryAllMyCoursesPanel(this);
        teacherScheduleTable = new TeacherScheduleTable();
        switchPane.setVisible(false);
        switchPane = new JTabbedPane();
        switchPane.addTab("我的课程",allMyCoursesPanel);
        switchPane.addTab("教师课表",teacherScheduleTable);
        switchPane.setVisible(true);
        add(switchPane);
        allMyCoursesPanel.setVisible(true);
        teacherScheduleTable.setVisible(true);
    }
}
