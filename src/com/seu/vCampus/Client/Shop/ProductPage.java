package com.seu.vCampus.Client.Shop;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Goods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;

public class ProductPage extends Component {
    /*获取商品信息*/
    private Common ShopProduct;
    /*用于翻页计数*/
    private int PageCounter;
    private int InitialIndex;
    /*各个界面*/
    private JPanel Product1;
    private JPanel Product2;
    private JPanel Product3;
    private JPanel ChangePage;

    /*各个页面的按钮和文本框*/
    private JButton PageUp;
    private JButton PageDown;
    private JTextField textField1;
    private JButton AddButton1;
    private JTextField textField2;
    private JButton AddButton2;
    private JTextField textField3;
    private JButton AddButton3;

    /*各个商品的三个标签*/
    private JLabel Picture1;
    private JLabel Picture2;
    private JLabel Picture3;
    private JLabel Name1;
    private JLabel Name2;
    private JLabel Name3;
    private JLabel Price1;
    private JLabel Price2;
    private JLabel Price3;
    private JLabel Page;

    /*不需要再修改的固定文本标签*/
    private JLabel label11;
    private JLabel label12;
    private JLabel label21;
    private JLabel label22;
    private JLabel label31;
    private JLabel label33;
    private JLabel label0;
    private JPanel panel1;
    private JLabel Stock1;
    private JLabel Stock2;
    private JLabel Stock3;

    private ImageIcon IconA;
    private ImageIcon IconB;
    private ImageIcon IconC;

    private Goods GoodsA;
    private Goods GoodsB;
    private Goods GoodsC;
    private ArrayList<Goods> thisClassGoodsList;
    private int index;
    private Common shopData;


