package com.seu.vCampus.Client.Shop;

import com.intellij.uiDesigner.core.GridLayoutManager;
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HaveBackgroundPicturePanel extends JPanel {

    private Icon Image;

    public HaveBackgroundPicturePanel(){
    }


    @Override
    protected  void paintComponent(Graphics g){
        if (null != Image) {
            processBackground(g);
        }
        System.out.println("f:paintComponent(Graphics g)");
    }

    private void setBackgroundPicture(Icon icon) {
        this.Image = icon;
        this.repaint();
    }

    private void processBackground(Graphics g) {
        ImageIcon icon = (ImageIcon) Image;
        Image image = icon.getImage();
        int cw = getWidth();
        int ch = getHeight();
        int iw = image.getWidth(this);
        int ih = image.getHeight(this);
        int x = 0;
        int y = 0;
        while (y <= ch) {
            g.drawImage(image, x, y, this);
            x += iw;
            if (x >= cw) {
                x = 0;
                y += ih;
            }
        }
    }
}
