package com.seu.vCampus.Client.AcademicAffairs.Teacher;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

public class GradesFrame extends JFrame {

    private Person teacher;
    private Common messenger;
    private Course course;
    private ArrayList<Course> courseList;
    private ArrayList<Course> students;
    private Vector<String> courseName;
    private JTable table;
    private JScrollPane scrollPane;
    private JTextField textField;

    public GradesFrame(String courseNumber, boolean isRead, QueryAllMyCoursesPanel qacP, TeacherMainPanel tmP){
        setBounds(200, 200, 800, 800);
        getContentPane().setLayout(new BorderLayout());
        messenger = Common.getInstance();
        teacher = new Person();
        course = new Course();
        course.setCourseNumber(courseNumber);
        courseList = new ArrayList<>();
        courseList.add(course);
        teacher.setECardNumber(messenger.getUser().getECardNumber());
        teacher.setCourses(courseList);
        teacher.setType(Message.MESSAGE_TYPE.TYPE_GET_ENROLLED_STUDENTS);
        messenger.getIO().SendMessages(teacher);
        teacher = (Person) messenger.getIO().ReceiveMessage();
        if(teacher.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
            courseList = teacher.getCourses();

            if(isRead) {
                setTitle("查看成绩");
                textField = new JTextField(courseList.get(0).getCourseName() + " 成绩册");
                textField.setEditable(false);
                getContentPane().add(textField, BorderLayout.NORTH);
                String[] columnNames = {"一卡通号", "姓名","成绩"};
                Object[][] data = new Object[courseList.size()][3];

                for(int i = 0; i < courseList.size(); i++) {
                    Course c = courseList.get(i);
                    data[i][0] = c.getECardNumber();
                    data[i][1] = c.getStudentName();
                    data[i][2] = c.getCourseGrade();
                }
                table = new JTable(data, columnNames);
                table.setLayout(new BorderLayout());
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                TableUtils.FitTableColumns(table);
                table.setFont(new Font("微软雅黑",Font.PLAIN,16));
                table.setRowHeight(25);
                table.setDefaultEditor(Object.class, null);
                scrollPane = new JScrollPane(table);

            }
            else {
                setTitle("录入成绩");
                textField = new JTextField(courseList.get(0).getCourseName() + " 成绩册录入栏");
                textField.setEditable(false);
                getContentPane().add(textField, BorderLayout.NORTH);

                String[] columnNames = {"一卡通号", "姓名","请输入成绩"};
                Object[][] data = new Object[courseList.size()][3];

                for(int i = 0; i < courseList.size(); i++) {
                    Course c = courseList.get(i);
                    data[i][0] = c.getECardNumber();
                    data[i][1] = c.getStudentName();
                    data[i][2] = "";
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
                TableUtils.FitTableColumns(table);
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
                                "确定提交成绩？提交后您本人无法再更改成绩哦~") == JOptionPane.YES_OPTION) {
                            courseList = new ArrayList<Course>();
                           for(int i = 0; i < table.getRowCount(); i++) {
                               Course c = new Course();
                               c.setECardNumber((String) table.getValueAt(i, 0));
                               c.setCourseNumber(courseNumber);
                               c.setCourseGrade((Integer.parseInt((String) table.getValueAt(i,2))));
                               courseList.add(c);
                           }
                           teacher.setCourses(courseList);
                           teacher.setType(Message.MESSAGE_TYPE.TYPE_GRADES_INPUT);
                           messenger.getIO().SendMessages(teacher);
                           teacher = (Person) messenger.getIO().ReceiveMessage();
                           if (teacher.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
                               JOptionPane.showMessageDialog(null,"提交成功","提示",
                                       JOptionPane.INFORMATION_MESSAGE);
                               int index = qacP.getSemComboBox().getSelectedIndex();
                               tmP.refresh();
                               tmP.getAllMyCoursesPanel().getSemComboBox().setSelectedIndex(index);
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
            setVisible(true);
        }
    }
}
