package com.seu.vCampus.Client.AcademicAffairs.Admin;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class ChangeCourseInfo extends JPanel{

    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;

    private ArrayList<Course> courses;
    private Common commonData;
    private Person person;
    private Course course;
    private String courseNumber;
    private String courseName;
    private String courseSemester;
    private String courseLecturer;
    private String coursePlace;
    private String courseTime;
    private String courseCredit;
    private String courseType;
    private int maximumStudents;
    private String lecturerECardNumber;
    private int enrolledStudents;


    public ChangeCourseInfo(){

        /**
         * Initialize the contents of the frame.
         */
        setBounds(100, 100, 1000, 800);
        setLayout(null);

        JLabel label = new JLabel("课程号");
        label.setBounds(31, 60, 108, 18);
        add(label);

        JLabel lblCoursename = new JLabel("课程名");
        lblCoursename.setBounds(31, 110, 97, 18);
        add(lblCoursename);

        JLabel lblNewLabel = new JLabel("课程学期");
        lblNewLabel.setBounds(31, 160, 119, 18);
        add(lblNewLabel);

        JLabel lblCourselecturer = new JLabel("授课教师");
        lblCourselecturer.setBounds(31, 210, 119, 18);
        add(lblCourselecturer);

        JLabel lblCourseplace = new JLabel("上课地点");
        lblCourseplace.setBounds(31, 260, 108, 18);
        add(lblCourseplace);

        JLabel lblCoursetime = new JLabel("上课时间");
        lblCoursetime.setBounds(31, 310, 108, 18);
        add(lblCoursetime);

        JLabel lblMaximumstudents = new JLabel("最大学生数");
        lblMaximumstudents.setBounds(31, 360, 119, 18);
        add(lblMaximumstudents);

        JLabel lblCoursetype = new JLabel("课程类型");
        lblCoursetype.setBounds(31, 410, 97, 18);
        add(lblCoursetype);

        JLabel lblCoursecredit = new JLabel("课程学分");
        lblCoursecredit.setBounds(31, 460, 108, 18);
        add(lblCoursecredit);

        textField = new JTextField();
        textField.setBounds(183, 57, 86, 24);
        add(textField);
        textField.setColumns(10);
        Document dt=textField.getDocument();
        dt.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setCourseNumber(textField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        textField_1 = new JTextField();
        textField_1.setBounds(183, 107, 86, 24);
        add(textField_1);
        textField_1.setColumns(10);
        Document dt_1=textField_1.getDocument();
        dt_1.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setCourseName(textField_1.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        textField_2 = new JTextField();
        textField_2.setBounds(183, 157, 86, 24);
        add(textField_2);
        textField_2.setColumns(10);
        Document dt_2=textField_2.getDocument();
        dt_2.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setCourseSemester(textField_2.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        textField_3 = new JTextField();
        textField_3.setBounds(183, 207, 86, 24);
        add(textField_3);
        textField_3.setColumns(10);
        Document dt_3=textField_3.getDocument();
        dt_3.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setCourseLecturer(textField_3.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        textField_4 = new JTextField();
        textField_4.setBounds(183, 257, 86, 24);
        add(textField_4);
        textField_4.setColumns(10);
        Document dt_4=textField_4.getDocument();
        dt_4.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setCoursePlace(textField_4.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        textField_5 = new JTextField();
        textField_5.setBounds(183, 307, 86, 24);
        add(textField_5);
        textField_5.setColumns(10);
        Document dt_5=textField_5.getDocument();
        dt_5.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setCourseTime(textField_5.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        textField_6 = new JTextField();
        textField_6.setBounds(183, 357, 86, 24);
        add(textField_6);
        textField_6.setColumns(10);
        Document dt_6=textField_6.getDocument();
        dt_6.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setMaximumStudents(Integer.parseInt(textField_6.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        String [] str=new String[] {"必修","选修"};
        JComboBox comboBox = new JComboBox(str);
        comboBox.setBounds(183, 407, 86, 24);
        add(comboBox);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBox.getSelectedIndex()==0)
                    course.setCourseType("必修");
                else
                    course.setCourseType("选修");
            }
        });

        textField_7 = new JTextField();
        textField_7.setBounds(183, 457, 86, 24);
        add(textField_7);
        textField_7.setColumns(10);
        Document dt_7=textField_7.getDocument();
        dt_7.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setCourseCredit(textField_7.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        JLabel label_1 = new JLabel("老师一卡通");
        label_1.setBounds(31, 510, 108, 18);
        add(label_1);

        textField_8 = new JTextField();
        textField_8.setBounds(183, 507, 86, 24);
        add(textField_8);
        textField_8.setColumns(10);
        Document dt_8=textField_8.getDocument();
        dt_8.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setECardNumber(textField_8.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        JLabel label_2 = new JLabel("已选学生数");
        label_2.setBounds(31, 560, 97, 18);
        add(label_2);

        textField_9 = new JTextField();
        textField_9.setBounds(183, 557, 86, 24);
        add(textField_9);
        textField_9.setColumns(10);
        Document dt_9=textField_9.getDocument();
        dt_9.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setEnrolledStudents(Integer.parseInt(textField_9.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        JLabel label_3 = new JLabel("是否有考试");
        label_3.setBounds(31, 610, 105, 18);
        add(label_3);

        String []isexam=new String []{"是","否"};
        JComboBox comboBox_1 = new JComboBox(isexam);
        comboBox_1.setBounds(183, 607, 86, 24);
        add(comboBox_1);
        comboBox_1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBox_1.getSelectedIndex()==0){
                    course.setExam(true);
                }
                else
                    course.setExam(false);
            }
        });

        JButton btnOk = new JButton("OK");
        btnOk.setBounds(31, 513, 97, 27);
        add(btnOk);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course courseCopy=course;
                courseCopy.setType(Message.MESSAGE_TYPE.TYPE_DESELECT_COURSE);
                commonData.getIO().SendMessages(courseCopy);
                courseCopy=(Course)commonData.getIO().ReceiveMessage();

                course.setType(Message.MESSAGE_TYPE.TYPE_ADD_COURSE);
                commonData.getIO().SendMessages(course);
                course=(Course)commonData.getIO().ReceiveMessage();
            }
        });

    }
}
