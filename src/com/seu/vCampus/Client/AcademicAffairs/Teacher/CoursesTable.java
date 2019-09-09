package com.seu.vCampus.Client.AcademicAffairs.Teacher;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class CoursesTable extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;

    private Common commondata;
    private Person user;
    private Course course;
    private ArrayList<Course> courses;
    private Vector rowData;
    private Vector rowVec;
    private DefaultTableModel defaultModel;
    public void getInf(){
        commondata=Common.getInstance();
        user=new Person();
        user.setECardNumber(commondata.getUser().getECardNumber());
        user.setType(Message.MESSAGE_TYPE.TYPE_GET_LECTURER_COURSES);
        commondata.getIO().SendMessages(user);
        user=(Person)commondata.getIO().ReceiveMessage();
        courses = user.getCourses();
    }
    public CoursesTable(){
        setBounds(200,200,800,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getInf();
        Vector columnNames=new Vector();
        columnNames.add("课程号");
        columnNames.add("课程名");
        columnNames.add("学期");
        columnNames.add("上课地点");
        columnNames.add("上课时间");
        columnNames.add("学生人数");
        columnNames.add("学分");

        rowData=new Vector();
        rowVec=new Vector();
        int number=courses.size();
        for(int i=0;i<number;i++){
            course=courses.get(i);
            rowData.add(course.getCourseNumber().split("-")[0]);
            rowData.add(course.getCourseName());
            rowData.add(course.getCourseSemester());
            rowData.add(course.getCoursePlace());
            rowData.add(course.getCourseTime());
            rowData.add(course.getEnrolledStudents());
            rowData.add(course.getCourseCredit());
            rowVec.add(rowData);
        }
        defaultModel=new DefaultTableModel(rowVec,columnNames);
        table=new JTable(defaultModel){
            public boolean isCellEditable(int row,int column){
                    return false;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableUtils.FitTableColumns(table);
        table.setFont(new Font("楷体",Font.PLAIN,14));
        scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().setVisible(true);
    }
}
