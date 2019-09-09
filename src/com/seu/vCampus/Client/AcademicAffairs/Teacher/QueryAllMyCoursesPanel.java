package com.seu.vCampus.Client.AcademicAffairs.Teacher;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TableUtils;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

public class QueryAllMyCoursesPanel extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private ArrayList<String> semesters;
    private JComboBox<String> semComboBox;
    private Common commonData;
    private Person user;
    private Course course;
    private ArrayList<Course> courses;
    private Vector<java.io.Serializable> rowData;
    private Vector<Vector<java.io.Serializable>> rowVec;
    private DefaultTableModel defaultModel;
    private ArrayList<QuerySemesterCourses> qSCs;
    private QuerySemesterCourses onTop;

    public QueryAllMyCoursesPanel(){
        setLayout(new BorderLayout());
        commonData = Common.getInstance();
        user = new Person();
        user.setECardNumber(commonData.getUser().getECardNumber());
        user.setType(Message.MESSAGE_TYPE.TYPE_GET_LECTURER_COURSES);
        commonData.getIO().SendMessages(user);
        user = (Person) commonData.getIO().ReceiveMessage();
        courses = user.getCourses();
        if(!courses.isEmpty()) {
            semesters = new ArrayList<String>();

            for (Course c : courses) {
                String tempSem = c.getCourseSemester();
                if (!semesters.contains(tempSem)) {
                    semesters.add(tempSem);
                }
            }
            String[] temp = new String[semesters.size()];
            temp = semesters.toArray(temp);
            semComboBox = new JComboBox<String>(temp);
            add(semComboBox,BorderLayout.NORTH);
            qSCs = new ArrayList<QuerySemesterCourses>();
            for (String s : semesters) {
                qSCs.add(new QuerySemesterCourses(s));
            }

            onTop = qSCs.get(0);
            add(onTop,BorderLayout.CENTER);
            onTop.setVisible(true);

            semComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    onTop.setVisible(false);
                    onTop = qSCs.get(semComboBox.getSelectedIndex());
                    add(onTop,BorderLayout.CENTER);
                    onTop.setVisible(true);
                }
            });

        }
        else {
            JTextField noCourses = new JTextField("老师，没有您教授的课程呢~");
            add(noCourses,BorderLayout.CENTER);
        }
    }
}
