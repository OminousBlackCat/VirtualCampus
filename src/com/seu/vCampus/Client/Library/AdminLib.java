package com.seu.vCampus.Client.Library;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Book;
import com.seu.vCampus.util.Message;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.PatternSyntaxException;

public class AdminLib {
    public JPanel AdminLibMPanel;
    private JButton DeleteBookButton;
    private JButton AddBookButton;
    private JTextField FilterField;
    protected JTable AdminLibTable;
    private static String[] columnNames = {"Name of Book",
            "Author",
            "类型",
            "ISBN"
    };
    protected static DefaultTableModel AModel = new DefaultTableModel(null, columnNames) {
        @Override
        public boolean isCellEditable(int row, int column) {
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
    private Common ABookData;

    public AdminLib() {
        $$$setupUI$$$();
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
                int viewRow = AdminLibTable.getSelectedRow();
                int modelRow = -1;
                if (viewRow < 0) {
                    //Selection got filtered away.
                } else {
                    modelRow = AdminLibTable.convertRowIndexToModel(viewRow);
                }
                if (modelRow != -1) {
                    Book NBook = ABookData.getBookInformation().getBookList().get(modelRow);
                    AModel.removeRow(modelRow);
                    NBook.setType(Message.MESSAGE_TYPE.TYPE_DELETE_BOOK);
                    ABookData.getIO().SendMessages(NBook);
                    NBook = (Book) ABookData.getIO().ReceiveMessage();
                    if (NBook.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
                        JOptionPane.showMessageDialog(null, "删除图书数据库操作成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "删除图书数据库操作失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
                /**
                 * delete
                 */
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
        } catch (PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        ABookData = Common.getInstance();
        int Blistsize = ABookData.getBookInformation().getBookList().size();
        int cnt = 0;
        while (cnt <= Blistsize) {
            Book NBook = ABookData.getBookInformation().getBookList().get(cnt);
            Object[] newRow = {NBook.getName(), NBook.getAuthor(), "Undecided", NBook.getBID()};
            AModel.addRow(newRow);
            cnt++;
        }

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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        AdminLibMPanel = new JPanel();
        AdminLibMPanel.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        AdminLibMPanel.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        AddBookButton = new JButton();
        AddBookButton.setText("Add Book");
        AdminLibMPanel.add(AddBookButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AdminLibMPanel.add(FilterField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(400, -1), new Dimension(400, -1), 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        AdminLibMPanel.add(scrollPane1, new GridConstraints(0, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        AdminLibTable.setFillsViewportHeight(true);
        AdminLibTable.setRowHeight(30);
        scrollPane1.setViewportView(AdminLibTable);
        DeleteBookButton = new JButton();
        DeleteBookButton.setText("Delete Book");
        AdminLibMPanel.add(DeleteBookButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Filter");
        AdminLibMPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        label1.setLabelFor(FilterField);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return AdminLibMPanel;
    }

}