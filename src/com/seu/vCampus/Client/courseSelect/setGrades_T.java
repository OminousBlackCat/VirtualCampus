package com.seu.vCampus.Client.courseSelect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;


public class setGrades_T extends JFrame {

    private JTable table;
    private DefaultTableModel defaultModel;
    public void getinf(){

    }
    public setGrades_T(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());

        Vector columnNames=new Vector();
        columnNames.add("一卡通");
        columnNames.add("学生姓名");
        columnNames.add("成绩");
        columnNames.add("选择");

        Vector rowData=new Vector();
        Vector vecRow=new Vector();

        getinf();//获取学生信息

        defaultModel=new DefaultTableModel(vecRow,columnNames);
        table=new JTable(defaultModel);
        panel.add(table,BorderLayout.CENTER);
    }
}
