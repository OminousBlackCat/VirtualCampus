package com.seu.vCampus.Client.AcademicAffairs.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainPanel extends JPanel {

    private QueryCoursesPanel qcP;
    private AddCoursePanel acP;
    private JTabbedPane switchPane;
    public AdminMainPanel(){
        this.setLayout(new BorderLayout());
        switchPane = new JTabbedPane();
        qcP = new QueryCoursesPanel(this);
        acP = new AddCoursePanel(this);
        add(switchPane);
        switchPane.addTab("查询学校课程",qcP);
        switchPane.addTab("添加课程",acP);
        switchPane.setTabPlacement(JTabbedPane.TOP);
    }

    public void refresh() {
        this.setLayout(new BorderLayout());
        switchPane.setVisible(false);
        qcP.setVisible(false);
        acP.setVisible(false);
        qcP = new QueryCoursesPanel(this);
        acP = new AddCoursePanel(this);
        switchPane = new JTabbedPane();
        switchPane.addTab("查询学校课程",qcP);
        switchPane.addTab("添加课程",acP);
        switchPane.setVisible(true);
        add(switchPane);
        switchPane.setTabPlacement(JTabbedPane.TOP);
    }
}
