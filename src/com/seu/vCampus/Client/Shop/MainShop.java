package com.seu.vCampus.Client.Shop;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Goods;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;
import com.seu.vCampus.util.ShopManage;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class MainShop {

    private Common ShopData;
    private ProductPage ProductLife;
    private ProductPage ProductComputer;
    private ProductPage ProductStudy;
    private ProductPage ProductFood;
    private Goods searchGoods;


    private static ImageIcon ShopLife = new ImageIcon(Common.picturePath + "/icon/ShopLife.png");
    private static ImageIcon ShopStudy = new ImageIcon(Common.picturePath + "/icon/ShopStudy.png");
    private static ImageIcon ShopComputer = new ImageIcon(Common.picturePath + "/icon/ShopComputer.png");
    private static ImageIcon ShopFood = new ImageIcon(Common.picturePath + "/icon/ShopFood.png");
    private static ImageIcon ShopSearch = new ImageIcon(Common.picturePath + "/icon/ShopSearch.jpg");
    private static ImageIcon ShoppingT = new ImageIcon(Common.picturePath + "/icon/ShoppingTrolley.png");

    private JTabbedPane tabbedPane1;
    private JPanel basis;
    private JPanel Search;
    private JTextField SearchtextField;
    private JButton Searchbutton;
    private JLabel label0;
    private JTextField ResulttextField;
    private JButton AddButton;
    private JLabel Picture0;
    private JLabel Name0;
    private JLabel Price0;
    private JLabel label2;
    private JPanel SearchResult;
    private JPanel ShoppingTrolley;
    private JTable goodsTable;
    private JButton CleanButton;
    private JButton DeleteButton;
    private JButton PayBillButton;
    private JTextField PaytextField;
    private JLabel ECardBalance;
    private JLabel TotalCost;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JPanel SearchPanel;
    private JPanel ShoppingCart;
    private JPanel PayBill;

    private JLabel Stock0;
    private JLabel Advertisement;
    private JLabel AdvertisementSearchPanel;

    private double SumOfMoney = 0;


    private static String[] header = {"编号", "名称", "价格", "数量"};
    private static DefaultTableModel ShopListModel = new DefaultTableModel(null, header) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public MainShop() {

        $$$setupUI$$$();
        ShopData = Common.getInstance();
        tabbedPane1.setIconAt(tabbedPane1.indexOfComponent(Search), ShopSearch);
        tabbedPane1.setIconAt(tabbedPane1.indexOfComponent(ShoppingTrolley), ShoppingT);
        SearchResult.setVisible(false);

        Searchbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int counter = 0;
                while (counter < ShopData.getShopInformation().getGoods().size()) {
                    if (SearchtextField.getText().equals(ShopData.getShopInformation().getGoods().get(counter).getGoodsNumber())) {
                        SearchResult.setVisible(true);
                        Picture0.setText("");
                        Name0.setText(ShopData.getShopInformation().getGoods().get(counter).getGoodsName());
                        Picture0.setIcon(new ImageIcon(Common.picturePath + "/icon/ProductPicture/" + ShopData.getShopInformation().getGoods().get(counter).getGoodsNumber() + ".png"));
                        Picture0.setText("");
                        Price0.setText(Double.toString(ShopData.getShopInformation().getGoods().get(counter).getGoodsPrice()) + "元");
                        Stock0.setText(Double.toString(ShopData.getShopInformation().getGoods().get(counter).getGoodsStock()) + "在库");
                        searchGoods = ShopData.getShopInformation().getGoods().get(counter);
                        SearchResult.setVisible(true);
                        break;
                    }
                    counter++;
                }
                if (counter == ShopData.getShopInformation().getGoods().size()) {
                    JOptionPane.showMessageDialog(null, "未找到匹配商品", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchGoods.getGoodsStock() >= Short.parseShort(ResulttextField.getText())) {
                    Goods temp = new Goods();
                    temp.setGoodsPrice(searchGoods.getGoodsPrice());
                    temp.setGoodsStock(Short.parseShort(ResulttextField.getText()));
                    temp.setGoodsName(searchGoods.getGoodsName());
                    temp.setGoodsNumber(searchGoods.getGoodsNumber());

                    searchGoods.setGoodsStock((short) (searchGoods.getGoodsStock() - Short.parseShort(ResulttextField.getText())));
                    Stock0.setText(Double.toString(searchGoods.getGoodsStock()) + "在库");
                    ShopData.getShoppingList().add(temp);
                    JOptionPane.showMessageDialog(null, "亲添加成功~", "成功", JOptionPane.INFORMATION_MESSAGE);
                    ResulttextField.setText("");
                } else
                    JOptionPane.showMessageDialog(null, "商品库存不足", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        CleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShopData.getShoppingList().clear();
                SumOfMoney = 0;
                TotalCost.setText("0元");

            }
        });
        PayBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PaytextField.getText().equals(ShopData.getUserCount().getBankPassword())) {
                    Double ECB = Double.parseDouble(ECardBalance.getText());
                    if (ECB >= SumOfMoney) {
                        ShopData.getUser().setECardBalance(ECB - SumOfMoney);
                        ShopData.getUser().setType(Message.MESSAGE_TYPE.TYPE_UPDATE_USER);
                        ShopData.getIO().SendMessages(ShopData.getUser());
                        ShopData.setUser((Person) ShopData.getIO().ReceiveMessage());
                        ShopManage sendManage = new ShopManage();
                        sendManage.setGoods(ShopData.getShoppingList());
                        ShopData.getIO().SendMessages(sendManage);
                        ShopData.getIO().ReceiveMessage();
                        ShopData.getShoppingList().clear();
                        ShopData.getShoppingList().clear();
                        ResulttextField.setText("");
                        PaytextField.setText("");
                        JOptionPane.showMessageDialog(null, "支付成功呢亲", "压力马斯内", JOptionPane.INFORMATION_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "一卡通余额不足", "错误", JOptionPane.ERROR_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "支付密码错误", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        initialization();


        Thread ListListener = new Thread(new Runnable() {
            @Override
            public void run() {
                int flag = ShopData.getShoppingList().size();
                while (true) {
                    if (flag == ShopData.getShoppingList().size()) {
                        try {
                            sleep(200);
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                    if (flag < ShopData.getShoppingList().size()) {
                        flag = ShopData.getShoppingList().size();
                        try {
                            sleep(200);
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                        Goods Temp = ShopData.getShoppingList().get(ShopData.getShoppingList().size() - 1);
                        Object[] tempData = {
                                Temp.getGoodsNumber(), Temp.getGoodsName(), Temp.getGoodsPrice(), Temp.getGoodsStock()};
                        SumOfMoney = SumOfMoney + Temp.getGoodsStock() * Temp.getGoodsPrice();
                        TotalCost.setText(Double.toString(SumOfMoney) + "元");
                        ShopListModel.addRow(tempData);
                    }
                    if (flag > ShopData.getShoppingList().size()) {
                        flag = ShopData.getShoppingList().size();
                        try {
                            sleep(200);
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                        int index = goodsTable.getSelectedRow();
                        ShopListModel.removeRow(index);
                    }
                    if (flag != 0 && ShopData.getShoppingList().size() == 0) {
                        for (int i = flag - 1; i >= 0; i--) {
                            ShopListModel.removeRow(i);
                        }
                        flag = ShopData.getShoppingList().size();
                    }
                }
            }
        });
        ListListener.start();
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = goodsTable.getSelectedRow();
                Goods Temp = ShopData.getShoppingList().get(index);
                SumOfMoney = SumOfMoney - Temp.getGoodsStock() * Temp.getGoodsPrice();
                TotalCost.setText(Double.toString(SumOfMoney) + "元");
                ShopData.getShoppingList().remove(index);
            }
        });
    }

    public void initialization() {

        goodsTable.setRowHeight(30);

        switch (ShopData.getSkinNumber()) {
            case 1:
                basis.setBackground(new Color(63, 87, 123));
                tabbedPane1.setBackground(new Color(63, 87, 123));
                Search.setBackground(new Color(63, 87, 123));
                SearchPanel.setBackground(new Color(63, 87, 123));
                SearchResult.setBackground(new Color(63, 87, 123));
                ShoppingTrolley.setBackground(new Color(63, 87, 123));
                ShoppingCart.setBackground(new Color(63, 87, 123));
                PayBill.setBackground(new Color(63, 87, 123));
                ProductLife.initialize();
                ProductComputer.initialize();
                ProductFood.initialize();
                ProductStudy.initialize();
                break;
            case 2:
                basis.setBackground(Color.BLACK);
                tabbedPane1.setBackground(Color.BLACK);
                Search.setBackground(Color.BLACK);
                SearchPanel.setBackground(Color.BLACK);
                SearchResult.setBackground(Color.BLACK);
                ShoppingTrolley.setBackground(Color.BLACK);
                ShoppingCart.setBackground(Color.BLACK);
                PayBill.setBackground(Color.BLACK);
                ProductLife.initialize();
                ProductComputer.initialize();
                ProductFood.initialize();
                ProductStudy.initialize();
                break;
            case 3:
                basis.setBackground(new Color(85, 20, 0));
                tabbedPane1.setBackground(new Color(85, 20, 0));
                Search.setBackground(new Color(85, 20, 0));
                SearchPanel.setBackground(new Color(85, 20, 0));
                SearchResult.setBackground(new Color(85, 20, 0));
                ShoppingTrolley.setBackground(new Color(85, 20, 0));
                ShoppingCart.setBackground(new Color(85, 20, 0));
                PayBill.setBackground(new Color(85, 20, 0));
                ProductLife.initialize();
                ProductComputer.initialize();
                ProductFood.initialize();
                ProductStudy.initialize();
                break;
            case 4:
                basis.setBackground(new Color(0, 70, 40));
                tabbedPane1.setBackground(new Color(0, 70, 40));
                Search.setBackground(new Color(0, 70, 40));
                SearchPanel.setBackground(new Color(0, 70, 40));
                SearchResult.setBackground(new Color(0, 70, 40));
                ShoppingTrolley.setBackground(new Color(0, 70, 40));
                ShoppingCart.setBackground(new Color(0, 70, 40));
                PayBill.setBackground(new Color(0, 70, 40));
                ProductLife.initialize();
                ProductComputer.initialize();
                ProductFood.initialize();
                ProductStudy.initialize();
                break;
        }

        AdvertisementSearchPanel.setText("");
        AdvertisementSearchPanel.setIcon(new ImageIcon(Common.picturePath + "/icon/Advertisement2.jpg"));
        Advertisement.setText("");
        Advertisement.setIcon(new ImageIcon(Common.picturePath + "/icon/Advertisement1.jpg"));

        ECardBalance.setText(Double.toString(ShopData.getUser().getECardBalance()));
        double cost = 0;
        int counter = 0;
        while (counter < ShopData.getShoppingList().size()) {
            cost += ShopData.getShoppingList().get(counter).getGoodsPrice() * ShopData.getShoppingList().get(counter).getGoodsStock();
            counter++;
        }
        TotalCost.setText(Double.toString(cost));

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        ShopData = Common.getInstance();
        tabbedPane1 = new JTabbedPane();

        ArrayList<Goods> Life = new ArrayList<>();
        ArrayList<Goods> Study = new ArrayList<>();
        ArrayList<Goods> Computer = new ArrayList<>();
        ArrayList<Goods> Food = new ArrayList<>();

        for (int i = 0; i < ShopData.getShopInformation().getGoods().size(); i++) {
            Goods temp = ShopData.getShopInformation().getGoods().get(i);
            switch (temp.getGoodsNumber().charAt(0)) {
                case '1':
                    Life.add(temp);
                    break;
                case '2':
                    Study.add(temp);
                    break;
                case '3':
                    Computer.add(temp);
                    break;
                case '4':
                    Food.add(temp);
                    break;
            }
        }

        ProductLife = new ProductPage(Life);
        ProductStudy = new ProductPage(Study);
        ProductComputer = new ProductPage(Computer);
        ProductFood = new ProductPage(Food);
        tabbedPane1.addTab("生活用品", ShopLife, ProductLife.getMainPanel(), "本页面将展示各类生活用品供同学们选择");
        tabbedPane1.addTab("教材工具", ShopStudy, ProductStudy.getMainPanel(), "本页面将展示各类教材工具供同学们选择");
        tabbedPane1.addTab("电子配件", ShopComputer, ProductComputer.getMainPanel(), "本页面将展示各类电子配件供同学们选择");
        tabbedPane1.addTab("零食饮料", ShopFood, ProductFood.getMainPanel(), "本页面将展示各类零食饮料供同学们选择");

        goodsTable = new JTable(ShopListModel);

    }


    public Component getPanel() {
        return basis;
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
        basis = new JPanel();
        basis.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        basis.setBackground(new Color(-14672351));
        tabbedPane1.setBackground(new Color(-8355712));
        basis.add(tabbedPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        Search = new JPanel();
        Search.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        Search.setBackground(new Color(-8355712));
        Search.setEnabled(true);
        Search.setForeground(new Color(-1));
        tabbedPane1.addTab("搜索界面", Search);
        SearchPanel = new JPanel();
        SearchPanel.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        SearchPanel.setBackground(new Color(-8355712));
        Search.add(SearchPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        SearchtextField = new JTextField();
        SearchPanel.add(SearchtextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label0 = new JLabel();
        label0.setForeground(new Color(-1));
        label0.setText("请输入要搜索商品的ID：");
        SearchPanel.add(label0, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Searchbutton = new JButton();
        Searchbutton.setBackground(new Color(-14672351));
        Searchbutton.setForeground(new Color(-16777216));
        Searchbutton.setText("搜索");
        SearchPanel.add(Searchbutton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        SearchPanel.add(spacer1, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        AdvertisementSearchPanel = new JLabel();
        AdvertisementSearchPanel.setText("Label");
        SearchPanel.add(AdvertisementSearchPanel, new GridConstraints(3, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        SearchPanel.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        SearchPanel.add(spacer3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        SearchResult = new JPanel();
        SearchResult.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        SearchResult.setBackground(new Color(-8355712));
        Search.add(SearchResult, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture0 = new JLabel();
        Picture0.setForeground(new Color(-1));
        Picture0.setText("商品图片");
        SearchResult.add(Picture0, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name0 = new JLabel();
        Name0.setForeground(new Color(-1));
        Name0.setText("商品名称");
        SearchResult.add(Name0, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price0 = new JLabel();
        Price0.setForeground(new Color(-1));
        Price0.setText("商品价格");
        SearchResult.add(Price0, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label2 = new JLabel();
        label2.setForeground(new Color(-1));
        label2.setText("购入数量：");
        SearchResult.add(label2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ResulttextField = new JTextField();
        SearchResult.add(ResulttextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton = new JButton();
        AddButton.setBackground(new Color(-14672351));
        AddButton.setForeground(new Color(-16777216));
        AddButton.setText("添加");
        SearchResult.add(AddButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        SearchResult.add(spacer4, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Stock0 = new JLabel();
        Stock0.setForeground(new Color(-1));
        Stock0.setText("商品库存");
        SearchResult.add(Stock0, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ShoppingTrolley = new JPanel();
        ShoppingTrolley.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        ShoppingTrolley.setBackground(new Color(-8355712));
        ShoppingTrolley.setForeground(new Color(-1));
        tabbedPane1.addTab("购物车", ShoppingTrolley);
        ShoppingCart = new JPanel();
        ShoppingCart.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        ShoppingCart.setBackground(new Color(-8355712));
        ShoppingTrolley.add(ShoppingCart, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        ShoppingCart.add(scrollPane1, new GridConstraints(0, 0, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        goodsTable.setAutoCreateRowSorter(true);
        goodsTable.setFillsViewportHeight(true);
        scrollPane1.setViewportView(goodsTable);
        PayBill = new JPanel();
        PayBill.setLayout(new GridLayoutManager(7, 5, new Insets(0, 0, 0, 0), -1, -1));
        PayBill.setBackground(new Color(-8355712));
        ShoppingTrolley.add(PayBill, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        label6 = new JLabel();
        label6.setForeground(new Color(-1));
        label6.setText("一卡通余额：");
        PayBill.add(label6, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label7 = new JLabel();
        label7.setForeground(new Color(-1));
        label7.setText("商品总价值：");
        PayBill.add(label7, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label8 = new JLabel();
        label8.setForeground(new Color(-1));
        label8.setText("请输入密码：");
        PayBill.add(label8, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PaytextField = new JTextField();
        PayBill.add(PaytextField, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        TotalCost = new JLabel();
        TotalCost.setForeground(new Color(-1));
        TotalCost.setText("Label");
        PayBill.add(TotalCost, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ECardBalance = new JLabel();
        ECardBalance.setForeground(new Color(-1));
        ECardBalance.setText("Label");
        PayBill.add(ECardBalance, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Advertisement = new JLabel();
        Advertisement.setText("Label");
        PayBill.add(Advertisement, new GridConstraints(6, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CleanButton = new JButton();
        CleanButton.setBackground(new Color(-14672351));
        CleanButton.setForeground(new Color(-16777216));
        CleanButton.setText("清空购物车");
        PayBill.add(CleanButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PayBillButton = new JButton();
        PayBillButton.setBackground(new Color(-14672351));
        PayBillButton.setForeground(new Color(-16777216));
        PayBillButton.setText("结账");
        PayBill.add(PayBillButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DeleteButton = new JButton();
        DeleteButton.setBackground(new Color(-14672351));
        DeleteButton.setForeground(new Color(-16777216));
        DeleteButton.setText("清除选中项");
        PayBill.add(DeleteButton, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        PayBill.add(spacer5, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        PayBill.add(spacer6, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        PayBill.add(spacer7, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        PayBill.add(spacer8, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return basis;
    }
}
