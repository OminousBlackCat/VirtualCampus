package com.seu.vCampus.Client.AcademicAffairs.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGradesButton extends DefaultCellEditor {
    private JButton button;

    public AdminGradesButton(final JTable table, AdminMainPanel amP) {
        super(new JTextField());
        this.setClickCountToStart(1);
        this.button = new JButton();
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminGradesButton.this.fireEditingCanceled();
                String realCourseNumber = (String) table.getValueAt(table.getSelectedRow(), 0)
                        + "-" + (String) table.getValueAt(table.getSelectedRow(), 2);
                if(table.getValueAt(table.getSelectedRow(), 12).equals("查看成绩册")) {
                    new AdminGradesFrame(realCourseNumber, false, amP);
                }
                else {
                    new AdminGradesFrame(realCourseNumber, true, amP);
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
