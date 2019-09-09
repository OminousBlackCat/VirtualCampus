package com.seu.vCampus.Client.Library;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Book;
import com.seu.vCampus.util.Message;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.StyleContext;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.PatternSyntaxException;

public class StuLib {
    public StuLib() {
        $$$setupUI$$$();
        RenewalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = Stutable.getSelectedRow();
                if (selectedRow != -1) {
                    SModel.setValueAt(30, selectedRow, 4);

                    Object NIsbn = Stutable.getValueAt(selectedRow, 3);
                    int Blistsize = BookData.getBookInformation().getBookList().size();
                    int cnt = 0;
                    while (cnt < Blistsize) {
                        Book NBook = BookData.getBookInformation().getBookList().get(cnt);
                        Object NBid = NBook.getBID();
                        if (NIsbn.equals(NBid)) {
                            SModel.setValueAt(30, selectedRow, 4);
                            NBook.setLendDays((short) 0);
                            NBook.setType(Message.MESSAGE_TYPE.TYPE_RENEWAL_BOOK);
                            BookData.getIO().SendMessages(NBook);
                            NBook = (Book) BookData.getIO().ReceiveMessage();
                            if (NBook.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
                                JOptionPane.showMessageDialog(null, "续借操作成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "续借操作失败", "错误", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        cnt++;
                    }
                }
            }
        });
        ReturnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = Stutable.getSelectedRow();
                if (selectedRow != -1) {
                    Object NIsbn = Stutable.getValueAt(selectedRow, 3);
                    int Blistsize = BookData.getBookInformation().getBookList().size();
                    int cnt = 0;
                    while (cnt < Blistsize) {
                        Book NBook = BookData.getBookInformation().getBookList().get(cnt);
                        Object NBid = NBook.getBID();
                        if (NIsbn.equals(NBid)) {
                            SModel.removeRow(selectedRow);
                            LModel.setValueAt("在库", cnt, 4);
                            NBook.setLent(false);
                            NBook.setType(Message.MESSAGE_TYPE.TYPE_RETURN_BOOK);
                            BookData.getIO().SendMessages(NBook);
                            NBook = (Book) BookData.getIO().ReceiveMessage();
                            if (NBook.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
                                JOptionPane.showMessageDialog(null, "还书操作成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "还书操作失败", "错误", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        cnt++;
                    }

                }
            }
        });
        borrowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int viewRow = Libtable.getSelectedRow();
                int modelRow = -1;
                if (viewRow < 0) {
                    //Selection got filtered away.
                } else {
                    modelRow = Libtable.convertRowIndexToModel(viewRow);
                }
                if (modelRow != -1) {

                    Book NBook = BookData.getBookInformation().getBookList().get(modelRow);
                    if (NBook.isLent()) {
                        JOptionPane.showMessageDialog(null, "这本书已经有主了", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    LModel.setValueAt("已被借阅", modelRow, 4);
                    Object[] newRow = {LModel.getValueAt(modelRow, 0),
                            LModel.getValueAt(modelRow, 1),
                            LModel.getValueAt(modelRow, 2),
                            LModel.getValueAt(modelRow, 3),
                            30
                    };
                    SModel.addRow(newRow);
                    NBook.setLent(true);
                    NBook.setType(Message.MESSAGE_TYPE.TYPE_LEND_BOOK);
                    BookData.getIO().SendMessages(NBook);
                    NBook = (Book) BookData.getIO().ReceiveMessage();
                    if (NBook.getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
                        JOptionPane.showMessageDialog(null, "借书数据库操作成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "借书数据库操作失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
    private static ImageIcon MybookIcon = new ImageIcon("src/icon/left/library.png");
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
    private TableRowSorter<DefaultTableModel> sorter;
    private Common BookData;

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


    protected static DefaultTableModel SModel = new DefaultTableModel(null, StutableHeader) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    protected static DefaultTableModel LModel = new DefaultTableModel(null, LibtableHeader) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private void createUIComponents() {
        // TODO: place custom component creation code here
//<<<<<<<HEAD
//
//        Object[] Data1 = {"How to play dota2", "Maou", "游戏", 222, 5};
//        Object[] Data2 = {"How to play guitar", "Sora", "音乐", 444, 27};
//        Object[] Data3 = {"How to play csgo", "Maou", "白给", 111, false};
//
//        SModel.addRow(Data1);
//        SModel.addRow(Data2);
//        Stutable = new JTable(SModel);
//        LModel.addRow(Data3);
//=======
        BookData = Common.getInstance();
        int Blistsize = BookData.getBookInformation().getBookList().size();
        int cnt = 0;
        while (cnt < Blistsize) {

            Book NBook = BookData.getBookInformation().getBookList().get(cnt);
            Object[] newRow = {NBook.getName(), NBook.getAuthor(), "Undecided", NBook.getBID()};
            LModel.addRow(newRow);
            if (NBook.isLent()) {
                LModel.setValueAt("已被借阅", cnt, 4);
            } else {
                LModel.setValueAt("在库", cnt, 4);
            }
            String NEcard = NBook.getECardNumber();
            String NUser = BookData.getUser().getECardNumber();
            if (NBook.isLent()) {
                if (NEcard.equals(NUser)) {
                    SModel.addRow(newRow);
                    SModel.setValueAt(30 - NBook.getLendDays(), SModel.getRowCount(), 4);
                }
            }
            cnt++;
        }


        Stutable = new JTable(SModel);
        Libtable = new JTable(LModel);

        //Create a table with a sorter.
        sorter = new TableRowSorter<DefaultTableModel>(LModel);
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
        } catch (PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }


    public JPanel getPanel() {
        return LibBPanel;
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
        LibMPanel = new JPanel();
        LibMPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        LibtabbedPane = new JTabbedPane();
        LibMPanel.add(LibtabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(1200, 800), null, 0, false));
        StuBPanel = new JPanel();
        StuBPanel.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        LibtabbedPane.addTab("MyBooks", StuBPanel);
        ReturnButton = new JButton();
        ReturnButton.setText("还书");
        StuBPanel.add(ReturnButton, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RenewalButton = new JButton();
        RenewalButton.setText("续借");
        StuBPanel.add(RenewalButton, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        StuScrollPane = new JScrollPane();
        StuBPanel.add(StuScrollPane, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        Stutable.setAutoCreateColumnsFromModel(true);
        Stutable.setAutoCreateRowSorter(true);
        Stutable.setFillsViewportHeight(true);
        Stutable.setRowHeight(30);
        StuScrollPane.setViewportView(Stutable);
        final Spacer spacer1 = new Spacer();
        StuBPanel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        LibBPanel = new JPanel();
        LibBPanel.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        LibtabbedPane.addTab("Library", LibBPanel);
        borrowButton = new JButton();
        borrowButton.setText("Borrow");
        LibBPanel.add(borrowButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LibScrollPane = new JScrollPane();
        LibBPanel.add(LibScrollPane, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        Libtable.setAutoCreateColumnsFromModel(true);
        Libtable.setAutoCreateRowSorter(false);
        Libtable.setEnabled(true);
        Libtable.setFillsViewportHeight(true);
        Libtable.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        Libtable.setRowHeight(30);
        LibScrollPane.setViewportView(Libtable);
        LibBPanel.add(FilterText, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, -1), new Dimension(400, -1), new Dimension(500, -1), 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Filter");
        LibBPanel.add(label1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Status");
        LibBPanel.add(label2, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Label");
        LibBPanel.add(label3, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        LibBPanel.add(spacer2, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return LibMPanel;
    }
}
