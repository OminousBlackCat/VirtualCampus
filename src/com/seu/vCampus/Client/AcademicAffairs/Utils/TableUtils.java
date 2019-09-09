package com.seu.vCampus.Client.AcademicAffairs.Utils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.util.Enumeration;

public class TableUtils {
    public static void FitTableColumns(JTable myTable) {
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
                    .getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferredWidth = (int) myTable.getCellRenderer(row, col)
                        .getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
                        .getPreferredSize().getWidth();
                width = Math.max(width, preferredWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width + myTable.getIntercellSpacing().width + 10);
        }
    }

    public static String ParseCourseTime(String ct) {
        String[] cts = ct.split(";");
        StringBuilder result = new StringBuilder("");
        for (String s : cts) {
            String weekDay, period;
            int wd = Integer.parseInt(s.split("-")[0]);
            int pr = Integer.parseInt(s.split("-")[1]);
            switch (wd) {
                case 1:
                    weekDay = "周一";
                    break;
                case 2:
                    weekDay = "周二";
                    break;
                case 3:
                    weekDay = "周三";
                    break;
                case 4:
                    weekDay = "周四";
                    break;
                case 5:
                    weekDay = "周五";
                    break;
                case 6:
                    weekDay = "周六";
                    break;
                case 7:
                case 0:
                    weekDay = "周日";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + wd);
            }
            period = String.valueOf((pr * 2) - 1) + "~" + String.valueOf((pr * 2)) + "节 ";
            result.append(weekDay).append(period);
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
