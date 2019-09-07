package com.seu.vCampus.Client.courseSelect;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

public class courseSelectForT extends JPanel {

    private Person user;
    private Common commondata;
    private Course course;
    private ArrayList<Course> courseList;
    private ArrayList<Course> students;
    Vector<String> courseName;
    public void initialize(){
        commondata=Common.getInstance();
        user=new Person();
        course=new Course();
        courseList=new ArrayList<>();
        user.setECardNumber(commondata.getUser().getECardNumber());
        user.setType(Message.MESSAGE_TYPE.TYPE_GET_LECTURER_COURSES);
        commondata.getIo().SendMessages(user);
        user=(Person)commondata.getIo().ReceiveMessage();
    }
    public courseSelectForT(){
        initialize();

        JLabel label = new JLabel("选择课程:");
        label.setBounds(59, 129, 72, 18);
        add(label);

        courseList=user.getCourses();
        int number=courseList.size();
        courseName=new Vector<>();
        for(int i=0;i<number;i++){
            courseName.add(courseList.get(i).getCourseName());
        }
        JComboBox comboBox = new JComboBox(courseName);
        comboBox.setBounds(161, 126, 106, 24);
        add(comboBox);


        JButton button = new JButton("\u67E5\u8BE2");
        button.setBounds(112, 232, 113, 27);
        add(button);

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    String courseSelected=courseList.get(comboBox.getSelectedIndex()).getCourseNumber();
                    course.setCourseNumber(courseSelected);
                    students=new ArrayList<>();
                    students.add(course);
                    user.setType(Message.MESSAGE_TYPE.TYPE_GET_ENROLLED_STUDENTS);
                    user.setCourses(students);
                    commondata.getIo().SendMessages(user);
                    user=(Person)commondata.getIo().ReceiveMessage();
                }
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹出学生成绩窗口
            }
        });
    }
}
