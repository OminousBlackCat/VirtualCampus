package com.seu.vCampus.Client.AcademicAffairs.Teacher;

import com.seu.vCampus.Client.AcademicAffairs.Student.CoursesTable;
import com.seu.vCampus.Client.AcademicAffairs.Student.ScheduleTable;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class TeacherScheduleTable extends JPanel {
    Common commonData;
    private Person user;
    private Course course;
    private TeacherCoursesTable coursesTable;
    private ArrayList<String> semester;
    private ArrayList<Course> courses;
    private ArrayList<TeacherCoursesTable> coursesTables;
    private TeacherCoursesTable ct;

    private JComboBox comboBox;
    public void initialize(){
        commonData=Common.getInstance();
        user = new Person();
        course = new Course();
        user.setECardNumber(commonData.getUser().getECardNumber());
        user.setType(Message.MESSAGE_TYPE.TYPE_GET_LECTURER_COURSES);
        commonData.getIO().SendMessages(user);
        user=(Person)commonData.getIO().ReceiveMessage();
        courses = user.getCourses();
        int n= courses.size();
        semester = new ArrayList<String>();
        for(int i=0;i<n;i++){
            String str = courses.get(i).getCourseSemester();
            if(semester.contains(str)){ }
            else
                semester.add(str);
        }

        coursesTables = new ArrayList<TeacherCoursesTable>();
        for(int i=0;i<semester.size();i++){
            coursesTable = new TeacherCoursesTable();
            for(int j=0; j<n; j++){
                if(courses.get(j).getCourseSemester().equals(semester.get(i))){
                    coursesTable.addCourse(courses.get(j).getCourseTime(), courses.get(j).getCourseName());
                }
            }
            coursesTables.add(coursesTable);
        }
    }

    public TeacherScheduleTable(){
        initialize();
        if(!coursesTables.isEmpty()) {
            ct = coursesTables.get(0);
            setLayout(new BorderLayout());
            String[] temp = new String[semester.size()];
            comboBox = new JComboBox(semester.toArray(temp));
            add(comboBox, BorderLayout.NORTH);
            TeacherScheduleTable.this.add(ct, BorderLayout.CENTER);
            ct.setVisible(true);
            comboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    int i = comboBox.getSelectedIndex();
                    ct.setVisible(false);
                    ct = coursesTables.get(i);
                    TeacherScheduleTable.this.add(ct, BorderLayout.CENTER);
                    ct.setVisible(true);
                }
            });
        }
        else {
            add(new JLabel("您还没有课呢~"),BorderLayout.NORTH);
        }
    }
}
