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
import javafx.scene.layout.FlowPane;

public class SelectCoursesPanel extends JPanel {
    private final FlowLayout flow = new FlowLayout();
    private CoursesSelectedPanel selectedPanel;
    private JPanel coursepanel;
    private JPanel selectpanel;
    private JPanel coursepanel1;
    private JSplitPane splitPane;
    private JPanel selectpanel1;
    private JPanel coursepanel2;
    private JPanel selectpanel2;
    private JLabel label;
    private JComboBox<String> term;

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
        course = new Course();
        course.setCourseSemester("19-20-1");
        courseList = new ArrayList<Course>();
        courseList.add(course);
        student.setCourses(courseList);
        student.setType(Message.MESSAGE_TYPE.TYPE_GET_COURSES_AVAILABLE);
        StudentData.getIO().SendMessages(student);
        student = (Person) StudentData.getIO().ReceiveMessage();
    }

    /**
     * Constructor
     */
    public SelectCoursesPanel() {
        initialize();
        String[] termList = new String[]{"第一学期", "第二学期"};
        this.term = new JComboBox<>(termList);
        this.setLayout(new BorderLayout());
        this.add(term, BorderLayout.NORTH);
        this.selectedPanel = new CoursesSelectedPanel(flow,true);

        this.label = new JLabel("已选课程");
        this.selectedPanel.add(label);
        this.add(selectedPanel, BorderLayout.WEST);//已选课程列表

        this.splitPane = new JSplitPane();
        this.splitPane.setDividerLocation(250);

        coursepanel1 = new JPanel();//第一学期可选课程
        splitPane.setLeftComponent(coursepanel1);

        selectpanel1 = new JPanel();//第一学期选择按钮
        splitPane.setRightComponent(selectpanel1);

        coursepanel2 = new JPanel();//第二学期可选课程

        selectpanel2 = new JPanel();//第二学期可选课程
        add(splitPane, BorderLayout.CENTER);

        term.addItemListener(new ItemListener(){//选择哪个学期
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED)
                {
                    if(term.getSelectedIndex()==0)
                    {
                        splitPane.setLeftComponent(coursepanel1);
                        splitPane.setRightComponent(selectpanel1);
                        splitPane.setDividerLocation(250);
                    }
                    else
                    {
                        splitPane.setLeftComponent(coursepanel2);
                        splitPane.setRightComponent(selectpanel2);
                        splitPane.setDividerLocation(250);
                    }
                }
            }

        });
        refresh();
    }

    public void refresh(){
        int number = courseList.size();
        JTextField jtf;
        JButton jb;
        for(int i=0;i<number;i++){
            course= student.getCourses().get(i);
            if(course.getCourseSemester()=="19-20-1"){
                jtf=new JTextField(course.getCourseNumber()+"    "+course.getCourseName()+"    "+course.getCourseSemester());
                jtf.setEditable(false);
                coursepanel1.add(jtf);
                jb=new JButton("选择");
                jb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedPanel.add(new JLabel(course.getCourseName()));
                        f5();
                    }
                });
                selectpanel1.add(jb);
            }
            else if(course.getCourseSemester()=="19-20-2"){
                jtf=new JTextField(course.getCourseNumber()+"    "+course.getCourseName()+"    "+course.getCourseSemester());
                jtf.setEditable(false);
                coursepanel2.add(jtf);
                jb=new JButton("选择");
                jb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedPanel.add(new JLabel(course.getCourseName()));
                        f5();
                    }
                });
                selectpanel2.add(jb);
            }
        }
    }
    public void f5(){
        coursepanel=new JPanel();
        selectpanel=new JPanel();
        JTextField textField;
        JButton button;
        int number=courseList.size();
        for(int i=0;i<number;i++){
            Course course1 = courseList.get(i);
            if(term.getSelectedIndex()==0){
                if(course1.getCourseSemester()=="19-20-1"){
                    textField=new JTextField(course1.getCourseNumber()+"  "+ course1.getCourseName());
                    textField.setEditable(false);
                    coursepanel.add(textField);
                    button=new JButton("选择");
                    if(course1.isConflict()){
                        button.setEnabled(false);
                        selectpanel.add(button);
                    }
                    else{
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                selectedPanel.add(new JLabel(course.getCourseName()));
                                f5();
                            }
                        });
                    }
                }
            }
            else{
                if(course1.getCourseSemester()=="19-20-2"){
                    textField=new JTextField(course1.getCourseNumber()+"  "+ course1.getCourseName());
                    textField.setEditable(false);
                    coursepanel.add(textField);
                    button=new JButton("选择");
                    if(course1.isConflict()|| course1.getEnrolledStudents()== course1.getMaximumStudents()){
                        button.setEnabled(false);
                        selectpanel.add(button);
                    }
                    else{
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                selectedPanel.add(new JLabel(course.getCourseName()));
                                f5();
                            }
                        });
                    }
                }
            }
        }
    }
    /**
     * Initialize the contents of the frame.
     */

}