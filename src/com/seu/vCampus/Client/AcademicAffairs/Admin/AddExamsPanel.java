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
import java.util.ArrayList;

public class AddExamsPanel extends JPanel {
    private Common common;
    private Person person;
    private Course course;
    private ArrayList<Course> courses;

    public AddExamsPanel(){

        common = Common.getInstance();

        setBounds(100, 100, 375, 500);
        setLayout(null);

        JLabel label_1 = new JLabel("考试时间：",JLabel.CENTER);
        label_1.setBounds(70, 200, 86, 20);
        add(label_1);

        JLabel label_2 = new JLabel("考试地点：",JLabel.CENTER);
        label_2.setBounds(70, 280, 86, 20);
        add(label_2);

        JTextField textField = new JTextField();
        textField.setBounds(194, 118, 86, 24);
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

        JTextField textField_1 = new JTextField();
        textField_1.setBounds(194, 198, 86, 24);
        add(textField_1);
        textField_1.setColumns(10);
        Document dt_1=textField_1.getDocument();
        dt_1.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setExamTime(textField_1.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        JTextField textField_2 = new JTextField();
        textField_2.setBounds(194, 278, 86, 24);
        add(textField_2);
        textField_2.setColumns(10);
        Document dt_2=textField_2.getDocument();
        dt_2.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course.setExamPlace(textField_2.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        JButton button = new JButton("确定");
        button.setBounds(70, 358, 86, 27);
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null,"确定添加？") == JOptionPane.YES_OPTION) {
                    course.setExam(true);
                    courses.add(course);
                    person.setCourses(courses);
                    person.setType(Message.MESSAGE_TYPE.TYPE_INPUT_EXAMS);
                    common.getIO().SendMessages(person);
                    person = (Person) common.getIO().ReceiveMessage();
                }
            }
        });
    }
}
