package com.seu.vCampus.Client.AcademicAffairs.Student;

import javax.swing.*;
import java.awt.*;

public class CoursesTable  extends JPanel {

    private JLabel jLabel;
    public CoursesTable(){

        setBounds(100, 100, 800, 800);
        setLayout(null);

        JLabel label_1 = new JLabel("周一",JLabel.CENTER);
        label_1.setBounds(80, 42, 184, 42);
        label_1.setOpaque(true);
        label_1.setForeground(Color.WHITE);
        label_1.setBackground(Color.getHSBColor((float) 0, (float) 0.11, (float) 0.34));
        add(label_1);

        JLabel label_2 = new JLabel("周二",JLabel.CENTER);
        label_2.setBounds(264, 42, 184, 42);
        label_2.setOpaque(true);
        label_2.setForeground(Color.WHITE);
        label_2.setBackground(Color.getHSBColor((float) 0.525,(float)0.34, (float) 0.50));
        add(label_2);

        JLabel label_3 = new JLabel("周三",JLabel.CENTER);
        label_3.setBounds(448, 42, 184, 42);
        label_3.setOpaque(true);
        label_3.setForeground(Color.WHITE);
        label_3.setBackground(Color.getHSBColor((float)0.492,(float)0.60, (float) 0.67));
        add(label_3);

        JLabel label_4 = new JLabel("周四",JLabel.CENTER);
        label_4.setBounds(632, 42, 184, 42);
        label_4.setOpaque(true);
        label_4.setForeground(Color.WHITE);
        label_4.setBackground(Color.getHSBColor((float) 0.372,(float) 0.29, (float) 0.87));
        add(label_4);

        JLabel label_5 = new JLabel("周五",JLabel.CENTER);
        label_5.setBounds(816, 42, 184, 42);
        label_5.setOpaque(true);
        label_5.setForeground(Color.WHITE);
        label_5.setBackground(Color.getHSBColor((float)0.231,(float)0.23, (float) 0.90));
        add(label_5);

        JLabel label1 = new JLabel("1",JLabel.CENTER);
        label1.setBounds(14, 85, 56, 65);
        label1.setOpaque(true);
        label1.setBackground(Color.getHSBColor((float)0.583,(float)0.12, (float) 0.96));
        add(label1);

        JLabel label2 = new JLabel("2",JLabel.CENTER);
        label2.setBounds(14, 150, 56, 65);
        label2.setOpaque(true);
        label2.setBackground(Color.getHSBColor((float)0.583,(float)0.12, (float) 0.96));
        add(label2);

        JLabel label3 = new JLabel("3",JLabel.CENTER);
        label3.setBounds(14, 215, 56, 65);
        label3.setOpaque(true);
        label3.setBackground(Color.getHSBColor((float)0.583,(float)0.12, (float) 0.96));
        add(label3);

        JLabel label4 = new JLabel("4",JLabel.CENTER);
        label4.setBounds(14, 280, 56, 65);
        label4.setOpaque(true);
        label4.setBackground(Color.getHSBColor((float)0.583,(float)0.12, (float) 0.96));
        add(label4);

        JLabel label5 = new JLabel("5",JLabel.CENTER);
        label5.setBounds(14, 345, 56, 65);
        label5.setOpaque(true);
        label5.setBackground(Color.getHSBColor((float)0.583,(float)0.12, (float) 0.96));
        add(label5);

        JLabel label6 = new JLabel("6",JLabel.CENTER);
        label6.setBounds(14, 410, 56, 65);
        label6.setOpaque(true);
        label6.setBackground(Color.getHSBColor((float)0.583,(float)0.12, (float) 0.96));
        add(label6);

        JLabel label7 = new JLabel("7",JLabel.CENTER);
        label7.setBounds(14, 475, 56, 65);
        label7.setOpaque(true);
        label7.setBackground(Color.getHSBColor((float)0.583,(float)0.12, (float) 0.96));
        add(label7);

        JLabel label8 = new JLabel("8",JLabel.CENTER);
        label8.setBounds(14, 540, 56, 65);
        label8.setOpaque(true);
        label8.setBackground(Color.getHSBColor((float)0.583,(float)0.12, (float) 0.96));
        add(label8);

        JLabel label9 = new JLabel("9",JLabel.CENTER);
        label9.setBounds(14, 605, 56, 65);
        label9.setOpaque(true);
        label9.setBackground(Color.getHSBColor((float)0.583,(float)0.12, (float) 0.96));
        add(label9);

        JLabel label10 = new JLabel("10",JLabel.CENTER);
        label10.setBounds(14, 670, 56, 65);
        label10.setOpaque(true);
        label10.setBackground(Color.getHSBColor((float)0.583,(float)0.12, (float) 0.96));
        add(label10);

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
            jLabel.setForeground(Color.WHITE);
            jLabel.setBounds(80+184*(l-1),85+130*(r-1),184,130);
            jLabel.setOpaque(true);
            jLabel.setBackground(Color.GRAY);
            this.add(jLabel);
        }


    }
}
