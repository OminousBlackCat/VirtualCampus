package com.seu.vCampus.Client.AcademicAffairs.Admin;

import com.seu.vCampus.util.Course;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class AddCourse {
    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;

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
    public AddCourse() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 330, 559);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label = new JLabel("courseNumber");
        label.setBounds(31, 60, 108, 18);
        frame.getContentPane().add(label);

        JLabel lblCoursename = new JLabel("courseName");
        lblCoursename.setBounds(31, 110, 97, 18);
        frame.getContentPane().add(lblCoursename);

        JLabel lblNewLabel = new JLabel("courseSemester");
        lblNewLabel.setBounds(31, 160, 119, 18);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblCourselecturer = new JLabel("courseLecturer");
        lblCourselecturer.setBounds(31, 210, 119, 18);
        frame.getContentPane().add(lblCourselecturer);

        JLabel lblCourseplace = new JLabel("coursePlace");
        lblCourseplace.setBounds(31, 260, 108, 18);
        frame.getContentPane().add(lblCourseplace);

        JLabel lblCoursetime = new JLabel("courseTime");
        lblCoursetime.setBounds(31, 310, 108, 18);
        frame.getContentPane().add(lblCoursetime);

        JLabel lblMaximumstudents = new JLabel("maximumStudents");
        lblMaximumstudents.setBounds(31, 360, 119, 18);
        frame.getContentPane().add(lblMaximumstudents);

        JLabel lblCoursetype = new JLabel("courseType");
        lblCoursetype.setBounds(31, 410, 97, 18);
        frame.getContentPane().add(lblCoursetype);

        JLabel lblCoursecredit = new JLabel("courseCredit");
        lblCoursecredit.setBounds(31, 460, 108, 18);
        frame.getContentPane().add(lblCoursecredit);

        textField = new JTextField();
        textField.setBounds(183, 57, 86, 24);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        Document dt=textField.getDocument();
        dt.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

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
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(183, 157, 86, 24);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(183, 207, 86, 24);
        frame.getContentPane().add(textField_3);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setBounds(183, 257, 86, 24);
        frame.getContentPane().add(textField_4);
        textField_4.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setBounds(183, 307, 86, 24);
        frame.getContentPane().add(textField_5);
        textField_5.setColumns(10);

        textField_6 = new JTextField();
        textField_6.setBounds(183, 357, 86, 24);
        frame.getContentPane().add(textField_6);
        textField_6.setColumns(10);

        String [] str=new String[] {"必修","选修"};
        JComboBox comboBox = new JComboBox(str);
        comboBox.setBounds(183, 407, 86, 24);
        frame.getContentPane().add(comboBox);

        textField_7 = new JTextField();
        textField_7.setBounds(183, 457, 86, 24);
        frame.getContentPane().add(textField_7);
        textField_7.setColumns(10);

        JButton btnOk = new JButton("OK");
        btnOk.setBounds(31, 513, 97, 27);
        frame.getContentPane().add(btnOk);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(185, 513, 97, 27);
        frame.getContentPane().add(btnCancel);
    }
}
