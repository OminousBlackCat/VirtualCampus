package com.seu.vCampus.Client.AcademicAffairs.Admin;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class ChangeGradePanel extends Panel {

/*private JTextField textField;
private JTextField textField_1;
private JTextField textField_2;

private Person student;
private Course course;
private ArrayList<Course> courses;
private Common common;
private Vector<String>courseNames;
private int selectedNumber;
private int grade;

public ChangeGradePanel(){
    setBounds(100, 100, 436, 484);

    JPanel panel = new JPanel();
    panel.setLayout(null);

    JLabel label = new JLabel("学生一卡通：");
    label.setBounds(87, 111, 95, 18);
    panel.add(label);

    courseNames=new Vector<String>();
    textField = new JTextField();
    textField.setBounds(223, 108, 95, 24);
    panel.add(textField);
    textField.setColumns(10);
    textField.addKeyListener(new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyChar()==KeyEvent.VK_ENTER){
                course.setECardNumber(textField.getText());
                courses.add(course);
                student.setCourses(courses);
                student.setType(Message.MESSAGE_TYPE.TYPE_GET_COURSES_SELECTED);
                common.getIO().SendMessages(student);
                student=(Person)common.getIO().ReceiveMessage();
                courses=student.getCourses();
                int n=courses.size();
                for(int i=0;i<n;i++){
                    courseNames.add(courses.get(i).getCourseName());
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    });



    JLabel label_1 = new JLabel("课程名：");
    label_1.setBounds(87, 181, 61, 18);
    panel.add(label_1);


    JComboBox comboBox = new JComboBox(courseNames);
    comboBox.setBounds(223, 178, 95, 24);
    panel.add(comboBox);
    comboBox.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            selectedNumber=comboBox.getSelectedIndex();
        }
    });

    JLabel label_2 = new JLabel("目前成绩：");
    label_2.setBounds(87, 254, 95, 18);
    panel.add(label_2);

    textField_1 = new JTextField();
    textField_1.setBounds(223, 251, 95, 24);
    textField_1.setText(String.valueOf(courses.get(selectedNumber).getCourseGrade()));
    textField_1.setEditable(false);
    panel.add(textField_1);
    textField_1.setColumns(10);

    JLabel label_3 = new JLabel("修改为：");
    label_3.setBounds(87, 320, 72, 18);
    panel.add(label_3);

    textField_2 = new JTextField();
    textField_2.setBounds(223, 317, 95, 24);
    panel.add(textField_2);
    textField_2.setColumns(10);
    Document dt_2=textField_2.getDocument();
    dt_2.addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            grade=Integer.parseInt(textField_2.getText());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    });

    JButton button_1=new JButton("确定");
    button_1.setBounds(87,390,90,24);
    panel.add(button_1);
    button_1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            course=courses.get(selectedNumber);
            course.setCourseGrade(grade);
            student.setType(Message.MESSAGE_TYPE.TYPE_SET_GRADE);
            common.getIO().SendMessages(course);
            student=(Person)common.getIO().ReceiveMessage();
        }
    });

    JButton button_2=new JButton("取消");
    button_2.setBounds(223,387,90,24);
    panel.add(button_2);
}*/
}
