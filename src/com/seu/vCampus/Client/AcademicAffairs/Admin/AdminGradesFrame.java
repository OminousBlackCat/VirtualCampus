package com.seu.vCampus.Client.AcademicAffairs.Admin;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class AdminGradesFrame extends JFrame {

    private Person admin;
    private Common messenger;
    private Course course;
    private ArrayList<Course> courseList;
    private ArrayList<Course> students;
    private Vector<String> courseName;
    private JTable table;
    private JScrollPane scrollPane;
    private JTextField textField;
    private JLabel jLabel;

    public AdminGradesFrame(String courseNumber, boolean isRead, AdminMainPanel amP){
        setBounds(200, 200, 420, 500);
        getContentPane().setLayout(new BorderLayout());
        messenger = Common.getInstance();
        admin = new Person();
        course = new Course();
        course.setCourseNumber(courseNumber);
        courseList = new ArrayList<>();
        courseList.add(course);
        admin.setECardNumber(messenger.getUser().getECardNumber());
        admin.setCourses(courseList);
        admin.setType(Message.MESSAGE_TYPE.TYPE_GET_ENROLLED_STUDENTS);
        messenger.getIO().SendMessages(admin);
        admin = (Person) messenger.getIO().ReceiveMessage();
        courseList = admin.getCourses();
        if(admin.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS && !courseList.isEmpty() ) {
            setVisible(true);
            if(isRead) {
                setTitle("查看成绩");
                jLabel = new JLabel(courseList.get(0).getCourseName() + " 学生名单");
                jLabel.setHorizontalAlignment(JLabel.CENTER);
                jLabel.setVerticalAlignment(JLabel.CENTER);
                getContentPane().add(jLabel, BorderLayout.NORTH);
                String[] columnNames = {"一卡通号", "姓名"};
                Object[][] data = new Object[courseList.size()][2];

                for(int i = 0; i < courseList.size(); i++) {
                    Course c = courseList.get(i);
                    data[i][0] = c.getECardNumber();
                    data[i][1] = c.getStudentName();
                }
                table = new JTable(data, columnNames);
                table.setLayout(new BorderLayout());
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                TableUtils.FitTableColumns(table);
                table.setFont(new Font("微软雅黑",Font.PLAIN,16));
                table.setRowHeight(25);
                table.setDefaultEditor(Object.class, null);
                scrollPane = new JScrollPane(table);
                setBounds(200, 200, 300, 500);

            }
            else {
                setTitle("修改成绩");
                jLabel = new JLabel(courseList.get(0).getCourseName() + " 成绩册修改");
                jLabel.setHorizontalAlignment(JLabel.CENTER);
                jLabel.setVerticalAlignment(JLabel.CENTER);
                getContentPane().add(jLabel, BorderLayout.NORTH);
                String[] columnNames = {"一卡通号", "姓名","成绩"};
                Object[][] data = new Object[courseList.size()][3];

                for(int i = 0; i < courseList.size(); i++) {
                    Course c = courseList.get(i);
                    data[i][0] = c.getECardNumber();
                    data[i][1] = c.getStudentName();
                    data[i][2] = c.getCourseGrade();
                }
                table = new JTable(data, columnNames) {
                    boolean[] isEdits = {false, false, true};
                    @Override
                    public boolean isCellEditable(int row, int col)
                    {
                        return isEdits[col];
                    }
                };

                table.setLayout(new BorderLayout());
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.getColumnModel().getColumn(0).setPreferredWidth(200);
                table.getColumnModel().getColumn(1).setPreferredWidth(100);
                table.getColumnModel().getColumn(2).setPreferredWidth(100);
                table.setFont(new Font("微软雅黑",Font.PLAIN,16));
                table.setRowHeight(25);
            }
            scrollPane = new JScrollPane(table);
            getContentPane().add(scrollPane, BorderLayout.CENTER);

            if(!isRead) {
                JButton submitButton = new JButton("提交");
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(JOptionPane.showConfirmDialog(null,
                                "确定提交成绩？") == JOptionPane.YES_OPTION) {
                            courseList = new ArrayList<Course>();
                            for(int i = 0; i < table.getRowCount(); i++) {
                                Course c = new Course();
                                c.setECardNumber((String) table.getValueAt(i, 0));
                                c.setCourseNumber(courseNumber);
                                c.setCourseGrade((Integer.parseInt(String.valueOf(table.getValueAt(i,2)))));
                                courseList.add(c);
                            }
                            admin.setCourses(courseList);
                            admin.setType(Message.MESSAGE_TYPE.TYPE_GRADES_INPUT);
                            messenger.getIO().SendMessages(admin);
                            admin = (Person) messenger.getIO().ReceiveMessage();
                            if (admin.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
                                JOptionPane.showMessageDialog(null,"提交成功","提示",
                                        JOptionPane.INFORMATION_MESSAGE);
                                amP.refresh();
                                dispose();
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"错误", "非常抱歉",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });
                getContentPane().add(submitButton,BorderLayout.SOUTH);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "没有学生！", "哎呀",JOptionPane.ERROR_MESSAGE);
        }
    }
}
