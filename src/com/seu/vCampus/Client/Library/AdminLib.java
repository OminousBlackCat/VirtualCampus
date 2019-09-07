package com.seu.vCampus.Client.Library;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminLib{
    public JPanel AdminLibMPanel;
    private JButton DeleteBookButton;
    private JButton AddBookButton;
    private JTextField FilterField;
    protected JTable AdminLibTable;
    protected static DefaultTableModel AModel;
    private String[] columnNames= {"Name of Book",
            "Author",
            "类型",
            "ISBN"
    };
    private TableRowSorter<DefaultTableModel> sorter;
    public AdminLib() {
        AddBookButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AdminAddBook.createAndShowGUI();
            }
        });
        DeleteBookButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow=AdminLibTable.getSelectedRow();
                if(selectedRow!=-1){
                    AModel.removeRow(selectedRow);
                }
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
            rf = RowFilter.regexFilter(FilterField.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        AModel = new DefaultTableModel(null,columnNames);


        //Create a table with a sorter.
        sorter = new TableRowSorter<DefaultTableModel>(AModel);
        AdminLibTable = new JTable(AModel);
        AdminLibTable.setRowSorter(sorter);
        AdminLibTable.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        AdminLibTable.setFillsViewportHeight(true);

        //For the purposes of this example, better to have a single
        //selection.
        AdminLibTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Create a separate form for filterText and statusText
        FilterField = new JTextField();
        //Whenever filterText changes, invoke newFilter.
        FilterField.getDocument().addDocumentListener(
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

}