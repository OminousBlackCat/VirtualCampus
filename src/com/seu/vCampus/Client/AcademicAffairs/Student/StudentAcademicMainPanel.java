package com.seu.vCampus.Client.AcademicAffairs.Student;

import javax.swing.*;
import java.awt.*;

public class StudentAcademicMainPanel extends JPanel {
    private CourseSelectionHallPanel courseSelectionHallPanel;
    private CoursesSelectedPanel coursesSelectedPanel;
    private ScheduleTable scheduleTablePanel;
    private QueryExams queryExamsPanel;
    private QueryGrades queryGradesPanel;
    private JTabbedPane switchPane;
    public StudentAcademicMainPanel() {
        this.setLayout(new BorderLayout());
        courseSelectionHallPanel = new CourseSelectionHallPanel(this);
        coursesSelectedPanel = new CoursesSelectedPanel();
        scheduleTablePanel = new ScheduleTable();
        queryExamsPanel = new QueryExams();
        queryGradesPanel = new QueryGrades();
        switchPane = new JTabbedPane();
        switchPane.addTab("选课大厅", courseSelectionHallPanel);
        switchPane.addTab("已选课程", coursesSelectedPanel);
        switchPane.addTab("查询课表", scheduleTablePanel);
        switchPane.addTab("查询考试",queryExamsPanel);
        switchPane.addTab("查询成绩",queryGradesPanel);
        add(switchPane);
        switchPane.setTabPlacement(JTabbedPane.TOP);
    }

    public void refresh() {
        switchPane.setVisible(false);
        switchPane = new JTabbedPane();
        courseSelectionHallPanel = new CourseSelectionHallPanel(this);
        coursesSelectedPanel = new CoursesSelectedPanel();
        scheduleTablePanel = new ScheduleTable();
        queryExamsPanel = new QueryExams();
        queryGradesPanel = new QueryGrades();
        switchPane.addTab("选课大厅", courseSelectionHallPanel);
        switchPane.addTab("已选课程", coursesSelectedPanel);
        switchPane.addTab("查询课表", scheduleTablePanel);
        switchPane.addTab("查询考试",queryExamsPanel);
        switchPane.addTab("查询成绩",queryGradesPanel);
        add(switchPane);
        switchPane.setTabPlacement(JTabbedPane.TOP);
        switchPane.setVisible(true);
    }
}
