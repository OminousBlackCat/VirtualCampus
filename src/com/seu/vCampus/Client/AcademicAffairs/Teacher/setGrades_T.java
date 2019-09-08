package com.seu.vCampus.Client.AcademicAffairs.Teacher;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;


public class setGrades_T extends JFrame {

    private JScrollPane jScrollPane;
    private JTable table;
    private DefaultTableModel defaultModel;
    private Common commondata;
    private Person user;
    private Course course;
    private ArrayList<Course> courses;
    private Vector rowData;
    private Vector vecRow;
    private int row;
    private int col;

    public void getinf(){
        vecRow=new Vector();
        courses=user.getCourses();
        int n=courses.size();
        for(int i=0;i<n;i++){
            rowData=new Vector();
            course=courses.get(i);
            rowData.add(course.getECardNumber());
            rowData.add(course.getStudentName());
            if(course.isGradeAdded())
                rowData.add(course.getCourseGrade());
            else
                rowData.add("请输入");
            vecRow.add(rowData);
        }
    }
    public setGrades_T(Person person){
        user=person;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Vector columnNames=new Vector();
        columnNames.add("一卡通");
        columnNames.add("学生姓名");
        columnNames.add("成绩");

        getinf();//获取学生信息
        defaultModel=new DefaultTableModel(vecRow,columnNames);
        table=new JTable(defaultModel){
            public boolean isCellEditable(int row,int column){
                if(column!=3){
                    return true;
                }
                else
                    return false;
            }
        };

        table.getModel().addTableModelListener(new TableModelListener() {//成绩修改
            @Override
            public void tableChanged(TableModelEvent e) {
                int number=e.getFirstRow();
                courses.get(number).setCourseGrade((Integer) table.getValueAt(number,2));
            }
        });


        jScrollPane.setLayout(new BorderLayout());
        jScrollPane.add(table,BorderLayout.CENTER);
        JButton jb=new JButton("确定");
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setCourses(courses);
                user.setType(Message.MESSAGE_TYPE.TYPE_GRADES_INPUT);
                commondata.getIO().SendMessages(user);
                user=(Person)commondata.getIO().ReceiveMessage();
                System.exit(0);
            }
        });

        getContentPane().add(jScrollPane, BorderLayout.CENTER);

    }
}
