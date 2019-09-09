package com.seu.vCampus.Client.AcademicAffairs.Utils;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TableButtonRender implements TableCellRenderer {
    // private JPanel panel;

    private JButton button;

    public TableButtonRender() {

        this.button = new JButton();

    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        this.button.setText(value == null ? "" : String.valueOf(value));
        if (String.valueOf(value).equals("冲突")||String.valueOf(value).equals("已满")
                ||String.valueOf(value).equals("成绩已录入"))
            this.button.setEnabled(false);
        else
            this.button.setEnabled(true);
        table.getColumnModel().getColumn(column).setWidth(String.valueOf(value).length() * 45);
        return this.button;
    }

}