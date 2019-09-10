package com.seu.vCampus.Client.AcademicAffairs.Student;

import com.seu.vCampus.Client.AcademicAffairs.Utils.TabbedPaneUI;

import javax.swing.*;
import java.awt.*;

public class StudentAcademicMainPanel extends JPanel {
    public CourseSelectionHallPanel getCourseSelectionHallPanel() {
        return courseSelectionHallPanel;
    }

    public void setCourseSelectionHallPanel(CourseSelectionHallPanel courseSelectionHallPanel) {
        this.courseSelectionHallPanel = courseSelectionHallPanel;
    }

    private CourseSelectionHallPanel courseSelectionHallPanel;
    private CoursesSelectedPanel coursesSelectedPanel;
    private ScheduleTable scheduleTablePanel;
    private QueryExams queryExamsPanel;
    private QueryGrades queryGradesPanel;
    private JTabbedPane switchPane;
    private TabbedPaneUI tabbedPaneUI;
    public StudentAcademicMainPanel() {
        this.setLayout(new BorderLayout());
        courseSelectionHallPanel = new CourseSelectionHallPanel(this);
        coursesSelectedPanel = new CoursesSelectedPanel();
        scheduleTablePanel = new ScheduleTable();
        queryExamsPanel = new QueryExams();
        queryGradesPanel = new QueryGrades();
        switchPane = new JTabbedPane();
        tabbedPaneUI=new TabbedPaneUI(Color.GRAY,Color.WHITE);
        switchPane.setUI(tabbedPaneUI);
        switchPane.addTab("选课大厅", courseSelectionHallPanel);
        switchPane.addTab("已选课程", coursesSelectedPanel);
        switchPane.addTab("查询课表", scheduleTablePanel);
        switchPane.addTab("查询考试",queryExamsPanel);
        switchPane.addTab("查询成绩",queryGradesPanel);
        add(switchPane);
        switchPane.setTabPlacement(JTabbedPane.TOP);
        switchPane.setVisible(true);
        this.setVisible(true);
    }

    public void refresh() {
        switchPane.setVisible(false);
        switchPane = new JTabbedPane();
        courseSelectionHallPanel = new CourseSelectionHallPanel(this);
        coursesSelectedPanel = new CoursesSelectedPanel();
        scheduleTablePanel = new ScheduleTable();
        queryExamsPanel = new QueryExams();
        queryGradesPanel = new QueryGrades();
        tabbedPaneUI=new TabbedPaneUI(Color.GRAY,Color.WHITE);
        switchPane.setUI(tabbedPaneUI);
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
