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
        // 设置按钮的大小及位置。
        this.button.setBounds(0, 0, 50, 15);
        this.button.setFont(new Font("楷体",Font.PLAIN,15));

    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        // 只为按钮赋值即可。也可以作其它操作，如绘背景等。
        this.button.setText(value == null ? "" : String.valueOf(value));
        if (String.valueOf(value).equals("冲突")||String.valueOf(value).equals("已满"))
            this.button.setEnabled(false);
        else
            this.button.setEnabled(true);
        return this.button;
    }

}