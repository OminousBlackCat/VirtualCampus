package com.seu.vCampus.Client.AcademicAffairs.Admin;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.util.ArrayList;

public class QueryCoursesPanel extends JPanel {
    public QueryCoursesPanel() {
        Common messenger = Common.getInstance();
        Person admin = new Person();
        admin.setType(Message.MESSAGE_TYPE.TYPE_QUERY_ALL_COURSES);
        messenger.getIO().SendMessages(admin);
        admin = (Person) messenger.getIO().ReceiveMessage();
        if(admin.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS && !admin.getCourses().isEmpty()) {
            ArrayList<Course> cs = admin.getCourses();
            String[] columnNames = {"课程编号", "课程名", "学期", "教师一卡通号", "教师姓名",
                    "上课地点", "上课时间", "课程类型", "学分", "是否考试", "人数上限","现有学生数","查看","操作"};
            Object[][] data = new Object[cs.size()][14];
            for(int i = 0; i < cs.size(); i++) {
                Course c = cs.get(i);
                data[i][0] = c.getCourseNumber().split("-")[0];
            }
        }
    }
}
