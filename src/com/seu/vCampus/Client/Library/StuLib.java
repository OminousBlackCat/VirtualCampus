package com.seu.vCampus.Client.Library;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Book;

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
    private Common BookData;
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
    private final short KeepDays=30;
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
        //Initialize S&L Model
        BookData=Common.getInstance();
        int Blistsize=BookData.getBookInformation().getBookList().size();
        int cnt=0;
        String NUser=BookData.getUser().getECardNumber();
        while(cnt<=Blistsize){
            Book NBook=BookData.getBookInformation().getBookList().get(cnt);
            LModel.setValueAt(NBook.getName(),cnt,0);
            LModel.setValueAt(NBook.getAuthor(),cnt,1);
            //LModel.setValueAt(type,cnt,2);
            LModel.setValueAt(NBook.getBID(),cnt,3);
            if(NBook.isLent()){
                LModel.setValueAt("已被借阅",cnt,4);
            }
            String NEcard=NBook.getECardNumber();
            if(NEcard==NUser){
                SModel.setValueAt(NBook.getName(),cnt,0);
                SModel.setValueAt(NBook.getAuthor(),cnt,1);
                //LModel.setValueAt(type,cnt,2);
                SModel.setValueAt(NBook.getBID(),cnt,3);
                SModel.setValueAt(KeepDays-NBook.getLendDays(),cnt,4);
            }
            cnt++;
        }

        Stutable=new JTable(SModel);
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
