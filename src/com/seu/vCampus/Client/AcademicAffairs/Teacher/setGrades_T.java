package com.seu.vCampus.Client.AcademicAffairs.Teacher;

import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;


public class setGrades_T extends JFrame {

    private JTable table;
    private DefaultTableModel defaultModel;
    private Person user;
    private Course course;
    private ArrayList<Course> courses;
    private Vector rowData;
    private Vector vecRow;
    private JButton jb;
    public void getinf(){
        vecRow=new Vector();
        courses=user.getCourses();
        int n=courses.size();
        for(int i=0;i<n;i++){
            rowData=new Vector();
            course=courses.get(i);
            rowData.add(course.getECardNumber());
            rowData.add(course.getStudentName());
            if(course.getCourseGrade()==0){
                rowData.add(course.getCourseGrade());
            }
            else
                rowData.add(0);
            if(course)
                rowData.add(course.getCourseGrade());
            else
                rowData.add("请输入");
            vecRow.add(rowData);
        }
    }
    public setGrades_T(Person person){
        user=person;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());

        Vector columnNames=new Vector();
        columnNames.add("一卡通");
        columnNames.add("学生姓名");
        columnNames.add("成绩");
        columnNames.add("输入");

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

        JTextField jtf=new JTextField();
        Document doc=jtf.getDocument();
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        TableColumn d=table.getColumn("输入");
        DefaultCellEditor dce=new DefaultCellEditor(jtf);
        d.setCellEditor(dce);

        panel.add(table,BorderLayout.CENTER);
    }
}
