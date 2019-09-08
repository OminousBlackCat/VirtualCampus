package com.seu.vCampus.Client.AcademicAffairs.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class MainPanel extends JPanel{


   public MainPanel(){

       setBounds(100, 100, 370, 475);
       setLayout(null);

       JButton button = new JButton("添加课程");
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
               AddCourse addCourse=new AddCourse();
           }
       });
       button.setBounds(117, 89, 125, 27);
       add(button);

       JButton button_1 = new JButton("添加考试");
       button_1.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               AddExams addExams=new AddExams();
           }
       });
       button_1.setBounds(117, 169, 125, 27);
       add(button_1);

       JButton button_2 = new JButton("修改成绩");
       button_2.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               ChangeGrade setGrades=new ChangeGrade();
               setGrades.setVisible(true);
           }
       });
       button_2.setBounds(117, 252, 125, 27);
       add(button_2);

       JButton button_3 = new JButton("修改课程信息");
       button_3.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
               ChangeCourseInf changeCourseInf=new ChangeCourseInf();
               changeCourseInf.setVisible(true);
           }
       });
       button_3.setBounds(117, 333, 125, 27);
       add(button_3);
    }

}
