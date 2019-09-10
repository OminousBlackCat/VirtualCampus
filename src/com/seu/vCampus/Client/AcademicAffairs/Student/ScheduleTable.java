package com.seu.vCampus.Client.AcademicAffairs.Student;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class ScheduleTable extends JPanel {
    Common commonData;
    private Person user;
    private Course course;
    private CoursesTable coursesTable;
    private ArrayList<String> semester;
    private ArrayList<Course> courses;
    private ArrayList<CoursesTable> coursesTables;
    private CoursesTable ct;

    private JComboBox comboBox;
    public void initialize(){
        commonData=Common.getInstance();
        user = new Person();
        course = new Course();
        user.setECardNumber(commonData.getUser().getECardNumber());
        user.setType(Message.MESSAGE_TYPE.TYPE_GET_COURSES_SELECTED);
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

        coursesTables = new ArrayList<CoursesTable>();
        for(int i=0;i<semester.size();i++){
            coursesTable = new CoursesTable();
            for(int j=0; j<n; j++){
                if(courses.get(j).getCourseSemester() == semester.get(i)){
                    coursesTable.addCourse(courses.get(i).getCourseTime(), courses.get(i).getCourseName());
                }
            }
            coursesTables.add(coursesTable);
        }
    }

    public ScheduleTable(){
        initialize();
        if(!coursesTables.isEmpty()) {
            ct = coursesTables.get(0);
            setLayout(new BorderLayout());
            String[] temp = new String[semester.size()];
            comboBox = new JComboBox(semester.toArray(temp));
            add(comboBox, BorderLayout.NORTH);
            ScheduleTable.this.add(ct, BorderLayout.CENTER);
            ct.setVisible(true);
            comboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    int i = comboBox.getSelectedIndex();
                    ct.setVisible(false);
                    ct = coursesTables.get(i);
                    ScheduleTable.this.add(ct, BorderLayout.CENTER);
                    ct.setVisible(true);
                }
            });
        }
        else {
            add(new JLabel("您还没有选课呢~"),BorderLayout.NORTH);
        }
    }

}
