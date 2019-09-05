package com.seu.vCampus.Client.Home;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.util.ArrayList;

import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Person;

public class courseSelectForStu extends JPanel{


    private JPanel panel;
    private JPanel panel1;
    private JPanel coursepanel1;
    private JSplitPane splitPane;
    private JPanel selectpanel1;
    private JPanel coursepanel2;
    private JPanel selectpanel2;
    private JLabel label;

    private Person user;
    private Course course;
    private ArrayList<Course>  courseList;
    /**
     * Launch the application.
     */


    /**
     * Create the application.
     */
    public courseSelectForStu(){

        String[] termlist=new String[] {"第一学期","第二学期"};
        JComboBox term = new JComboBox(termlist) ;
        setLayout(new BorderLayout());
        add(term, BorderLayout.NORTH);
        panel = new JPanel();

        label = new JLabel("\u5DF2\u9009\u8BFE\u7A0B");
        panel.add(label);
        add(panel, BorderLayout.WEST);//已选课程列表

        splitPane = new JSplitPane();
        splitPane.setDividerLocation(250);


        coursepanel1 = new JPanel();//第一学期可选课程
        splitPane.setLeftComponent(coursepanel1);

        selectpanel1 = new JPanel();//第一学期选择按钮
        splitPane.setRightComponent(selectpanel1);

        coursepanel2 =new JPanel();//第二学期可选课程

        selectpanel2=new JPanel();//第二学期可选课程
        add(splitPane, BorderLayout.CENTER);

        term.addItemListener(new ItemListener(){//选择哪个学期
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED)
                {
                    if(term.getSelectedIndex()==0)
                    {
                        splitPane.setLeftComponent(coursepanel1);
                        splitPane.setRightComponent(selectpanel1);
                        splitPane.setDividerLocation(250);
                    }
                    else
                    {
                        splitPane.setLeftComponent(coursepanel2);
                        splitPane.setRightComponent(selectpanel2);
                        splitPane.setDividerLocation(250);
                    }
                }
            }

        });

    }

    public void refresh(){
        courseList =user.getCourses();
        int number = courseList.size();
        JTextField jtf;
        JButton jb;
        for(int i=0;i<number;i++){
            course=courseList.get(i);
            if(course.getCourseSemester()=="19-20-1"){
                jtf=new JTextField(course.getCourseNumber()+"    "+course.getCourseName()+"    "+course.getCourseSemester());
                coursepanel1.add(jtf);
                jb=new JButton("选择");
                jb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                         panel.add(new JLabel(course.getCourseName()));

                    }
                });
                selectpanel1.add(jb);
                f5();
            }
            else if(course.getCourseSemester()=="19-20-2"){
                jtf=new JTextField(course.getCourseNumber()+"    "+course.getCourseName()+"    "+course.getCourseSemester());
                coursepanel2.add(jtf);
                jb=new JButton("选择");
                jb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                selectpanel2.add(jb);
                f5();
            }
        }
    }
    public void f5(){
        panel1=new JPanel();
        courseList=user.getCourses();
        Course course1=new Course();
        int number=courseList.size();
        for(int i=0;i<number;i++){
            course1=courseList.get(i);
            if(){

            }
        }
    }
    /**
     * Initialize the contents of the frame.
     */

}
