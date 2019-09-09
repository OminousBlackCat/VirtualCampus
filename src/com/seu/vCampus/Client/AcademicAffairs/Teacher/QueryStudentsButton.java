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
import java.util.ArrayList;

public class QueryStudentsButton extends DefaultCellEditor {
    private Common messenger;
    private JButton button;
    private ArrayList<Course> courses;

    public QueryStudentsButton(final JTable table) {
        super(new JTextField());
        this.setClickCountToStart(1);
        this.button = new JButton();
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messenger = Common.getInstance();
                QueryStudentsButton.this.fireEditingCanceled();
                if(table.getValueAt(table.getSelectedRow(), 10).equals("查看学生名单")) {
                        String courseNumber = (String) table.getValueAt(table.getSelectedRow(), 0)
                                + "-" + (String) table.getValueAt(table.getSelectedRow(), 2);
                    System.out.println(courseNumber);
                    courses = new ArrayList<Course>();
                    Course tempCourse = new Course();
                    tempCourse.setCourseNumber(courseNumber);
                    courses.add(tempCourse);
                    Person me = new Person();
                    me.setECardNumber(messenger.getUser().getECardNumber());
                    me.setCourses(courses);
                    me.setType(Message.MESSAGE_TYPE.TYPE_GET_ENROLLED_STUDENTS);
                    messenger.getIO().SendMessages(me);
                    me = (Person) messenger.getIO().ReceiveMessage();
                    if(me.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
                        courses = me.getCourses();
                        JFrame ViewStudentsFrame = new JFrame();
                        String[] columnNames = {"一卡通号", "姓名"};
                        Object[][] data = new Object[courses.size()][2];

                        for(int i = 0; i < courses.size(); i++) {
                            Course c = courses.get(i);
                            data[i][0] = c.getECardNumber();
                            data[i][1] = c.getStudentName();
                        }

                        JTable coursesTable = new JTable(data, columnNames);
                        coursesTable.setLayout(new BorderLayout());
                        coursesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        TableUtils.FitTableColumns(coursesTable);
                        coursesTable.setFont(new Font("微软雅黑",Font.PLAIN,16));
                        coursesTable.setRowHeight(25);
                        coursesTable.setDefaultEditor(Object.class, null);
                        JScrollPane scrollPane = new JScrollPane(coursesTable);
                        ViewStudentsFrame.setBounds(500,200,300,600);
                        ViewStudentsFrame.getContentPane().setLayout(new BorderLayout());
                        ViewStudentsFrame.getContentPane().add(scrollPane,BorderLayout.CENTER);
                        ViewStudentsFrame.setTitle("学生名单");
                        JTextField tfd = new JTextField(courses.get(0).getCourseName() + "   学生名单");
                        tfd.setEditable(false);
                        ViewStudentsFrame.getContentPane().add(tfd, BorderLayout.NORTH);
                        ViewStudentsFrame.setVisible(true);
                    }

                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.button.setText(value == null ? "" : String.valueOf(value));
        return this.button;
    }

    @Override
    public Object getCellEditorValue() {
        return this.button.getText();
    }
}
