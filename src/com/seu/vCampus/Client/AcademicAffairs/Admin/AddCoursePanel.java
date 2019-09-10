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

public class AddCoursePanel extends JPanel{
    private JTextField courseNumberField;
    private JTextField courseNameField;
    private JTextField courseSemesterField;
    private JTextField courseLecturerField;
    private JTextField coursePlaceField;
    private JTextField courseTimeField;
    private JTextField maximumStudentsField;
    private JTextField courseCreditField;
    private JTextField lecturerECardNumberField;
    private JTextField enrolledStudentsField;
    private String courseType;
    private boolean isExam;

    private ArrayList <Course> courses;
    private Common commonData;
    private Person person;
    private Course course;


    public AddCoursePanel(AdminMainPanel amP) {
        setBounds(100, 100, 330, 559);
        setLayout(null);

        JLabel label = new JLabel("课程号");
        label.setBounds(181, 90, 108, 18);
        add(label);

        JLabel lblCoursename = new JLabel("课程名");
        lblCoursename.setBounds(581, 90, 97, 18);
        add(lblCoursename);

        JLabel lblNewLabel = new JLabel("课程学期");
        lblNewLabel.setBounds(181, 190, 119, 18);
        add(lblNewLabel);

        JLabel lblCourselecturer = new JLabel("授课教师");
        lblCourselecturer.setBounds(581, 190, 119, 18);
        add(lblCourselecturer);

        JLabel lblCourseplace = new JLabel("上课地点");
        lblCourseplace.setBounds(181, 290, 108, 18);
        add(lblCourseplace);

        JLabel lblCoursetime = new JLabel("上课时间");
        lblCoursetime.setBounds(581, 290, 108, 18);
        add(lblCoursetime);

        JLabel lblMaximumstudents = new JLabel("最大学生数");
        lblMaximumstudents.setBounds(181, 390, 119, 18);
        add(lblMaximumstudents);

        JLabel lblCoursetype = new JLabel("课程类型");
        lblCoursetype.setBounds(581, 390, 97, 18);
        add(lblCoursetype);

        JLabel lblCoursecredit = new JLabel("课程学分");
        lblCoursecredit.setBounds(181, 490, 108, 18);
        add(lblCoursecredit);

        courseNumberField = new JTextField();
        courseNumberField.setBounds(353, 83, 100, 30);
        add(courseNumberField);
        courseNumberField.setColumns(10);

        courseNameField = new JTextField();
        courseNameField.setBounds(753, 83, 100, 30);
        add(courseNameField);
        courseNameField.setColumns(10);

        courseSemesterField = new JTextField();
        courseSemesterField.setBounds(353, 183, 100, 30);
        add(courseSemesterField);
        courseSemesterField.setColumns(10);

        courseLecturerField = new JTextField();
        courseLecturerField.setBounds(753, 183, 100, 30);
        add(courseLecturerField);
        courseLecturerField.setColumns(10);

        coursePlaceField = new JTextField();
        coursePlaceField.setBounds(353, 283, 100, 30);
        add(coursePlaceField);
        coursePlaceField.setColumns(10);

        courseTimeField = new JTextField();
        courseTimeField.setBounds(753, 283, 100, 30);
        add(courseTimeField);
        courseTimeField.setColumns(10);

        maximumStudentsField = new JTextField();
        maximumStudentsField.setBounds(353, 383, 100, 30);
        add(maximumStudentsField);
        maximumStudentsField.setColumns(10);
        Document dt_6= maximumStudentsField.getDocument();

        String [] str=new String[] {"必修","选修"};
        JComboBox comboBox = new JComboBox(str);
        comboBox.setBounds(753, 383, 100, 30);
        add(comboBox);
        courseType = "必修";
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBox.getSelectedIndex()==0)
                    courseType = "必修";
                else
                    courseType = "选修";
            }
        });

        courseCreditField = new JTextField();
        courseCreditField.setBounds(353, 483, 100, 30);
        add(courseCreditField);
        courseCreditField.setColumns(10);

        JLabel label_1 = new JLabel("老师一卡通");
        label_1.setBounds(581, 490, 108, 18);
        add(label_1);

        lecturerECardNumberField = new JTextField();
        lecturerECardNumberField.setBounds(753, 483, 100, 30);
        add(lecturerECardNumberField);
        lecturerECardNumberField.setColumns(10);

        JLabel label_2 = new JLabel("已选学生数");
        label_2.setBounds(181, 590, 97, 18);
        add(label_2);

        enrolledStudentsField = new JTextField();
        enrolledStudentsField.setBounds(353, 583, 100, 30);
        add(enrolledStudentsField);
        enrolledStudentsField.setColumns(10);

        JLabel label_3 = new JLabel("是否有考试");
        label_3.setBounds(581, 590, 105, 18);
        add(label_3);

        String []isexam=new String []{"是","否"};
        JComboBox isExamBox = new JComboBox(isexam);
        isExamBox.setBounds(753, 583, 100, 30);
        add(isExamBox);
        isExamBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(isExamBox.getSelectedIndex()==0){
                    isExam = true;
                }
                else
                    isExam = false;
            }
        });

        JButton btnOk = new JButton("提交");
        btnOk.setBounds(451, 713, 110, 40);
        add(btnOk);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null,"确定提交？") == JOptionPane.YES_OPTION) {
                    course = new Course(courseNumberField.getText() + "-" + courseSemesterField.getText(),
                            courseNameField.getText(),
                            courseSemesterField.getText(), courseLecturerField.getText(),
                            lecturerECardNumberField.getText(), coursePlaceField.getText(),
                            courseTimeField.getText(), courseCreditField.getText(), courseType,
                            Integer.parseInt(maximumStudentsField.getText()),
                            Integer.parseInt(enrolledStudentsField.getText()), isExam, false);
                    course.setType(Message.MESSAGE_TYPE.TYPE_ADD_COURSE);
                    commonData = Common.getInstance();
                    commonData.getIO().SendMessages(course);
                    course = (Course) commonData.getIO().ReceiveMessage();
                    course.setECardNumber(commonData.getUser().getECardNumber());
                    if(course.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
                        JOptionPane.showMessageDialog(null,"提交成功","提示",
                                JOptionPane.INFORMATION_MESSAGE);
                        amP.refresh();

                    }
                }
            }
        });

    }
}
