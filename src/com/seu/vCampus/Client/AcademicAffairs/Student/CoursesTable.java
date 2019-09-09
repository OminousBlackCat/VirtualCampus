package com.seu.vCampus.Client.AcademicAffairs.Student;

import javax.swing.*;
import java.awt.*;

public class CoursesTable  extends JPanel {

    private JLabel jLabel;
    public CoursesTable(){

        setBounds(100, 100, 800, 800);
        setLayout(null);

        JLabel label_1 = new JLabel("周一",JLabel.CENTER);
        label_1.setBounds(80, 42, 130, 42);
        label_1.setOpaque(true);
        label_1.setBackground(Color.GREEN);
        add(label_1);

        JLabel label_2 = new JLabel("周二",JLabel.CENTER);
        label_2.setBounds(210, 42, 130, 42);
        label_2.setOpaque(true);
        label_2.setBackground(Color.GREEN);
        add(label_2);

        JLabel label_3 = new JLabel("周三",JLabel.CENTER);
        label_3.setBounds(340, 42, 130, 42);
        label_3.setOpaque(true);
        label_3.setBackground(Color.GREEN);
        add(label_3);

        JLabel label_4 = new JLabel("周四",JLabel.CENTER);
        label_4.setBounds(470, 42, 130, 42);
        label_4.setOpaque(true);
        label_4.setBackground(Color.GREEN);
        add(label_4);

        JLabel label_5 = new JLabel("周五",JLabel.CENTER);
        label_5.setBounds(600, 42, 130, 42);
        label_5.setOpaque(true);
        label_5.setBackground(Color.GREEN);
        add(label_5);

        JLabel label1 = new JLabel("1",JLabel.CENTER);
        label1.setBounds(14, 85, 56, 55);
        add(label1);

        JLabel label2 = new JLabel("2",JLabel.CENTER);
        label2.setBounds(14, 140, 56, 55);
        add(label2);

        JLabel label3 = new JLabel("3",JLabel.CENTER);
        label3.setBounds(14, 195, 56, 55);
        add(label3);

        JLabel label4 = new JLabel("4",JLabel.CENTER);
        label4.setBounds(14, 250, 56, 55);
        add(label4);

        JLabel label5 = new JLabel("5",JLabel.CENTER);
        label5.setBounds(14, 305, 56, 55);
        add(label5);

        JLabel label6 = new JLabel("6",JLabel.CENTER);
        label6.setBounds(14, 360, 56, 55);
        add(label6);

        JLabel label7 = new JLabel("7",JLabel.CENTER);
        label7.setBounds(14, 415, 56, 55);
        add(label7);

        JLabel label8 = new JLabel("8",JLabel.CENTER);
        label8.setBounds(14, 470, 56, 55);
        add(label8);

        JLabel label9 = new JLabel("9",JLabel.CENTER);
        label9.setBounds(14, 525, 56, 55);
        add(label9);

        JLabel label10 = new JLabel("10",JLabel.CENTER);
        label10.setBounds(14, 580, 56, 55);
        add(label10);

        JLabel label11 = new JLabel("11",JLabel.CENTER);
        label11.setBounds(14, 635, 56, 55);
        add(label11);

        JLabel label12 = new JLabel("12",JLabel.CENTER);
        label12.setBounds(14, 690, 56, 55);
        add(label12);


    }
    public void addCourse(String CourseTime,String CourseName){
        String []time;
        time = CourseTime.split(";");
        int n = time.length;
        for(int i=0; i < n; i++){
            String[] t = time[i].split("-");
            int l= Integer.parseInt(t[0]);
            int r= Integer.parseInt(t[1]);
            jLabel=new JLabel(CourseName,JLabel.CENTER);
            jLabel.setBounds(80+130*(l-1),85+55*(r-1),130,55);
            jLabel.setOpaque(true);
            jLabel.setBackground(Color.GRAY);
            this.add(jLabel);
        }


    }
}
