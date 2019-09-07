package com.seu.vCampus.Client.Library;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Book;

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
    private Common ABookData;
    private static String[] columnNames= {"Name of Book",
            "Author",
            "类型",
            "ISBN"
    };

    protected static DefaultTableModel AModel = new DefaultTableModel(null,columnNames){
        @Override
        public boolean isCellEditable(int row, int column){
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (column == 3) {
                return false;
            } else {
                return true;
            }
        }
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
                    int Blistsize=ABookData.getBookInformation().getBookList().size();
                    int cnt=0;
                    boolean find=false;
                    while(cnt<=Blistsize){
                        Book NBook=ABookData.getBookInformation().getBookList().get(cnt);
                        if(AModel.getValueAt(selectedRow,3)==NBook.getBID()){
                            find=true;
                            //delete book
                            AModel.removeRow(selectedRow);
                            break;
                        }
                        cnt++;
                    }

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
        //Initialize AModel
        ABookData=Common.getInstance();
        int Blistsize=ABookData.getBookInformation().getBookList().size();
        int cnt=0;
        while(cnt<=Blistsize){
            Book NBook=ABookData.getBookInformation().getBookList().get(cnt);
            AModel.setValueAt(NBook.getName(),cnt,0);
            AModel.setValueAt(NBook.getAuthor(),cnt,1);
            //LModel.setValueAt(type,cnt,2);
            AModel.setValueAt(NBook.getBID(),cnt,3);
            cnt++;
        }

        //Create a table with a sorter.
        sorter = new TableRowSorter<DefaultTableModel>(AModel);
        AdminLibTable = new JTable(AModel);
        AdminLibTable.setRowSorter(sorter);

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