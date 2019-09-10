/*
package com.seu.vCampus.Client.AcademicAffairs.Admin;

import com.seu.vCampus.util.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeInfoButton extends DefaultCellEditor {
    private JButton button;

    public ChangeInfoButton(final JTable table, AdminMainPanel amP) {
        super(new JTextField());
        this.setClickCountToStart(1);
        this.button = new JButton();
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeInfoButton.this.fireEditingCanceled();
                String realCourseNumber = (String) table.getValueAt(table.getSelectedRow(), 0)
                        + "-" + (String) table.getValueAt(table.getSelectedRow(), 2);
                if(table.getValueAt(table.getSelectedRow(), 13).equals("修改信息")) {
                    if(JOptionPane.showConfirmDialog(null,
                            "确定修改？") == JOptionPane.YES_OPTION) {
                        Course c = new Course( realCourseNumber,table.getValueAt(table.getSelectedRow(),1),
                                (String) table.getValueAt(table.getSelectedRow(),2),
                                (String) table.getValueAt(table.getSelectedRow(),4),
                                (String) table.getValueAt(table.getSelectedRow(),3),
                                (String) table.getValueAt(table.getSelectedRow(),6),
                                table.getValueAt(table.getSelectedRow(),5),
                                table.getValueAt(table.getSelectedRow(),7),
                                table.getValueAt(table.getSelectedRow(),8),
                                Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),10)),
                                Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),11)),
                                (table.getValueAt(table.getSelectedRow(),9)).equals("是"),
                                false);
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
*/
