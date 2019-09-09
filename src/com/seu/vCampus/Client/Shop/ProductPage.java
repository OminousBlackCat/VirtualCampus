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
import java.util.ArrayList;

public class ProductPage extends Component {

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

    private JLabel label12;

    private JLabel label22;

    private JLabel label32;
    private JPanel panel1;
    private JLabel Stock3;
    private JLabel Stock1;
    private JLabel Stock2;
    private JLabel label0;
    private JLabel Page;


    private JPanel Product4;
    private JPanel Product5;
    private JPanel Product6;
    private JLabel Picture4;
    private JLabel Picture5;
    private JLabel Picture6;
    private JLabel Name4;
    private JLabel Name5;
    private JLabel Name6;
    private JLabel Stock4;
    private JLabel Stock5;
    private JLabel Stock6;
    private JLabel Price4;
    private JLabel Price5;
    private JLabel Price6;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton AddButton4;
    private JButton AddButton5;
    private JButton AddButton6;
    private JLabel label42;
    private JLabel label52;
    private JLabel label62;


    private ImageIcon IconA;
    private ImageIcon IconB;
    private ImageIcon IconC;
    private ImageIcon IconD;
    private ImageIcon IconE;
    private ImageIcon IconF;

    private Goods GoodsA;
    private Goods GoodsB;
    private Goods GoodsC;
    private Goods GoodsD;
    private Goods GoodsE;
    private Goods GoodsF;

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
                index += 6;
                if (index + 6 < thisClassGoodsList.size()) {
                    LoadGoods(6);
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
                index -= 6;
                if (index == 0) {
                    initialize();
                } else {
                    LoadGoods(6);
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
        AddButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Goods addThisGoods = new Goods();
                addThisGoods.setGoodsNumber(GoodsD.getGoodsNumber());
                addThisGoods.setGoodsName(GoodsD.getGoodsName());
                addThisGoods.setGoodsPrice(GoodsD.getGoodsPrice());
                addThisGoods.setGoodsStock(Short.parseShort(textField4.getText()));
                GoodsD.setGoodsStock((short) (GoodsD.getGoodsStock() - Short.parseShort(textField4.getText())));
                shopData.getShoppingList().add(addThisGoods);
                JOptionPane.showMessageDialog(null, "添加" + addThisGoods.getGoodsName() + "成功亲~请前往购物车查看~",
                        "成功！", JOptionPane.INFORMATION_MESSAGE);
                textField4.setText("");
            }
        });
        AddButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Goods addThisGoods = new Goods();
                addThisGoods.setGoodsNumber(GoodsE.getGoodsNumber());
                addThisGoods.setGoodsName(GoodsE.getGoodsName());
                addThisGoods.setGoodsPrice(GoodsE.getGoodsPrice());
                addThisGoods.setGoodsStock(Short.parseShort(textField5.getText()));
                GoodsE.setGoodsStock((short) (GoodsE.getGoodsStock() - Short.parseShort(textField5.getText())));
                shopData.getShoppingList().add(addThisGoods);
                JOptionPane.showMessageDialog(null, "添加" + addThisGoods.getGoodsName() + "成功亲~请前往购物车查看~",
                        "成功！", JOptionPane.INFORMATION_MESSAGE);
                textField5.setText("");
            }
        });
        AddButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Goods addThisGoods = new Goods();
                addThisGoods.setGoodsNumber(GoodsF.getGoodsNumber());
                addThisGoods.setGoodsName(GoodsF.getGoodsName());
                addThisGoods.setGoodsPrice(GoodsF.getGoodsPrice());
                addThisGoods.setGoodsStock(Short.parseShort(textField6.getText()));
                GoodsF.setGoodsStock((short) (GoodsF.getGoodsStock() - Short.parseShort(textField6.getText())));
                shopData.getShoppingList().add(addThisGoods);
                JOptionPane.showMessageDialog(null, "添加" + addThisGoods.getGoodsName() + "成功亲~请前往购物车查看~",
                        "成功！", JOptionPane.INFORMATION_MESSAGE);
                textField6.setText("");
            }
        });
    }

    public void initialize() {

        ChangePage.setBackground(null);


        PageUp.setEnabled(false);
        PageDown.setEnabled(true);

        index = 0;
        if (thisClassGoodsList.size() >= 6) {
            LoadGoods(6);
        } else {
            LoadGoods(thisClassGoodsList.size());
        }

        Product1.setBackground(null);
        Product1.setOpaque(false);
        Product2.setBackground(null);
        Product2.setOpaque(false);
        Product3.setBackground(null);
        Product3.setOpaque(false);
        Product4.setBackground(null);
        Product4.setOpaque(false);
        Product5.setBackground(null);
        Product5.setOpaque(false);
        Product6.setBackground(null);
        Product6.setOpaque(false);
        ChangePage.setBackground(null);
        ChangePage.setOpaque(false);


    }

    private void LoadGoods(int x) {
        switch (x) {
            case 1:
                LoadGoodsA();
                LoadGoodsB(false);
                LoadGoodsC(false);
                LoadGoodsD(false);
                LoadGoodsE(false);
                LoadGoodsF(false);
                break;
            case 2:
                LoadGoodsA();
                LoadGoodsB(true);
                LoadGoodsC(false);
                LoadGoodsD(false);
                LoadGoodsE(false);
                LoadGoodsF(false);
                break;
            case 3:
                LoadGoodsA();
                LoadGoodsB(true);
                LoadGoodsC(true);
                LoadGoodsD(false);
                LoadGoodsE(false);
                LoadGoodsF(false);
                break;
            case 4:
                LoadGoodsA();
                LoadGoodsB(true);
                LoadGoodsC(true);
                LoadGoodsD(true);
                LoadGoodsE(false);
                LoadGoodsF(false);
                break;
            case 5:
                LoadGoodsA();
                LoadGoodsB(true);
                LoadGoodsC(true);
                LoadGoodsD(true);
                LoadGoodsE(true);
                LoadGoodsF(false);
                break;
            case 6:
                LoadGoodsA();
                LoadGoodsB(true);
                LoadGoodsC(true);
                LoadGoodsD(true);
                LoadGoodsE(true);
                LoadGoodsF(true);
                break;
        }
    }

    public void LoadGoodsA() {
        GoodsA = thisClassGoodsList.get(index);
        Name1.setText(GoodsA.getGoodsName());
        Price1.setText(Double.toString(GoodsA.getGoodsPrice()) + "元");
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
            label22.setVisible(true);
            Stock2.setVisible(true);
            GoodsB = thisClassGoodsList.get(index + 1);
            Name2.setText(GoodsB.getGoodsName());
            Price2.setText(Double.toString(GoodsB.getGoodsPrice()) + "元");
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
            label32.setVisible(true);
            GoodsC = thisClassGoodsList.get(index + 2);
            Name3.setText(GoodsC.getGoodsName());
            Price3.setText(Double.toString(GoodsC.getGoodsPrice()) + "元");
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
            label32.setVisible(false);
        }
    }

    public void LoadGoodsD(boolean isLoad) {
        if (isLoad) {
            Name4.setVisible(true);
            Price4.setVisible(true);
            AddButton4.setVisible(true);
            textField4.setVisible(true);
            Picture4.setVisible(true);
            label42.setVisible(true);
            Stock4.setVisible(true);
            GoodsD = thisClassGoodsList.get(index + 3);
            Name4.setText(GoodsD.getGoodsName());
            Price4.setText(Double.toString(GoodsD.getGoodsPrice()) + "元");
            IconD = new ImageIcon("src/icon/ProductPicture/" + GoodsD.getPictureNumber() + ".png");
            Picture4.setIcon(IconD);
            Stock4.setText(Short.toString(GoodsD.getGoodsStock()) + "在库");
            Picture4.setText("");
        } else {
            Stock4.setVisible(false);
            Name4.setVisible(false);
            Price4.setVisible(false);
            AddButton4.setVisible(false);
            textField4.setVisible(false);
            Picture4.setVisible(false);
            label42.setVisible(false);
        }
    }

    public void LoadGoodsE(boolean isLoad) {
        if (isLoad) {
            Name5.setVisible(true);
            Price5.setVisible(true);
            AddButton5.setVisible(true);
            textField5.setVisible(true);
            Picture5.setVisible(true);
            label52.setVisible(true);
            Stock5.setVisible(true);
            GoodsE = thisClassGoodsList.get(index + 4);
            Name5.setText(GoodsE.getGoodsName());
            Price5.setText(Double.toString(GoodsE.getGoodsPrice()) + "元");
            IconE = new ImageIcon("src/icon/ProductPicture/" + GoodsE.getPictureNumber() + ".png");
            Picture5.setIcon(IconE);
            Stock5.setText(Short.toString(GoodsE.getGoodsStock()) + "在库");
            Picture5.setText("");
        } else {
            Stock5.setVisible(false);
            Name5.setVisible(false);
            Price5.setVisible(false);
            AddButton5.setVisible(false);
            textField5.setVisible(false);
            Picture5.setVisible(false);
            label52.setVisible(false);
        }
    }

    public void LoadGoodsF(boolean isLoad) {
        if (isLoad) {
            Name6.setVisible(true);
            Price6.setVisible(true);
            AddButton6.setVisible(true);
            textField6.setVisible(true);
            Picture6.setVisible(true);
            label62.setVisible(true);
            Stock6.setVisible(true);
            GoodsF = thisClassGoodsList.get(index + 5);
            Name6.setText(GoodsF.getGoodsName());
            Price6.setText(Double.toString(GoodsF.getGoodsPrice()) + "元");
            IconF = new ImageIcon("src/icon/ProductPicture/" + GoodsF.getPictureNumber() + ".png");
            Picture6.setIcon(IconF);
            Stock6.setText(Short.toString(GoodsF.getGoodsStock()) + "在库");
            Picture6.setText("");
        } else {
            Stock6.setVisible(false);
            Name6.setVisible(false);
            Price6.setVisible(false);
            AddButton6.setVisible(false);
            textField6.setVisible(false);
            Picture6.setVisible(false);
            label62.setVisible(false);
        }
    }

    public void setThisClassGoodsList(ArrayList<Goods> thisClassGoodsList) {
        this.thisClassGoodsList = thisClassGoodsList;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

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
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-16777216));
        Product1 = new JPanel();
        Product1.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product1.setBackground(new Color(-8355712));
        panel1.add(Product1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Name1 = new JLabel();
        Name1.setForeground(new Color(-1));
        Name1.setText("商品名称");
        Product1.add(Name1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price1 = new JLabel();
        Price1.setForeground(new Color(-1));
        Price1.setText("商品价格");
        Product1.add(Price1, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label12 = new JLabel();
        label12.setBackground(new Color(-12828863));
        label12.setForeground(new Color(-1));
        label12.setText("购入数量：");
        Product1.add(label12, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        Product1.add(textField1, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton1 = new JButton();
        AddButton1.setBackground(new Color(-14672351));
        AddButton1.setForeground(new Color(-1));
        AddButton1.setText("添加");
        Product1.add(AddButton1, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        Product1.add(spacer1, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Picture1 = new JLabel();
        Picture1.setText("商品图片");
        Product1.add(Picture1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Stock1 = new JLabel();
        Stock1.setForeground(new Color(-1));
        Stock1.setText("商品库存");
        Product1.add(Stock1, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Product3 = new JPanel();
        Product3.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product3.setBackground(new Color(-8355712));
        panel1.add(Product3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture3 = new JLabel();
        Picture3.setText("商品图片");
        Product3.add(Picture3, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name3 = new JLabel();
        Name3.setForeground(new Color(-1));
        Name3.setText("商品名称");
        Product3.add(Name3, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price3 = new JLabel();
        Price3.setForeground(new Color(-1));
        Price3.setText("商品价格");
        Product3.add(Price3, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label32 = new JLabel();
        label32.setForeground(new Color(-1));
        label32.setText("购入数量：");
        Product3.add(label32, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        Product3.add(textField3, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton3 = new JButton();
        AddButton3.setBackground(new Color(-14672351));
        AddButton3.setForeground(new Color(-1));
        AddButton3.setText("添加");
        Product3.add(AddButton3, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        Product3.add(spacer2, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Stock3 = new JLabel();
        Stock3.setForeground(new Color(-1));
        Stock3.setText("商品库存");
        Product3.add(Stock3, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ChangePage = new JPanel();
        ChangePage.setLayout(new GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), -1, -1));
        ChangePage.setBackground(new Color(-8355712));
        panel1.add(ChangePage, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PageUp = new JButton();
        PageUp.setBackground(new Color(-14672351));
        PageUp.setForeground(new Color(-1));
        PageUp.setText("上一页");
        ChangePage.add(PageUp, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label0 = new JLabel();
        label0.setForeground(new Color(-1));
        label0.setText("当前页数：");
        ChangePage.add(label0, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Page = new JLabel();
        Page.setForeground(new Color(-1));
        Page.setText("Label");
        ChangePage.add(Page, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PageDown = new JButton();
        PageDown.setBackground(new Color(-14672351));
        PageDown.setForeground(new Color(-1));
        PageDown.setText("下一页");
        ChangePage.add(PageDown, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        ChangePage.add(spacer3, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        ChangePage.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Product4 = new JPanel();
        Product4.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product4.setBackground(new Color(-8355712));
        panel1.add(Product4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Name4 = new JLabel();
        Name4.setForeground(new Color(-1));
        Name4.setText("商品名称");
        Product4.add(Name4, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price4 = new JLabel();
        Price4.setForeground(new Color(-1));
        Price4.setText("商品价格");
        Product4.add(Price4, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label42 = new JLabel();
        label42.setBackground(new Color(-12828863));
        label42.setForeground(new Color(-1));
        label42.setText("购入数量：");
        Product4.add(label42, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField4 = new JTextField();
        Product4.add(textField4, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton4 = new JButton();
        AddButton4.setBackground(new Color(-14672351));
        AddButton4.setForeground(new Color(-1));
        AddButton4.setText("添加");
        Product4.add(AddButton4, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        Product4.add(spacer5, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Picture4 = new JLabel();
        Picture4.setText("商品图片");
        Product4.add(Picture4, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Stock4 = new JLabel();
        Stock4.setForeground(new Color(-1));
        Stock4.setText("商品库存");
        Product4.add(Stock4, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Product2 = new JPanel();
        Product2.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product2.setBackground(new Color(-8355712));
        panel1.add(Product2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Picture2 = new JLabel();
        Picture2.setText("商品图片");
        Product2.add(Picture2, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Name2 = new JLabel();
        Name2.setForeground(new Color(-1));
        Name2.setText("商品名称");
        Product2.add(Name2, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price2 = new JLabel();
        Price2.setEnabled(true);
        Price2.setForeground(new Color(-1));
        Price2.setText("商品价格");
        Price2.setVerticalAlignment(0);
        Product2.add(Price2, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label22 = new JLabel();
        label22.setForeground(new Color(-1));
        label22.setText("购入数量：");
        Product2.add(label22, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        Product2.add(textField2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton2 = new JButton();
        AddButton2.setBackground(new Color(-14672351));
        AddButton2.setForeground(new Color(-1));
        AddButton2.setText("添加");
        Product2.add(AddButton2, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        Product2.add(spacer6, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Stock2 = new JLabel();
        Stock2.setForeground(new Color(-1));
        Stock2.setText("商品库存");
        Product2.add(Stock2, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Product5 = new JPanel();
        Product5.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product5.setBackground(new Color(-8355712));
        panel1.add(Product5, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Name5 = new JLabel();
        Name5.setForeground(new Color(-1));
        Name5.setText("商品名称");
        Product5.add(Name5, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price5 = new JLabel();
        Price5.setForeground(new Color(-1));
        Price5.setText("商品价格");
        Product5.add(Price5, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label52 = new JLabel();
        label52.setBackground(new Color(-12828863));
        label52.setForeground(new Color(-1));
        label52.setText("购入数量：");
        Product5.add(label52, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField5 = new JTextField();
        Product5.add(textField5, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton5 = new JButton();
        AddButton5.setBackground(new Color(-14672351));
        AddButton5.setForeground(new Color(-1));
        AddButton5.setText("添加");
        Product5.add(AddButton5, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        Product5.add(spacer7, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Picture5 = new JLabel();
        Picture5.setText("商品图片");
        Product5.add(Picture5, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Stock5 = new JLabel();
        Stock5.setForeground(new Color(-1));
        Stock5.setText("商品库存");
        Product5.add(Stock5, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Product6 = new JPanel();
        Product6.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product6.setBackground(new Color(-8355712));
        panel1.add(Product6, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Name6 = new JLabel();
        Name6.setForeground(new Color(-1));
        Name6.setText("商品名称");
        Product6.add(Name6, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price6 = new JLabel();
        Price6.setForeground(new Color(-1));
        Price6.setText("商品价格");
        Product6.add(Price6, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label62 = new JLabel();
        label62.setBackground(new Color(-12828863));
        label62.setForeground(new Color(-1));
        label62.setText("购入数量：");
        Product6.add(label62, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField6 = new JTextField();
        Product6.add(textField6, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton6 = new JButton();
        AddButton6.setBackground(new Color(-14672351));
        AddButton6.setForeground(new Color(-1));
        AddButton6.setText("添加");
        Product6.add(AddButton6, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        Product6.add(spacer8, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Picture6 = new JLabel();
        Picture6.setText("商品图片");
        Product6.add(Picture6, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Stock6 = new JLabel();
        Stock6.setForeground(new Color(-1));
        Stock6.setText("商品库存");
        Product6.add(Stock6, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }


}
