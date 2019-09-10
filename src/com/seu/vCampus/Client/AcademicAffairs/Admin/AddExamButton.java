package com.seu.vCampus.Client.AcademicAffairs.Admin;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExamButton extends DefaultCellEditor {
    private JButton button;
    public AddExamButton(JTable table, AdminMainPanel amP) {
        super(new JTextField());
        this.setClickCountToStart(1);
        this.button = new JButton();
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddExamButton.this.fireEditingCanceled();
                String realCourseNumber = (String) table.getValueAt(table.getSelectedRow(), 0)
                        + "-" + (String) table.getValueAt(table.getSelectedRow(), 2);
                if(JOptionPane.showConfirmDialog(null,
                        "确定修改？") == JOptionPane.YES_OPTION) {
                    Course c = new Course(realCourseNumber,
                            (String) table.getValueAt(table.getSelectedRow(),3),
                            (String) table.getValueAt(table.getSelectedRow(),4));
                    c.setType(Message.MESSAGE_TYPE.TYPE_SET_EXAM);
                    Common messenger = Common.getInstance();
                    messenger.getIO().SendMessages(c);
                    if(messenger.getIO().ReceiveMessage().getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
                        JOptionPane.showMessageDialog(null,"成功","提示",
                                JOptionPane.INFORMATION_MESSAGE);
                        amP.refresh();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "失败", "抱歉",
                                JOptionPane.ERROR_MESSAGE);
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
