package com.seu.vCampus.Client.AcademicAffairs.Admin;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TabbedPaneUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class AdminMainPanel extends JPanel{


    private JTabbedPane jTabbedPane;
    private TabbedPaneUI tabbedPaneUI;
    private AddCoursePanel addCoursePanel;
    private AddExams addExamsPanel;
    private ChangeCourseInfo changeCourseInfo;
    private ChangeGrade changeGrade;
    public AdminMainPanel(){

        setBounds(100, 100, 370, 475);
        setLayout(new BorderLayout());

        addCoursePanel=new AddCoursePanel();
        addExamsPanel=new AddExams();
        changeCourseInfo=new ChangeCourseInfo();
        changeGrade=new ChangeGrade();
        jTabbedPane=new JTabbedPane();
        tabbedPaneUI=new TabbedPaneUI(Color.GRAY,Color.WHITE);
        jTabbedPane.setUI(tabbedPaneUI);
        jTabbedPane.addTab("添加课程",null,addCoursePanel,null);
        jTabbedPane.addTab("添加考试",null,addExamsPanel,null);
        jTabbedPane.addTab("修改课程信息",null,changeCourseInfo,null);
        jTabbedPane.addTab("修改成绩",null,changeGrade,null);
        add(jTabbedPane,BorderLayout.CENTER);
        jTabbedPane.setTabPlacement(JTabbedPane.TOP);
        jTabbedPane.setVisible(true);
        this.setVisible(true);
    }

}
