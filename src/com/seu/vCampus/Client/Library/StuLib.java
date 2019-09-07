package com.seu.vCampus.Client.Library;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StuLib {
    public StuLib() {
        RenewalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow=Stutable.getSelectedRow();
                if(selectedRow!=-1){
                    SModel.setValueAt(30,selectedRow,4);
                }
            }
        });
        ReturnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow=Stutable.getSelectedRow();
                if(selectedRow!=-1){
                    SModel.removeRow(selectedRow);

                }
            }
        });
    }

    public JPanel LibMPanel;
    private JTabbedPane LibtabbedPane;
    private JPanel LibBPanel;
    private JPanel StuBPanel;
    private JButton borrowButton;
    private JTable Libtable;
    private JTextField FilterText;
    private JButton ReturnButton;
    private JButton RenewalButton;
    private JTable Stutable;
    private JScrollPane StuScrollPane;
    private JScrollPane LibScrollPane;
    private TableRowSorter <DefaultTableModel> sorter;
    private static String[] StutableHeader = {"Name of Book",
            "Author",
            "类型",
            "ISBN",
            "剩余借阅天数"
    };
    private static String[] LibtableHeader = {"Name of Book",
            "Author",
            "类型",
            "ISBN",
            "借阅情况"
    };
    protected static DefaultTableModel SModel=new DefaultTableModel(null,StutableHeader){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    protected static DefaultTableModel LModel=new DefaultTableModel(null,LibtableHeader){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    private void createUIComponents() {
        // TODO: place custom component creation code here

        Object[] Data1 = {"How to play dota2", "Maou", "游戏", 222, 5};
        Object[] Data2=  {"How to play guitar", "Sora", "音乐", 444, 27};
        Object[] Data3= {"How to play csgo", "Maou", "白给", 111, false};

        SModel.addRow(Data1);
        SModel.addRow(Data2);
        Stutable=new JTable(SModel);
        LModel.addRow(Data3);
        Libtable=new JTable(LModel);

        //Create a table with a sorter.
        sorter= new TableRowSorter<DefaultTableModel>(LModel);
        Libtable.setRowSorter(sorter);


        //For the purposes of this demo, better to have a single
        //selection.
        Libtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        FilterText = new JTextField();
        //Whenever filterText changes, invoke newFilter.
        FilterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
    }



    /**
     * Update the row filter regular expression from the expression in
     * the text box.
     */
    private void newFilter() {
        RowFilter<DefaultTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(FilterText.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

}
