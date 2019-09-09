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
            }
        });
        AddButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Goods addThisGoods = new Goods();
                addThisGoods.setGoodsNumber(GoodsB.getGoodsNumber());
                addThisGoods.setGoodsName(GoodsB.getGoodsName());
                addThisGoods.setGoodsPrice(GoodsB.getGoodsPrice());
                addThisGoods.setGoodsStock(Short.parseShort(textField2.getText()));
                GoodsB.setGoodsStock((short) (GoodsB.getGoodsStock() - Short.parseShort(textField2.getText())));
                shopData.getShoppingList().add(addThisGoods);
                JOptionPane.showMessageDialog(null, "添加" + addThisGoods.getGoodsName() + "成功亲~请前往购物车查看~",
                        "成功！", JOptionPane.INFORMATION_MESSAGE);
                textField1.setText("");
            }
        });

        AddButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            GoodsB = thisClassGoodsList.get(index + 1);
            Name2.setText(GoodsB.getGoodsName());
            Price2.setText(Double.toString(GoodsB.getGoodsPrice()));
            IconB = new ImageIcon("src/icon/ProductPicture/" + GoodsB.getPictureNumber() + ".png");
            Picture2.setIcon(IconB);
            Picture2.setText("");
        } else {
            Name2.setVisible(false);
            Price2.setVisible(false);
            AddButton2.setVisible(false);
            textField2.setVisible(false);
            Picture2.setVisible(false);
            label21.setVisible(false);
        }
    }

    public void LoadGoodsC(boolean isLoad) {
        if (isLoad) {
            Name3.setVisible(true);
            Price3.setVisible(true);
            AddButton3.setVisible(true);
            textField3.setVisible(true);
            Picture3.setVisible(true);
            label31.setVisible(true);
            GoodsC = thisClassGoodsList.get(index + 2);
            Name3.setText(GoodsC.getGoodsName());
            Price3.setText(Double.toString(GoodsC.getGoodsPrice()));
            IconC = new ImageIcon("src/icon/ProductPicture/" + GoodsC.getPictureNumber() + ".png");
            Picture3.setIcon(IconC);
            Picture3.setText("");
        } else {
            Name3.setVisible(false);
            Price3.setVisible(false);
            AddButton3.setVisible(false);
            textField3.setVisible(false);
            Picture3.setVisible(false);
            label31.setVisible(false);
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
        panel1.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-8355712));
        Product1 = new JPanel();
        Product1.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product1.setBackground(new Color(-8355712));
        panel1.add(Product1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Name1 = new JLabel();
        Name1.setForeground(new Color(-1));
        Name1.setText("商品名称");
        Product1.add(Name1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Price1 = new JLabel();
        Price1.setForeground(new Color(-1));
        Price1.setText("商品价格");
        Product1.add(Price1, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label12 = new JLabel();
        label12.setBackground(new Color(-12828863));
        label12.setForeground(new Color(-1));
        label12.setText("购入数量：");
        Product1.add(label12, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        Product1.add(textField1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton1 = new JButton();
        AddButton1.setBackground(new Color(-14672351));
        AddButton1.setForeground(new Color(-1));
        AddButton1.setText("添加");
        Product1.add(AddButton1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        Product1.add(spacer1, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Picture1 = new JLabel();
        Picture1.setText("商品图片");
        Product1.add(Picture1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label11 = new JLabel();
        label11.setForeground(new Color(-1));
        label11.setText("元");
        Product1.add(label11, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Product3 = new JPanel();
        Product3.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        Product3.setBackground(new Color(-8355712));
        panel1.add(Product3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
        Product3.add(Price3, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label33 = new JLabel();
        label33.setForeground(new Color(-1));
        label33.setText("购入数量：");
        Product3.add(label33, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        Product3.add(textField3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton3 = new JButton();
        AddButton3.setBackground(new Color(-14672351));
        AddButton3.setForeground(new Color(-1));
        AddButton3.setText("添加");
        Product3.add(AddButton3, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        Product3.add(spacer2, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label31 = new JLabel();
        label31.setForeground(new Color(-1));
        label31.setText("元");
        Product3.add(label31, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Product2 = new JPanel();
        Product2.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
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
        Product2.add(Price2, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label22 = new JLabel();
        label22.setForeground(new Color(-1));
        label22.setText("购入数量：");
        Product2.add(label22, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        Product2.add(textField2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddButton2 = new JButton();
        AddButton2.setBackground(new Color(-14672351));
        AddButton2.setForeground(new Color(-1));
        AddButton2.setText("添加");
        Product2.add(AddButton2, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        Product2.add(spacer3, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        label21 = new JLabel();
        label21.setForeground(new Color(-1));
        label21.setText("元");
        Product2.add(label21, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ChangePage = new JPanel();
        ChangePage.setLayout(new GridLayoutManager(3, 6, new Insets(0, 0, 0, 0), -1, -1));
        ChangePage.setBackground(new Color(-8355712));
        panel1.add(ChangePage, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PageUp = new JButton();
        PageUp.setBackground(new Color(-14672351));
        PageUp.setForeground(new Color(-1));
        PageUp.setText("上一页");
        ChangePage.add(PageUp, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label0 = new JLabel();
        label0.setForeground(new Color(-1));
        label0.setText("当前页数：");
        ChangePage.add(label0, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Page = new JLabel();
        Page.setForeground(new Color(-1));
        Page.setText("Label");
        ChangePage.add(Page, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PageDown = new JButton();
        PageDown.setBackground(new Color(-14672351));
        PageDown.setForeground(new Color(-1));
        PageDown.setText("下一页");
        ChangePage.add(PageDown, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        ChangePage.add(spacer4, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        ChangePage.add(spacer5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        ChangePage.add(spacer6, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        ChangePage.add(spacer7, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        ChangePage.add(spacer8, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        ChangePage.add(spacer9, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        ChangePage.add(spacer10, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        ChangePage.add(spacer11, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        ChangePage.add(spacer12, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        ChangePage.add(spacer13, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
