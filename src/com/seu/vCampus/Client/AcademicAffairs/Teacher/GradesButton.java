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

public class GradesButton extends DefaultCellEditor {
    private JButton button;

    public GradesButton(final JTable table, QueryAllMyCoursesPanel qacP, TeacherMainPanel tmP) {
        super(new JTextField());
        this.setClickCountToStart(1);
        this.button = new JButton();
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GradesButton.this.fireEditingCanceled();
                String realCourseNumber = (String) table.getValueAt(table.getSelectedRow(), 0)
                        + "-" + (String) table.getValueAt(table.getSelectedRow(), 2);
                if(table.getValueAt(table.getSelectedRow(), 11).equals("查看成绩册")) {
                    new GradesFrame(realCourseNumber, true, qacP,tmP);
                }
                else {
                    new GradesFrame(realCourseNumber, false,qacP,tmP);
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
