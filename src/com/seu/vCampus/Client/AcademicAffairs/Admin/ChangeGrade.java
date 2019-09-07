package com.seu.vCampus.Client.AcademicAffairs.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ChangeGrade extends JFrame {

    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    public ChangeGrade(){
        setBounds(100, 100, 436, 484);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel label = new JLabel("学生一卡通：");
        label.setBounds(87, 111, 95, 18);
        panel.add(label);

        Vector<String>course=new Vector<String>();
        textField = new JTextField();
        textField.setBounds(223, 108, 95, 24);
        panel.add(textField);
        textField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        textField.setColumns(10);

        JLabel label_1 = new JLabel("课程名：");
        label_1.setBounds(87, 181, 61, 18);
        panel.add(label_1);


        JComboBox comboBox = new JComboBox(course);
        comboBox.setBounds(223, 178, 95, 24);
        panel.add(comboBox);

        JLabel label_2 = new JLabel("目前成绩：");
        label_2.setBounds(87, 254, 95, 18);
        panel.add(label_2);

        textField_1 = new JTextField();
        textField_1.setBounds(223, 251, 95, 24);
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
    }
}