    public ProductPage(ArrayList<Goods> set) {
        thisClassGoodsList = set;
        $$$setupUI$$$();
        shopData = Common.getInstance();
        initialize();
        PageDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index += 3;
                if (index + 3 < thisClassGoodsList.size()) {
                    LoadGoods(3);
                    PageUp.setEnabled(true);
                    PageDown.setEnabled(true);
                } else {
                    LoadGoods(thisClassGoodsList.size() - index);
                    PageDown.setEnabled(false);
                    PageUp.setEnabled(true);
                }
            }
        });
        PageUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index -= 3;
                if (index == 0) {
                    initialize();
                } else {
                    LoadGoods(3);
                    PageUp.setEnabled(true);
                    PageDown.setEnabled(true);
                }
            }
        });
        AddButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Short.parseShort(textField1.getText()) > GoodsA.getGoodsStock()) {
                    JOptionPane.showMessageDialog(null, "你添加的太多了呢亲~", "兄啊你要干什么", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                Goods addThisGoods = new Goods();
                addThisGoods.setGoodsNumber(GoodsA.getGoodsNumber());
                addThisGoods.setGoodsName(GoodsA.getGoodsName());
                addThisGoods.setGoodsPrice(GoodsA.getGoodsPrice());
                addThisGoods.setGoodsStock(Short.parseShort(textField1.getText()));
                GoodsA.setGoodsStock((short) (GoodsA.getGoodsStock() - Short.parseShort(textField1.getText())));
                shopData.getShoppingList().add(addThisGoods);
                JOptionPane.showMessageDialog(null, "添加" + addThisGoods.getGoodsName() + "成功亲~请前往购物车查看~",
                        "成功！", JOptionPane.INFORMATION_MESSAGE);
                textField1.setText("");
                LoadGoodsA();
            }
        });
        AddButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Short.parseShort(textField2.getText()) > GoodsB.getGoodsStock()) {
                    JOptionPane.showMessageDialog(null, "你添加的太多了呢亲~", "兄啊你要干什么", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Goods addThisGoods = new Goods();
                addThisGoods.setGoodsNumber(GoodsB.getGoodsNumber());
                addThisGoods.setGoodsName(GoodsB.getGoodsName());
                addThisGoods.setGoodsPrice(GoodsB.getGoodsPrice());
                addThisGoods.setGoodsStock(Short.parseShort(textField2.getText()));
                GoodsB.setGoodsStock((short) (GoodsB.getGoodsStock() - Short.parseShort(textField2.getText())));
                shopData.getShoppingList().add(addThisGoods);
                JOptionPane.showMessageDialog(null, "添加" + addThisGoods.getGoodsName() + "成功亲~请前往购物车查看~",
                        "成功！", JOptionPane.INFORMATION_MESSAGE);
                textField2.setText("");
                LoadGoodsB(true);
            }
        });

        AddButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Short.parseShort(textField3.getText()) > GoodsC.getGoodsStock()) {
                    JOptionPane.showMessageDialog(null, "你添加的太多了呢亲~", "兄啊你要干什么", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                Goods addThisGoods = new Goods();
                addThisGoods.setGoodsNumber(GoodsC.getGoodsNumber());
                addThisGoods.setGoodsName(GoodsC.getGoodsName());
                addThisGoods.setGoodsPrice(GoodsC.getGoodsPrice());
                addThisGoods.setGoodsStock(Short.parseShort(textField3.getText()));
                GoodsC.setGoodsStock((short) (GoodsC.getGoodsStock() - Short.parseShort(textField3.getText())));
                shopData.getShoppingList().add(addThisGoods);
                JOptionPane.showMessageDialog(null, "添加" + addThisGoods.getGoodsName() + "成功亲~请前往购物车查看~",
                        "成功！", JOptionPane.INFORMATION_MESSAGE);
                textField3.setText("");
                LoadGoodsC(true);
            }
        });
    }

    public void initialize() {

        PageUp.setEnabled(false);
        PageDown.setEnabled(true);

        index = 0;
        if (thisClassGoodsList.size() >= 3) {
            LoadGoods(3);
        } else {
            LoadGoods(thisClassGoodsList.size());
        }
    }

    private void LoadGoods(int x) {
        switch (x) {
            case 1:
                LoadGoodsA();
                LoadGoodsB(false);
                LoadGoodsC(false);
                break;
            case 2:
                LoadGoodsA();
                LoadGoodsB(true);
                LoadGoodsC(false);
                break;
            case 3:
                LoadGoodsA();
                LoadGoodsB(true);
                LoadGoodsC(true);
                break;

        }
    }

    public void LoadGoodsA() {
        GoodsA = thisClassGoodsList.get(index);
        Name1.setText(GoodsA.getGoodsName());
        Price1.setText(Double.toString(GoodsA.getGoodsPrice()));
        Stock1.setText(Short.toString(GoodsA.getGoodsStock()) + "在库");
        IconA = new ImageIcon("src/icon/ProductPicture/" + GoodsA.getPictureNumber() + ".png");
        Picture1.setIcon(IconA);
        Picture1.setText("");
    }

    public void LoadGoodsB(boolean isLoad) {
        if (isLoad) {
            Name2.setVisible(true);
            Price2.setVisible(true);
            AddButton2.setVisible(true);
            textField2.setVisible(true);
            Picture2.setVisible(true);
            label21.setVisible(true);
            label22.setVisible(true);
            Stock2.setVisible(true);
            GoodsB = thisClassGoodsList.get(index + 1);
            Name2.setText(GoodsB.getGoodsName());
            Price2.setText(Double.toString(GoodsB.getGoodsPrice()));
            IconB = new ImageIcon("src/icon/ProductPicture/" + GoodsB.getPictureNumber() + ".png");
            Picture2.setIcon(IconB);
            Stock2.setText(Short.toString(GoodsB.getGoodsStock()) + "在库");
            Picture2.setText("");
        } else {
            Stock2.setVisible(false);
            Name2.setVisible(false);
            Price2.setVisible(false);
            AddButton2.setVisible(false);
            textField2.setVisible(false);
            Picture2.setVisible(false);
            label21.setVisible(false);
            label22.setVisible(false);
        }
    }

    public void LoadGoodsC(boolean isLoad) {
        if (isLoad) {
            Name3.setVisible(true);
            Stock3.setVisible(true);
            Price3.setVisible(true);
            AddButton3.setVisible(true);
            textField3.setVisible(true);
            Picture3.setVisible(true);
            label31.setVisible(true);
            label33.setVisible(true);
            GoodsC = thisClassGoodsList.get(index + 2);
            Name3.setText(GoodsC.getGoodsName());
            Price3.setText(Double.toString(GoodsC.getGoodsPrice()));
            IconC = new ImageIcon("src/icon/ProductPicture/" + GoodsC.getPictureNumber() + ".png");
            Picture3.setIcon(IconC);
            Stock3.setText(Short.toString(GoodsC.getGoodsStock()) + "在库");
            Picture3.setText("");
        } else {
            Stock3.setVisible(false);
            Name3.setVisible(false);
            Price3.setVisible(false);
            AddButton3.setVisible(false);
            textField3.setVisible(false);
            Picture3.setVisible(false);
            label31.setVisible(false);
            label33.setVisible(false);
        }
    }


    public void setThisClassGoodsList(ArrayList<Goods> thisClassGoodsList) {
        this.thisClassGoodsList = thisClassGoodsList;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel1 = new JPanel();
    }


    public JPanel getMainPanel() {
        return panel1;
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
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-8355712));
        Product1 = new JPanel();
        Product1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product1.setBackground(new Color(-8355712));
        panel1.add(Product1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Name1 = new JLabel();
        Name1.setForeground(new Color(-1));
        Name1.setText("商品名称");
        Product1.add(Name1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price1 = new JLabel();
        Price1.setForeground(new Color(-1));
        Price1.setText("商品价格");
        Product1.add(Price1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label12 = new JLabel();
        label12.setBackground(new Color(-12828863));
        label12.setForeground(new Color(-1));
        label12.setText("购入数量：");
        Product1.add(label12, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        Product1.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton1 = new JButton();
        AddButton1.setBackground(new Color(-14672351));
        AddButton1.setForeground(new Color(-1));
        AddButton1.setText("添加");
        Product1.add(AddButton1, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        Product1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Picture1 = new JLabel();
        Picture1.setText("商品图片");
        Product1.add(Picture1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label11 = new JLabel();
        label11.setForeground(new Color(-1));
        label11.setText("元");
        Product1.add(label11, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Stock1 = new JLabel();
        Stock1.setForeground(new Color(-1));
        Stock1.setText("商品库存");
        Product1.add(Stock1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Product3 = new JPanel();
        Product3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product3.setBackground(new Color(-8355712));
        panel1.add(Product3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture3 = new JLabel();
        Picture3.setText("商品图片");
        Product3.add(Picture3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name3 = new JLabel();
        Name3.setForeground(new Color(-1));
        Name3.setText("商品名称");
        Product3.add(Name3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price3 = new JLabel();
        Price3.setForeground(new Color(-1));
        Price3.setText("商品价格");
        Product3.add(Price3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label33 = new JLabel();
        label33.setForeground(new Color(-1));
        label33.setText("购入数量：");
        Product3.add(label33, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        Product3.add(textField3, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton3 = new JButton();
        AddButton3.setBackground(new Color(-14672351));
        AddButton3.setForeground(new Color(-1));
        AddButton3.setText("添加");
        Product3.add(AddButton3, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        Product3.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label31 = new JLabel();
        label31.setForeground(new Color(-1));
        label31.setText("元");
        Product3.add(label31, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Stock3 = new JLabel();
        Stock3.setForeground(new Color(-1));
        Stock3.setText("商品库存");
        Product3.add(Stock3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Product2 = new JPanel();
        Product2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product2.setBackground(new Color(-8355712));
        panel1.add(Product2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture2 = new JLabel();
        Picture2.setText("商品图片");
        Product2.add(Picture2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name2 = new JLabel();
        Name2.setForeground(new Color(-1));
        Name2.setText("商品名称");
        Product2.add(Name2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price2 = new JLabel();
        Price2.setEnabled(true);
        Price2.setForeground(new Color(-1));
        Price2.setText("商品价格");
        Price2.setVerticalAlignment(0);
        Product2.add(Price2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label22 = new JLabel();
        label22.setForeground(new Color(-1));
        label22.setText("购入数量：");
        Product2.add(label22, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        Product2.add(textField2, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton2 = new JButton();
        AddButton2.setBackground(new Color(-14672351));
        AddButton2.setForeground(new Color(-1));
        AddButton2.setText("添加");
        Product2.add(AddButton2, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        Product2.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label21 = new JLabel();
        label21.setForeground(new Color(-1));
        label21.setText("元");
        Product2.add(label21, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Stock2 = new JLabel();
        Stock2.setForeground(new Color(-1));
        Stock2.setText("商品库存");
        Product2.add(Stock2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ChangePage = new JPanel();
        ChangePage.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 6, new Insets(0, 0, 0, 0), -1, -1));
        ChangePage.setBackground(new Color(-8355712));
        panel1.add(ChangePage, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PageUp = new JButton();
        PageUp.setBackground(new Color(-14672351));
        PageUp.setForeground(new Color(-1));
        PageUp.setText("上一页");
        ChangePage.add(PageUp, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label0 = new JLabel();
        label0.setForeground(new Color(-1));
        label0.setText("当前页数：");
        ChangePage.add(label0, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Page = new JLabel();
        Page.setForeground(new Color(-1));
        Page.setText("Label");
        ChangePage.add(Page, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PageDown = new JButton();
        PageDown.setBackground(new Color(-14672351));
        PageDown.setForeground(new Color(-1));
        PageDown.setText("下一页");
        ChangePage.add(PageDown, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        ChangePage.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(1, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        ChangePage.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        ChangePage.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        ChangePage.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        ChangePage.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer9 = new com.intellij.uiDesigner.core.Spacer();
        ChangePage.add(spacer9, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer10 = new com.intellij.uiDesigner.core.Spacer();
        ChangePage.add(spacer10, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
