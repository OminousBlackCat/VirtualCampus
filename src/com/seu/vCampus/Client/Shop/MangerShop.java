package com.seu.vCampus.Client.Shop;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Goods;
import com.seu.vCampus.util.Message;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MangerShop {
    private Common ManagerShopData;
    private JTabbedPane manage;
    private JPanel panel1;
    private JPanel add;
    private JPanel sub;
    private JLabel label;
    private JLabel label2;
    private JLabel Label3;
    private JTextField AddName;
    private JTextField AddNumber;
    private JTextField AddPrice;
    private JButton AddProductButton;
    private JLabel Label4;
    private JTextField AddStock;
    private JTextField ChangeName;
    private JTextField ChangeNumber;
    private JTextField ChangePrice;
    private JTextField ChangeStock;
    private JButton ConfirmChange;
    private JLabel Label8;
    private JLabel Label10;
    private JLabel Label12;
    private JLabel Label13;
    private JPanel adjust;
    private JTable ShopTable;
    private JLabel Advertisement2;
    private JLabel Advertisement1;
    private JButton Choose;
    private JTable ShopTable1;

    private static String[] header = {"GID", "名称", "价格", "数量"};
    private static DefaultTableModel ShopListModel = new DefaultTableModel(null, header) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public MangerShop() {

        $$$setupUI$$$();
        initialization();

        Advertisement1.setText("");
        Advertisement1.setIcon(new ImageIcon("src/icon/90004.jpg"));
        Advertisement2.setText("");
        Advertisement2.setIcon(new ImageIcon("src/icon/90004.jpg"));

        ConfirmChange.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int chooseIndex = ShopTable.getSelectedRow();
                Goods temp = new Goods();
                temp.setGoodsPrice(Double.parseDouble(ChangePrice.getText()));
                temp.setGoodsNumber(ChangeNumber.getText());
                temp.setGoodsStock(Short.parseShort(ChangeStock.getText()));
                temp.setGoodsName(ChangeName.getText());

                ManagerShopData.getShopInformation().getGoods().set(chooseIndex, temp);
                Object[] tempO = {temp.getGoodsNumber(), temp.getGoodsName(), temp.getGoodsPrice(), temp.getGoodsStock()};
                ShopListModel.removeRow(0);
                ShopListModel.insertRow(0, tempO);
            }
        });
        AddProductButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Goods addGoods = new Goods();
                addGoods.setGoodsName(AddName.getText());
                addGoods.setGoodsStock(Short.parseShort(AddStock.getText()));
                addGoods.setGoodsNumber(AddNumber.getText());
                addGoods.setGoodsPrice(Double.parseDouble(AddPrice.getText()));

                ManagerShopData.getShopInformation().getGoods().add(addGoods);
                addGoods.setType(Message.MESSAGE_TYPE.TYPE_ADD_GOODS);
                ManagerShopData.getIO().SendMessages(addGoods);
                ManagerShopData.getIO().ReceiveMessage();

                Object[] temp = {
                        addGoods.getGoodsNumber(), addGoods.getGoodsName(), addGoods.getGoodsPrice(), addGoods.getGoodsStock()
                };
                ShopListModel.addRow(temp);

                JOptionPane.showMessageDialog(null, "添加" + addGoods.getGoodsName() + "你已经成功添加该商品",
                        "成功！", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        Choose.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int ChooseIndex = ShopTable1.getSelectedRow();
                System.out.println(ChooseIndex);
                Goods temp = ManagerShopData.getShopInformation().getGoods().get(ChooseIndex);
                ChangeName.setText(temp.getGoodsName());
                ChangeNumber.setText(temp.getGoodsNumber());
                ChangePrice.setText(Double.toString(temp.getGoodsPrice()));
                ChangeStock.setText(Short.toString(temp.getGoodsStock()));
            }
        });
    }

    public void initialization() {

        ShopTable.setRowHeight(30);
        ShopTable1.setRowHeight(30);

        switch (ManagerShopData.getSkinNumber()) {
            case 1:
                panel1.setBackground(new Color(63, 87, 123));
                add.setBackground(new Color(63, 87, 123));
                sub.setBackground(new Color(63, 87, 123));
                adjust.setBackground(new Color(63, 87, 123));
                break;
            case 2:
                panel1.setBackground(Color.BLACK);
                add.setBackground(Color.BLACK);
                sub.setBackground(Color.BLACK);
                adjust.setBackground(Color.BLACK);
                break;
            case 3:
                panel1.setBackground(new Color(85, 20, 0));
                add.setBackground(new Color(85, 20, 0));
                sub.setBackground(new Color(85, 20, 0));
                adjust.setBackground(new Color(85, 20, 0));
                break;
            case 4:
                panel1.setBackground(new Color(0, 70, 40));
                add.setBackground(new Color(0, 70, 40));
                sub.setBackground(new Color(0, 70, 40));
                adjust.setBackground(new Color(0, 70, 40));
                break;
        }

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
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-14672351));
        manage = new JTabbedPane();
        manage.setBackground(new Color(-8355712));
        manage.setForeground(new Color(-14672351));
        panel1.add(manage, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(1200, 800), null, 0, false));
        add = new JPanel();
        add.setLayout(new GridLayoutManager(7, 5, new Insets(0, 0, 0, 0), -1, -1));
        add.setBackground(new Color(-14672351));
        manage.addTab("增加商品", add);
        label = new JLabel();
        label.setForeground(new Color(-1));
        label.setText("商品名称：");
        add.add(label, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label2 = new JLabel();
        label2.setForeground(new Color(-1));
        label2.setText("商品编号：");
        add.add(label2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Label3 = new JLabel();
        Label3.setForeground(new Color(-1));
        Label3.setText("商品价格：");
        add.add(Label3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AddName = new JTextField();
        add.add(AddName, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddNumber = new JTextField();
        add.add(AddNumber, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddPrice = new JTextField();
        add.add(AddPrice, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddProductButton = new JButton();
        AddProductButton.setBackground(new Color(-14672351));
        AddProductButton.setForeground(new Color(-16777216));
        AddProductButton.setText("添加");
        add.add(AddProductButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Label4 = new JLabel();
        Label4.setForeground(new Color(-1));
        Label4.setText("商品数量：");
        add.add(Label4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AddStock = new JTextField();
        add.add(AddStock, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        add.add(spacer1, new GridConstraints(0, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        add.add(spacer2, new GridConstraints(1, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        add.add(spacer3, new GridConstraints(2, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        add.add(spacer4, new GridConstraints(3, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        add.add(spacer5, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Advertisement1 = new JLabel();
        Advertisement1.setText("Label");
        add.add(Advertisement1, new GridConstraints(6, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        add.add(scrollPane1, new GridConstraints(0, 0, 7, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(ShopTable);
        final Spacer spacer6 = new Spacer();
        add.add(spacer6, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        add.add(spacer7, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        sub = new JPanel();
        sub.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        manage.addTab("商品调整及种类删除", sub);
        adjust = new JPanel();
        adjust.setLayout(new GridLayoutManager(8, 4, new Insets(0, 0, 0, 0), -1, -1));
        adjust.setBackground(new Color(-14672351));
        sub.add(adjust, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Label8 = new JLabel();
        Label8.setForeground(new Color(-1));
        Label8.setText("商品名称：");
        adjust.add(Label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Label10 = new JLabel();
        Label10.setForeground(new Color(-1));
        Label10.setText("商品编号：");
        adjust.add(Label10, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Label12 = new JLabel();
        Label12.setForeground(new Color(-1));
        Label12.setText("商品价格：");
        adjust.add(Label12, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Label13 = new JLabel();
        Label13.setForeground(new Color(-1));
        Label13.setText("商品库存：");
        adjust.add(Label13, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ChangeName = new JTextField();
        adjust.add(ChangeName, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ChangePrice = new JTextField();
        adjust.add(ChangePrice, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ChangeStock = new JTextField();
        adjust.add(ChangeStock, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ChangeNumber = new JTextField();
        adjust.add(ChangeNumber, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer8 = new Spacer();
        adjust.add(spacer8, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        adjust.add(spacer9, new GridConstraints(1, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        adjust.add(spacer10, new GridConstraints(2, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        adjust.add(spacer11, new GridConstraints(3, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Choose = new JButton();
        Choose.setBackground(new Color(-14672351));
        Choose.setForeground(new Color(-16777216));
        Choose.setText("选择修改");
        adjust.add(Choose, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ConfirmChange = new JButton();
        ConfirmChange.setBackground(new Color(-14672351));
        ConfirmChange.setForeground(new Color(-16777216));
        ConfirmChange.setText("确认变更内容");
        adjust.add(ConfirmChange, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        adjust.add(spacer12, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        adjust.add(spacer13, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Advertisement2 = new JLabel();
        Advertisement2.setText("Label");
        adjust.add(Advertisement2, new GridConstraints(7, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        adjust.add(spacer14, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer15 = new Spacer();
        adjust.add(spacer15, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer16 = new Spacer();
        adjust.add(spacer16, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        sub.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane2.setViewportView(ShopTable1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ManagerShopData = Common.getInstance();
        ShopTable = new JTable(ShopListModel);
        ShopTable1 = new JTable(ShopListModel);
        initList();
    }

    private void initList() {
        ShopTable = new JTable(ShopListModel);
        int index = ManagerShopData.getShopInformation().getGoods().size();
        for (int i = 0; i < index; i++) {
            Goods tempG = ManagerShopData.getShopInformation().getGoods().get(i);
            Object[] temp = {
                    tempG.getGoodsNumber(), tempG.getGoodsName(), tempG.getGoodsPrice(), tempG.getGoodsStock()
            };
            ShopListModel.addRow(temp);
        }
    }
}
