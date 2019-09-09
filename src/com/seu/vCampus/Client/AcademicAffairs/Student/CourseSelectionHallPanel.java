package com.seu.vCampus.Client.AcademicAffairs.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.ArrayList;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

public class CourseSelectionHallPanel extends JPanel {
    private ArrayList<String> semesters;
    private ArrayList<CoursesSelectedPanel> cSPs;
    private ArrayList<SelectCoursesPanel> scPs;
    private JLabel label;
    private JComboBox<String> semComboBox;

    private Common StudentData;
    private Person student;
    private Course course;
    private ArrayList<Course> courseList;


    /**
     * Initialize the application panel.
     */
    public void initialize(){
        StudentData = Common.getInstance();
        student = new Person();
        student.setECardNumber(StudentData.getUser().getECardNumber());
        student.setType(Message.MESSAGE_TYPE.TYPE_GET_COURSES_AVAILABLE);
        StudentData.getIO().SendMessages(student);
        student = (Person) StudentData.getIO().ReceiveMessage();
        courseList = student.getCourses();
        student.setType(Message.MESSAGE_TYPE.TYPE_GET_WITHOUT_GRADES);
        StudentData.getIO().SendMessages(student);
        student = (Person) StudentData.getIO().ReceiveMessage();
        courseList.addAll(student.getCourses());
        semesters = new ArrayList<String>();
        if(!courseList.isEmpty()) {
            for (Course c : courseList) {
                String tempSem = c.getCourseSemester();
                if (!semesters.contains(tempSem)) {
                    semesters.add(tempSem);
                }
            }
        }
    }
    /**
     * Constructor
     */
    public CourseSelectionHallPanel(StudentAcademicMainPanel fatherPanel) {
        initialize();
        setLayout(new BorderLayout());
        if(!semesters.isEmpty()) {
            scPs = new ArrayList<>();
            cSPs = new ArrayList<>();
            String[] temp = new String[semesters.size()];
            temp = semesters.toArray(temp);
            semComboBox = new JComboBox<String>(temp);
            add(semComboBox, BorderLayout.NORTH);
            for (String s : semesters) {
                scPs.add(new SelectCoursesPanel(s,fatherPanel));
                cSPs.add(new CoursesSelectedPanel(s,fatherPanel));
            }

            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,cSPs.get(0),scPs.get(0));
            splitPane.setDividerLocation(500);
            add(splitPane,BorderLayout.CENTER);

            semComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    splitPane.setLeftComponent(cSPs.get(semComboBox.getSelectedIndex()));
                    splitPane.setRightComponent(scPs.get(semComboBox.getSelectedIndex()));
                }
            });

        }
        else add(new JTextField("暂无可选课程"), BorderLayout.CENTER);
    }

}