package com.seu.vCampus.Client.AcademicAffairs.Teacher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMainPanel extends JPanel {

    public TeacherMainPanel(){

        setBounds(100, 100, 800, 800);
        setLayout(null);

        JButton button = new JButton("\u67E5\u8BFE\u8868");
        button.setBounds(342, 295, 113, 27);
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CoursesTable coursesTable=new CoursesTable();
                coursesTable.setVisible(true);
            }
        });


        JButton button_1 = new JButton("\u8F93\u6210\u7EE9");
        button_1.setBounds(342, 462, 113, 27);
        add(button_1);
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputGrades inputGrades=new InputGrades();
                inputGrades.setVisible(true);
            }
        });
    }

}
