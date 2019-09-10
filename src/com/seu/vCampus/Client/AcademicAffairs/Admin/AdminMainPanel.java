package com.seu.vCampus.Client.AcademicAffairs.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainPanel extends JPanel {

    private QueryCoursesPanel qcP;
    private AddCoursePanel acP;
    private AddExamsPanel aeP;
    private JTabbedPane switchPane;
    public AdminMainPanel(){
        this.setLayout(new BorderLayout());
        switchPane = new JTabbedPane();
        qcP = new QueryCoursesPanel(this);
        acP = new AddCoursePanel(this);
        aeP = new AddExamsPanel(this);
        add(switchPane);
        switchPane.addTab("查询学校课程",qcP);
        switchPane.addTab("考试信息",aeP);
        switchPane.addTab("添加课程",acP);
        switchPane.setTabPlacement(JTabbedPane.TOP);
    }

    public void refresh() {
        this.setLayout(new BorderLayout());
        int index = switchPane.getSelectedIndex();
        switchPane.setVisible(false);
        qcP.setVisible(false);
        acP.setVisible(false);
        aeP.setVisible(false);
        qcP = new QueryCoursesPanel(this);
        acP = new AddCoursePanel(this);
        aeP = new AddExamsPanel(this);
        switchPane = new JTabbedPane();
        switchPane.addTab("查询学校课程",qcP);
        switchPane.addTab("考试信息",aeP);
        switchPane.addTab("添加课程",acP);
        switchPane.setVisible(true);
        add(switchPane);
        switchPane.setSelectedIndex(index);
        switchPane.setTabPlacement(JTabbedPane.TOP);
    }
}
