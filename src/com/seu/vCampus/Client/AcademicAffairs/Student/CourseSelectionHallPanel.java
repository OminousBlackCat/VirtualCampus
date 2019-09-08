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
        semesters = new ArrayList<String>();
        if(student.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
            courseList = student.getCourses();
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
    public CourseSelectionHallPanel() {
        initialize();
        setLayout(new BorderLayout());
        if(!semesters.isEmpty()) {
            JSplitPane splitPane = new JSplitPane();
            splitPane.setDividerLocation(500);
            add(splitPane);
            semComboBox = new JComboBox<>((String[]) semesters.toArray());
            add(semComboBox, BorderLayout.NORTH);
            for (String s : semesters) {
                scPs.add(new SelectCoursesPanel(s));
                cSPs.add(new CoursesSelectedPanel(s));
            }
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