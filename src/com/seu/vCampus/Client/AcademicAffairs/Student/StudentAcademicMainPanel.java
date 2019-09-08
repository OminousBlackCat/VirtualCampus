package com.seu.vCampus.Client.AcademicAffairs.Student;

import javax.swing.*;
import java.awt.*;

public class StudentAcademicMainPanel extends JPanel {

    public StudentAcademicMainPanel() {
        this.setLayout(new BorderLayout());
        JPanel selectCoursesPanel = new CourseSelectionHallPanel();
        JPanel coursesSelectedPanel = new CoursesSelectedPanel();
        JPanel coursesTablePanel = new CoursesTable();
        JPanel queryExamsPanel = new QueryExams();
        JPanel queryGradesPanel = new QueryGrades();
        JTabbedPane switchPane = new JTabbedPane();
        switchPane.addTab("选课大厅", selectCoursesPanel);
        switchPane.addTab("已选课程", coursesSelectedPanel);
        switchPane.addTab("查询课表", coursesTablePanel);
        switchPane.addTab("查询考试",queryExamsPanel);
        switchPane.addTab("查询成绩",queryGradesPanel);
        add(switchPane);
        switchPane.setTabPlacement(JTabbedPane.TOP);
    }
}
