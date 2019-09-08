package com.seu.vCampus.Client.Shop;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.BookManage;
import com.seu.vCampus.util.Goods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Shop {

    private Common ShopData;
    private ArrayList<Goods> live;
    private ArrayList<Goods> edu;
    private ArrayList<Goods> elec;
    private ArrayList<Goods> junk;
    private int index=0;
    /*以下是所有panel*/
    private JTabbedPane main;
    private JPanel panel1;
    private JPanel life;
    private JPanel study;
    private JPanel computer;
    private JPanel food;
    private JPanel bill;
    private JPanel lifedesign;
    private JPanel Product2;
    private JPanel Product1;
    private JPanel Product3;
    private JPanel search;
    private JPanel product10;
    private JPanel product11;
    private JPanel product12;
    private JPanel switch4;
    private JPanel billtopay;
    private JPanel search1;
    private JPanel product4;
    private JPanel product5;
    private JPanel product6;
    private JPanel switch1;
    private JPanel switch2;
    private JPanel studydesign;
    private JPanel computerdesign;
    private JPanel fooddesign;
    private JPanel product7;
    private JPanel product9;
    private JPanel switch3;
    private JPanel product8;
    private JPanel list;

    /*以下是life界面的product1/2/3的设定信息*/
    /*product1*/
    private JButton addbutton1;
    private JTextField textField1;
    private JLabel Picture1;
    private JLabel Name1;
    private JLabel Price1;

    /*product2*/
    private JTextField textField2;
    private JButton addbutton2;
    private JLabel Picture2;
    private JLabel Name2;
    private JLabel Price2;

    /*product3*/
    private JTextField textField3;
    private JButton addbutton3;
    private JLabel Picture3;
    private JLabel Name3;
    private JLabel Price3;

    /*换页*/
    private JButton changepage2;
    private JButton changepage1;
    private JLabel page;

    /*以下是study界面的product4/5/6的设定信息*/
    /*product4*/
    private JLabel Picture4;
    private JLabel Name4;
    private JLabel Price4;
    private JButton addbutton4;
    private JTextField textField4;

    /*product5*/
    private JLabel Picture5;
    private JLabel Name5;
    private JLabel Price5;
    private JButton addbutton5;
    private JTextField textField5;

    /*product6*/
    private JLabel Picture6;
    private JLabel Name6;
    private JLabel Price6;
    private JButton addbutton6;
    private JTextField textField6;

    /*换页*/
    private JLabel page2;
    private JButton cahngepage3;
    private JButton changepage4;

    /*以下是computer界面的product7/8/9的设定信息*/
    /*product7*/
    private JLabel Picture7;
    private JLabel Name7;
    private JLabel Price7;
    private JTextField textField7;
    private JButton addbutton7;

    /*product8*/
    private JLabel Picture8;
    private JLabel Name8;
    private JLabel Price8;
    private JTextField textField8;
    private JButton addbutton8;

    /*product9*/
    private JLabel Picture9;
    private JLabel Name9;
    private JLabel Price9;
    private JTextField textField9;
    private JButton addbutton9;

    /*换页*/
    private JButton changepage5;
    private JButton changepage6;
    private JLabel page3;

    /*以下是food界面的product10/11/12的设定信息*/
    /*product10*/
    private JLabel Picture10;
    private JLabel Name10;
    private JLabel Price10;
    private JTextField textField10;
    private JButton addbutton10;

    /*product11*/
    private JLabel Picture11;
    private JLabel Name11;
    private JLabel Price11;
    private JTextField textField11;
    private JButton addbutton11;

    /*product12*/
    private JLabel Picture12;
    private JLabel Name12;
    private JLabel Price12;
    private JTextField textField12;
    private JButton addbutton12;

    /*换页*/
    private JButton changepage7;
    private JButton changepage8;
    private JLabel page4;

    /*以下是search界面的设定信息*/
    /*搜索框*/
    private JTextField searchtextField;
    private JButton searchbutton;

    /*结账界面*/
    /*设定信息*/
    private JButton pay;
    private JPasswordField passwordField1;
    private JButton clear;
    private JButton delete;
    private JLabel Totalcost;
    private JLabel Yleft;
    private JLabel label115;

    /*以下label不需要二次更改*/
    private JLabel labels;
    private JLabel label01;
    private JLabel label03;
    private JLabel label02;
    private JLabel label104;
    private JLabel label105;
    private JLabel label114;
    private JLabel label124;
    private JLabel label125;
    private JLabel label4;
    private JLabel label1;
    private JLabel label24;
    private JLabel label25;
    private JLabel label14;
    private JLabel label15;
    private JLabel label35;
    private JLabel label34;
    private JLabel label54;
    private JLabel label55;
    private JLabel label44;
    private JLabel label45;
    private JLabel label64;
    private JLabel label65;
    private JLabel label2;
    private JLabel label84;
    private JLabel label74;
    private JLabel label75;
    private JLabel label94;
    private JLabel label95;
    private JLabel label3;
    /*商品搜索结果界面设定*/
    private JPanel SearchResult;
    private JTextField SearchtextField;
    private JButton SearchAddButton;
    private JLabel SearchPicture;
    private JLabel SearchName;
    private JLabel SearchPrice;
    private JLabel label0;
    private JLabel label00;
    private JTable ShopList;

    public Shop() {
        ShopData = Common.getInstance();
        SearchResult.setVisible(false);

    }

    public void initilize(){
        int size=ShopData.getShopInformation().getGoods().size();
        int counter=0;
        while(counter<size){
            switch (ShopData.getShopInformation().getGoods().get(counter).getGoodsNumber().indexOf(0)){
                case '1':
                    live.add(ShopData.getShopInformation().getGoods().get(counter));
                    break;
                case '2':
                    edu.add(ShopData.getShopInformation().getGoods().get(counter));
                    break;
                case '3':
                    elec.add(ShopData.getShopInformation().getGoods().get(counter));
                    break;
                case '4':
                    junk.add(ShopData.getShopInformation().getGoods().get(counter));
                    break;
            }
            counter++;
        }
        Name1.setText(Name1.getText()+live.get(0).getGoodsName());
        Price1.setText(Price1.getText()+Double.toString(live.get(0).getGoodsPrice()));
        Name2.setText(Name2.getText()+live.get(1).getGoodsName());
        Price2.setText(Price2.getText()+Double.toString(live.get(1).getGoodsPrice()));
        Name3.setText(Name3.getText()+live.get(2).getGoodsName());
        Price3.setText(Price3.getText()+Double.toString(live.get(2).getGoodsPrice()));

        Name4.setText(Name4.getText()+edu.get(0).getGoodsName());
        Price4.setText(Picture4.getText()+Double.toString(edu.get(0).getGoodsPrice()));
        Name5.setText(Name5.getText()+edu.get(1).getGoodsName());
        Price5.setText(Price5.getText()+Double.toString(edu.get(1).getGoodsPrice()));
        Name6.setText(Name6.getText()+edu.get(2).getGoodsName());
        Price6.setText(Price6.getText()+Double.toString(edu.get(2).getGoodsPrice()));

        Name7.setText(Name7.getText()+elec.get(0).getGoodsName());
        Price7.setText(Price7.getText()+Double.toString(elec.get(0).getGoodsPrice()));
        Name8.setText(Name8.getText()+elec.get(1).getGoodsName());
        Price8.setText(Price8.getText()+Double.toString(elec.get(1).getGoodsPrice()));
        Name9.setText(Name9.getText()+elec.get(2).getGoodsName());
        Price9.setText(Price9.getText()+Double.toString(elec.get(2).getGoodsPrice()));

        Name10.setText(Name10.getText()+junk.get(0).getGoodsName());
        Price10.setText(Price10.getText()+Double.toString(junk.get(0).getGoodsPrice()));
        Name11.setText(Name11.getText()+junk.get(1).getGoodsName());
        Price11.setText(Price11.getText()+Double.toString(junk.get(1).getGoodsPrice()));
        Name12.setText(Name12.getText()+junk.get(2).getGoodsName());
        Price12.setText(Price2.getText()+Double.toString(junk.get(2).getGoodsPrice()));
    }

    private void AddProduct(){

    }

    private boolean refreash(ArrayList<Goods> goods){
        if(goods.size()>index){
            index+=3;
            return true;
        }else
            return false;
    }

    private void AddtoShoppingTrolley(ArrayList<Goods> goods,int index,String goodsAmount){
        if(goods.get(index).getGoodsStock()<Integer.parseInt(goodsAmount))
            JOptionPane.showMessageDialog(null,"库存不足","错误",JOptionPane.ERROR_MESSAGE);
        else{

        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-14672351));
        panel1.setToolTipText("");
        main = new JTabbedPane();
        main.setBackground(new Color(-8355712));
        main.setEnabled(true);
        main.setForeground(new Color(-1));
        main.setTabPlacement(1);
        panel1.add(main, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(1200, 800), null, 0, false));
        search = new JPanel();
        search.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        search.setBackground(new Color(-8355712));
        main.addTab("搜索界面", search);
        search1 = new JPanel();
        search1.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        search1.setBackground(new Color(-8355712));
        search.add(search1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labels = new JLabel();
        labels.setForeground(new Color(-1));
        labels.setText("请输入你要搜索的商品BID：");
        search1.add(labels, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchtextField = new JTextField();
        search1.add(searchtextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchbutton = new JButton();
        searchbutton.setBackground(new Color(-14672351));
        searchbutton.setForeground(new Color(-1));
        searchbutton.setText("搜索");
        search1.add(searchbutton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        search1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        search1.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        search1.add(spacer3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        search1.add(spacer4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        search1.add(spacer5, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        search1.add(spacer6, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        search1.add(spacer7, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        search1.add(spacer8, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        search1.add(spacer9, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        SearchResult = new JPanel();
        SearchResult.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        SearchResult.setBackground(new Color(-8355712));
        SearchResult.setEnabled(true);
        search.add(SearchResult, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        SearchPicture = new JLabel();
        SearchPicture.setText("商品图片");
        SearchResult.add(SearchPicture, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SearchName = new JLabel();
        SearchName.setForeground(new Color(-1));
        SearchName.setText("商品名称");
        SearchResult.add(SearchName, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SearchPrice = new JLabel();
        SearchPrice.setForeground(new Color(-1));
        SearchPrice.setText("商品价格");
        SearchResult.add(SearchPrice, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label00 = new JLabel();
        label00.setForeground(new Color(-1));
        label00.setText("购入数量：");
        SearchResult.add(label00, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SearchtextField = new JTextField();
        SearchResult.add(SearchtextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        SearchAddButton = new JButton();
        SearchAddButton.setBackground(new Color(-14672351));
        SearchAddButton.setForeground(new Color(-1));
        SearchAddButton.setText("添加");
        SearchResult.add(SearchAddButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        SearchResult.add(spacer10, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label0 = new JLabel();
        label0.setForeground(new Color(-1));
        label0.setText("元");
        SearchResult.add(label0, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        life = new JPanel();
        life.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        main.addTab("生活用品", life);
        lifedesign = new JPanel();
        lifedesign.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        lifedesign.setBackground(new Color(-8355712));
        life.add(lifedesign, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(403, 28), null, 0, false));
        Product1 = new JPanel();
        Product1.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product1.setBackground(new Color(-8355712));
        lifedesign.add(Product1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture1 = new JLabel();
        Picture1.setText("商品图片");
        Product1.add(Picture1, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name1 = new JLabel();
        Name1.setForeground(new Color(-1));
        Name1.setText("商品名称");
        Product1.add(Name1, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price1 = new JLabel();
        Price1.setForeground(new Color(-1));
        Price1.setHorizontalAlignment(10);
        Price1.setHorizontalTextPosition(11);
        Price1.setText("商品价格");
        Product1.add(Price1, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label14 = new JLabel();
        label14.setForeground(new Color(-1));
        label14.setText("购入数量：");
        Product1.add(label14, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addbutton1 = new JButton();
        addbutton1.setBackground(new Color(-14672351));
        addbutton1.setForeground(new Color(-1));
        addbutton1.setText("添加");
        Product1.add(addbutton1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        Product1.add(textField1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer11 = new Spacer();
        Product1.add(spacer11, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label15 = new JLabel();
        label15.setForeground(new Color(-1));
        label15.setText("元");
        Product1.add(label15, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Product2 = new JPanel();
        Product2.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product2.setBackground(new Color(-8355712));
        lifedesign.add(Product2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture2 = new JLabel();
        Picture2.setText("商品图片");
        Product2.add(Picture2, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name2 = new JLabel();
        Name2.setForeground(new Color(-1));
        Name2.setText("商品名称");
        Product2.add(Name2, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price2 = new JLabel();
        Price2.setForeground(new Color(-1));
        Price2.setText("商品价格");
        Product2.add(Price2, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label24 = new JLabel();
        label24.setForeground(new Color(-1));
        label24.setText("购入数量：");
        Product2.add(label24, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        Product2.add(textField2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addbutton2 = new JButton();
        addbutton2.setBackground(new Color(-14672351));
        addbutton2.setForeground(new Color(-1));
        addbutton2.setText("添加");
        Product2.add(addbutton2, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        Product2.add(spacer12, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label25 = new JLabel();
        label25.setForeground(new Color(-1));
        label25.setText("元");
        Product2.add(label25, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Product3 = new JPanel();
        Product3.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product3.setBackground(new Color(-8355712));
        lifedesign.add(Product3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture3 = new JLabel();
        Picture3.setText("商品图片");
        Product3.add(Picture3, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label34 = new JLabel();
        label34.setForeground(new Color(-1));
        label34.setText("购入数量：");
        Product3.add(label34, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name3 = new JLabel();
        Name3.setForeground(new Color(-1));
        Name3.setText("商品名称");
        Product3.add(Name3, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        Product3.add(textField3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addbutton3 = new JButton();
        addbutton3.setBackground(new Color(-14672351));
        addbutton3.setForeground(new Color(-1));
        addbutton3.setText("添加");
        Product3.add(addbutton3, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        Product3.add(spacer13, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label35 = new JLabel();
        label35.setForeground(new Color(-1));
        label35.setText("元");
        Product3.add(label35, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price3 = new JLabel();
        Price3.setForeground(new Color(-1));
        Price3.setText("商品价格");
        Product3.add(Price3, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        switch1 = new JPanel();
        switch1.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        switch1.setBackground(new Color(-8355712));
        lifedesign.add(switch1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        label1 = new JLabel();
        label1.setForeground(new Color(-1));
        label1.setText("当前页数：");
        switch1.add(label1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        page = new JLabel();
        page.setForeground(new Color(-1));
        page.setText("page");
        switch1.add(page, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changepage2 = new JButton();
        changepage2.setBackground(new Color(-14672351));
        changepage2.setForeground(new Color(-1));
        changepage2.setText("下一页");
        switch1.add(changepage2, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changepage1 = new JButton();
        changepage1.setBackground(new Color(-14672351));
        changepage1.setForeground(new Color(-1));
        changepage1.setText("上一页");
        switch1.add(changepage1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        switch1.add(spacer14, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer15 = new Spacer();
        switch1.add(spacer15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        study = new JPanel();
        study.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        main.addTab("教材工具", study);
        studydesign = new JPanel();
        studydesign.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        studydesign.setBackground(new Color(-8355712));
        study.add(studydesign, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(403, 28), null, 0, false));
        product4 = new JPanel();
        product4.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        product4.setBackground(new Color(-8355712));
        studydesign.add(product4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture4 = new JLabel();
        Picture4.setText("商品图片");
        product4.add(Picture4, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name4 = new JLabel();
        Name4.setForeground(new Color(-1));
        Name4.setText("商品名称");
        product4.add(Name4, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price4 = new JLabel();
        Price4.setForeground(new Color(-1));
        Price4.setHorizontalAlignment(10);
        Price4.setHorizontalTextPosition(11);
        Price4.setText("商品价格");
        product4.add(Price4, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label44 = new JLabel();
        label44.setForeground(new Color(-1));
        label44.setText("购入数量：");
        product4.add(label44, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addbutton4 = new JButton();
        addbutton4.setBackground(new Color(-14672351));
        addbutton4.setForeground(new Color(-1));
        addbutton4.setText("添加");
        product4.add(addbutton4, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField4 = new JTextField();
        product4.add(textField4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer16 = new Spacer();
        product4.add(spacer16, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label45 = new JLabel();
        label45.setForeground(new Color(-1));
        label45.setText("元");
        product4.add(label45, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        product5 = new JPanel();
        product5.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        product5.setBackground(new Color(-8355712));
        studydesign.add(product5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture5 = new JLabel();
        Picture5.setText("商品图片");
        product5.add(Picture5, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name5 = new JLabel();
        Name5.setForeground(new Color(-1));
        Name5.setText("商品名称");
        product5.add(Name5, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price5 = new JLabel();
        Price5.setForeground(new Color(-1));
        Price5.setText("商品价格");
        product5.add(Price5, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label54 = new JLabel();
        label54.setForeground(new Color(-1));
        label54.setText("购入数量：");
        product5.add(label54, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField5 = new JTextField();
        product5.add(textField5, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addbutton5 = new JButton();
        addbutton5.setBackground(new Color(-14672351));
        addbutton5.setForeground(new Color(-1));
        addbutton5.setText("添加");
        product5.add(addbutton5, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer17 = new Spacer();
        product5.add(spacer17, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label55 = new JLabel();
        label55.setForeground(new Color(-1));
        label55.setText("元");
        product5.add(label55, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        product6 = new JPanel();
        product6.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        product6.setBackground(new Color(-8355712));
        studydesign.add(product6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture6 = new JLabel();
        Picture6.setText("商品图片");
        product6.add(Picture6, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label64 = new JLabel();
        label64.setForeground(new Color(-1));
        label64.setText("购入数量：");
        product6.add(label64, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price6 = new JLabel();
        Price6.setForeground(new Color(-1));
        Price6.setText("商品价格");
        product6.add(Price6, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name6 = new JLabel();
        Name6.setForeground(new Color(-1));
        Name6.setText("商品名称");
        product6.add(Name6, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField6 = new JTextField();
        product6.add(textField6, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addbutton6 = new JButton();
        addbutton6.setBackground(new Color(-14672351));
        addbutton6.setForeground(new Color(-1));
        addbutton6.setText("添加");
        product6.add(addbutton6, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer18 = new Spacer();
        product6.add(spacer18, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label65 = new JLabel();
        label65.setForeground(new Color(-1));
        label65.setText("元");
        product6.add(label65, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        switch2 = new JPanel();
        switch2.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        switch2.setBackground(new Color(-8355712));
        studydesign.add(switch2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        label2 = new JLabel();
        label2.setForeground(new Color(-1));
        label2.setText("当前页数：");
        switch2.add(label2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        page2 = new JLabel();
        page2.setForeground(new Color(-1));
        page2.setText("page");
        switch2.add(page2, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changepage4 = new JButton();
        changepage4.setBackground(new Color(-14672351));
        changepage4.setForeground(new Color(-1));
        changepage4.setText("下一页");
        switch2.add(changepage4, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cahngepage3 = new JButton();
        cahngepage3.setBackground(new Color(-14672351));
        cahngepage3.setForeground(new Color(-1));
        cahngepage3.setText("上一页");
        switch2.add(cahngepage3, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer19 = new Spacer();
        switch2.add(spacer19, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer20 = new Spacer();
        switch2.add(spacer20, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        computer = new JPanel();
        computer.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        main.addTab("电子配件", computer);
        computerdesign = new JPanel();
        computerdesign.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        computerdesign.setBackground(new Color(-8355712));
        computer.add(computerdesign, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(403, 28), null, 0, false));
        product7 = new JPanel();
        product7.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        product7.setBackground(new Color(-8355712));
        computerdesign.add(product7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Name7 = new JLabel();
        Name7.setForeground(new Color(-1));
        Name7.setText("商品名称");
        product7.add(Name7, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price7 = new JLabel();
        Price7.setForeground(new Color(-1));
        Price7.setHorizontalAlignment(10);
        Price7.setHorizontalTextPosition(11);
        Price7.setText("商品价格");
        product7.add(Price7, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label74 = new JLabel();
        label74.setForeground(new Color(-1));
        label74.setText("购入数量：");
        product7.add(label74, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addbutton7 = new JButton();
        addbutton7.setBackground(new Color(-14672351));
        addbutton7.setForeground(new Color(-1));
        addbutton7.setText("添加");
        product7.add(addbutton7, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField7 = new JTextField();
        product7.add(textField7, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer21 = new Spacer();
        product7.add(spacer21, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label75 = new JLabel();
        label75.setForeground(new Color(-1));
        label75.setText("元");
        product7.add(label75, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Picture7 = new JLabel();
        Picture7.setText("商品图片");
        product7.add(Picture7, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        product8 = new JPanel();
        product8.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        product8.setBackground(new Color(-8355712));
        computerdesign.add(product8, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture8 = new JLabel();
        Picture8.setText("商品图片");
        product8.add(Picture8, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name8 = new JLabel();
        Name8.setForeground(new Color(-1));
        Name8.setText("商品名称");
        product8.add(Name8, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price8 = new JLabel();
        Price8.setForeground(new Color(-1));
        Price8.setText("商品价格");
        product8.add(Price8, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label84 = new JLabel();
        label84.setForeground(new Color(-1));
        label84.setText("购入数量：");
        product8.add(label84, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField8 = new JTextField();
        product8.add(textField8, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addbutton8 = new JButton();
        addbutton8.setBackground(new Color(-14672351));
        addbutton8.setForeground(new Color(-1));
        addbutton8.setText("添加");
        product8.add(addbutton8, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer22 = new Spacer();
        product8.add(spacer22, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setForeground(new Color(-1));
        label5.setText("元");
        product8.add(label5, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        product9 = new JPanel();
        product9.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        product9.setBackground(new Color(-8355712));
        computerdesign.add(product9, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture9 = new JLabel();
        Picture9.setText("商品图片");
        product9.add(Picture9, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label94 = new JLabel();
        label94.setForeground(new Color(-1));
        label94.setText("购入数量：");
        product9.add(label94, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price9 = new JLabel();
        Price9.setForeground(new Color(-1));
        Price9.setText("商品价格");
        product9.add(Price9, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField9 = new JTextField();
        product9.add(textField9, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addbutton9 = new JButton();
        addbutton9.setBackground(new Color(-14672351));
        addbutton9.setForeground(new Color(-1));
        addbutton9.setText("添加");
        product9.add(addbutton9, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer23 = new Spacer();
        product9.add(spacer23, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label95 = new JLabel();
        label95.setForeground(new Color(-1));
        label95.setText("元");
        product9.add(label95, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name9 = new JLabel();
        Name9.setForeground(new Color(-1));
        Name9.setText("商品名称");
        product9.add(Name9, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        switch3 = new JPanel();
        switch3.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        switch3.setBackground(new Color(-8355712));
        computerdesign.add(switch3, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        label3 = new JLabel();
        label3.setForeground(new Color(-1));
        label3.setText("当前页数：");
        switch3.add(label3, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        page3 = new JLabel();
        page3.setForeground(new Color(-1));
        page3.setText("page");
        switch3.add(page3, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changepage6 = new JButton();
        changepage6.setBackground(new Color(-14672351));
        changepage6.setForeground(new Color(-1));
        changepage6.setText("下一页");
        switch3.add(changepage6, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changepage5 = new JButton();
        changepage5.setBackground(new Color(-14672351));
        changepage5.setForeground(new Color(-1));
        changepage5.setText("上一页");
        switch3.add(changepage5, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer24 = new Spacer();
        switch3.add(spacer24, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer25 = new Spacer();
        switch3.add(spacer25, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        food = new JPanel();
        food.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        main.addTab("零食饮料", food);
        fooddesign = new JPanel();
        fooddesign.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        fooddesign.setBackground(new Color(-8355712));
        food.add(fooddesign, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(403, 28), null, 0, false));
        product10 = new JPanel();
        product10.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        product10.setBackground(new Color(-8355712));
        fooddesign.add(product10, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture10 = new JLabel();
        Picture10.setText("商品图片");
        product10.add(Picture10, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name10 = new JLabel();
        Name10.setForeground(new Color(-1));
        Name10.setText("商品名称");
        product10.add(Name10, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price10 = new JLabel();
        Price10.setForeground(new Color(-1));
        Price10.setHorizontalAlignment(10);
        Price10.setHorizontalTextPosition(11);
        Price10.setText("商品价格");
        product10.add(Price10, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label104 = new JLabel();
        label104.setForeground(new Color(-1));
        label104.setText("购入数量：");
        product10.add(label104, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addbutton10 = new JButton();
        addbutton10.setBackground(new Color(-14672351));
        addbutton10.setForeground(new Color(-1));
        addbutton10.setText("添加");
        product10.add(addbutton10, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField10 = new JTextField();
        product10.add(textField10, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer26 = new Spacer();
        product10.add(spacer26, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label105 = new JLabel();
        label105.setForeground(new Color(-1));
        label105.setText("元");
        product10.add(label105, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        product11 = new JPanel();
        product11.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        product11.setBackground(new Color(-8355712));
        fooddesign.add(product11, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture11 = new JLabel();
        Picture11.setText("商品图片");
        product11.add(Picture11, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name11 = new JLabel();
        Name11.setForeground(new Color(-1));
        Name11.setText("商品名称");
        product11.add(Name11, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price11 = new JLabel();
        Price11.setForeground(new Color(-1));
        Price11.setText("商品价格");
        product11.add(Price11, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label114 = new JLabel();
        label114.setForeground(new Color(-1));
        label114.setText("购入数量：");
        product11.add(label114, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField11 = new JTextField();
        product11.add(textField11, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addbutton11 = new JButton();
        addbutton11.setBackground(new Color(-14672351));
        addbutton11.setForeground(new Color(-1));
        addbutton11.setText("添加");
        product11.add(addbutton11, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label115 = new JLabel();
        label115.setForeground(new Color(-1));
        label115.setText("元");
        product11.add(label115, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer27 = new Spacer();
        product11.add(spacer27, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        product12 = new JPanel();
        product12.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        product12.setBackground(new Color(-8355712));
        fooddesign.add(product12, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture12 = new JLabel();
        Picture12.setText("商品图片");
        product12.add(Picture12, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label124 = new JLabel();
        label124.setForeground(new Color(-1));
        label124.setText("购入数量：");
        product12.add(label124, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price12 = new JLabel();
        Price12.setForeground(new Color(-1));
        Price12.setText("商品价格");
        product12.add(Price12, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name12 = new JLabel();
        Name12.setForeground(new Color(-1));
        Name12.setText("商品名称");
        product12.add(Name12, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField12 = new JTextField();
        product12.add(textField12, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addbutton12 = new JButton();
        addbutton12.setBackground(new Color(-14672351));
        addbutton12.setForeground(new Color(-1));
        addbutton12.setText("添加");
        product12.add(addbutton12, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label125 = new JLabel();
        label125.setForeground(new Color(-1));
        label125.setText("元");
        product12.add(label125, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer28 = new Spacer();
        product12.add(spacer28, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        switch4 = new JPanel();
        switch4.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        switch4.setBackground(new Color(-8355712));
        fooddesign.add(switch4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        label4 = new JLabel();
        label4.setForeground(new Color(-1));
        label4.setText("当前页数：");
        switch4.add(label4, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        page4 = new JLabel();
        page4.setForeground(new Color(-1));
        page4.setText("page");
        switch4.add(page4, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changepage8 = new JButton();
        changepage8.setBackground(new Color(-14672351));
        changepage8.setForeground(new Color(-1));
        changepage8.setText("下一页");
        switch4.add(changepage8, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changepage7 = new JButton();
        changepage7.setBackground(new Color(-14672351));
        changepage7.setForeground(new Color(-1));
        changepage7.setText("上一页");
        switch4.add(changepage7, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer29 = new Spacer();
        switch4.add(spacer29, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer30 = new Spacer();
        switch4.add(spacer30, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        bill = new JPanel();
        bill.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        bill.setBackground(new Color(-8355712));
        main.addTab("购物车", bill);
        list = new JPanel();
        list.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        list.setBackground(new Color(-8355712));
        bill.add(list, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        clear = new JButton();
        clear.setBackground(new Color(-14672351));
        clear.setForeground(new Color(-1));
        clear.setText("清空购物车");
        list.add(clear, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delete = new JButton();
        delete.setBackground(new Color(-14672351));
        delete.setForeground(new Color(-1));
        delete.setText("删除选中项");
        list.add(delete, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer31 = new Spacer();
        list.add(spacer31, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer32 = new Spacer();
        list.add(spacer32, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer33 = new Spacer();
        list.add(spacer33, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        billtopay = new JPanel();
        billtopay.setLayout(new GridLayoutManager(4, 6, new Insets(0, 0, 0, 0), -1, -1));
        billtopay.setBackground(new Color(-8355712));
        bill.add(billtopay, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        label01 = new JLabel();
        label01.setForeground(new Color(-1));
        label01.setText("商品总价值：");
        billtopay.add(label01, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label02 = new JLabel();
        label02.setForeground(new Color(-1));
        label02.setText("一卡通余额：");
        billtopay.add(label02, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label03 = new JLabel();
        label03.setForeground(new Color(-1));
        label03.setText("请输入密码结账：");
        billtopay.add(label03, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pay = new JButton();
        pay.setBackground(new Color(-14672351));
        pay.setForeground(new Color(-1));
        pay.setText("支付");
        billtopay.add(pay, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordField1 = new JPasswordField();
        billtopay.add(passwordField1, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Totalcost = new JLabel();
        Totalcost.setForeground(new Color(-1));
        Totalcost.setText("Label");
        billtopay.add(Totalcost, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Yleft = new JLabel();
        Yleft.setForeground(new Color(-1));
        Yleft.setText("Label");
        billtopay.add(Yleft, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer34 = new Spacer();
        billtopay.add(spacer34, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer35 = new Spacer();
        billtopay.add(spacer35, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer36 = new Spacer();
        billtopay.add(spacer36, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer37 = new Spacer();
        billtopay.add(spacer37, new GridConstraints(0, 4, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer38 = new Spacer();
        billtopay.add(spacer38, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer39 = new Spacer();
        billtopay.add(spacer39, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer40 = new Spacer();
        billtopay.add(spacer40, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer41 = new Spacer();
        billtopay.add(spacer41, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer42 = new Spacer();
        billtopay.add(spacer42, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer43 = new Spacer();
        billtopay.add(spacer43, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    public JPanel getPanel() {
        return panel1;
    }
    public static void main(String args[]){
        Shop shop = new Shop();
        JFrame frame = new JFrame();
        frame.setBounds(500,500,1200,800);
        shop.$$$getRootComponent$$$().setBackground(Color.black);
        frame.setContentPane(shop.$$$getRootComponent$$$());
        frame.setVisible(true);


    }

}
