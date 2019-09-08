package com.seu.vCampus.Client.AcademicAffairs.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Vector;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

public class SelectCoursesPanel extends JPanel {

    private JComboBox<String> term;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JTable table2;
    private DefaultTableModel defaultModel;

    private Common StudentData;
    private Person student;
    private Course course;
    private ArrayList<Course> courseList;
    private Vector rowData;
    private Vector rowVec;
    private CoursesTable coursesTable;
    /**
     * Initialize the application panel.
     */
    public void initialize1(){

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

    public void initialize2(){
        course = new Course();
        course.setCourseSemester("19-20-2");
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
        initialize1();
        setLayout(new BorderLayout());
        scrollPane1.setLayout(new BorderLayout());
        scrollPane2.setLayout(new BorderLayout());
        Vector columnNames=new Vector();
        columnNames.add("课程号");
        columnNames.add("课程名");
        columnNames.add("学期");
        columnNames.add("上课地点");
        columnNames.add("上课时间");
        columnNames.add("课程类型");
        columnNames.add("学分");

        int number= courseList.size();
        for(int i=0;i<number;i++){
            course=courseList.get(i);
            rowData.add(course.getCourseNumber());
            rowData.add(course.getCourseName());
            rowData.add(course.getCourseSemester());
            rowData.add(course.getCoursePlace());
            rowData.add(course.getCourseTime());
            rowData.add(course.getCourseType());
            rowData.add(course.getCourseCredit());

            rowVec.add(rowData);
        }
        defaultModel=new DefaultTableModel(rowVec,columnNames);
        table1=new JTable(defaultModel);
        scrollPane1.add(table1,BorderLayout.CENTER);

        initialize2();
        number= courseList.size();
        for(int i=0;i<number;i++){
            course=courseList.get(i);
            rowData.add(course.getCourseNumber());
            rowData.add(course.getCourseName());
            rowData.add(course.getCourseSemester());
            rowData.add(course.getCoursePlace());
            rowData.add(course.getCourseTime());
            rowData.add(course.getCourseType());
            rowData.add(course.getCourseCredit());

            rowVec.add(rowData);
        }
        defaultModel=new DefaultTableModel(rowVec,columnNames);
        table2=new JTable(defaultModel);
        scrollPane2.add(table2,BorderLayout.CENTER);

        String[] str=new String[]{"19-20-1","19-20-2"};
        term=new JComboBox(str);
        term.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(term.getSelectedIndex()==0){
                    add(scrollPane1,BorderLayout.CENTER);
                }
                else
                    add(scrollPane2,BorderLayout.CENTER);
            }
        });

        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    int row =((JTable)e.getSource()).rowAtPoint(e.getPoint());
                    String courseTime=(String)defaultModel.getValueAt(row,4);
                    String courseName=(String)defaultModel.getValueAt(row,1);
                    Confirm confirm=new Confirm();
                    coursesTable.addCourse(courseTime,courseName);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }


}