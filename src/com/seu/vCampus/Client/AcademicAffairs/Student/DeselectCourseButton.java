package com.seu.vCampus.Client.AcademicAffairs.Student;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Course;
import com.seu.vCampus.util.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeselectCourseButton extends DefaultCellEditor  {
    private Common messenger;
    private JButton button;
    public DeselectCourseButton(final JTable table, final StudentAcademicMainPanel toRefresh, CourseSelectionHallPanel csP) {
        super(new JTextField());

        this.setClickCountToStart(1);
        this.button = new JButton();
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeselectCourseButton.this.fireEditingCanceled();
                if(table.getValueAt(table.getSelectedRow(), 9).equals("退课")) {
                    String courseNumber = (String) table.getValueAt(table.getSelectedRow(), 0);
                    courseNumber = courseNumber.concat("-" + (String) table.getValueAt(table.getSelectedRow(), 3));
                    System.out.println(courseNumber);
                    choose(courseNumber);
                    int index = csP.getSemComboBox().getSelectedIndex();
                    toRefresh.refresh();
                    toRefresh.getCourseSelectionHallPanel().getSemComboBox().setSelectedIndex(index);
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

    private void choose(String courseNumber) {

        if (JOptionPane.showConfirmDialog(null,"确定退课？") == JOptionPane.YES_OPTION) {
            messenger = Common.getInstance();
            Course course = new Course();
            course.setECardNumber(messenger.getUser().getECardNumber());
            course.setCourseNumber(courseNumber);
            course.setType(Message.MESSAGE_TYPE.TYPE_DESELECT_COURSE);
            messenger.getIO().SendMessages(course);
            course = (Course) messenger.getIO().ReceiveMessage();
            Message.MESSAGE_TYPE type = course.getType();
            if(type == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
                JOptionPane.showMessageDialog(null,"退课成功","提示",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null,"错误", "非常抱歉",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
