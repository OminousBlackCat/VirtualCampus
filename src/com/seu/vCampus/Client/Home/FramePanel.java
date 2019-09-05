package com.seu.vCampus.Client.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class FramePanel extends JPanel {


    private static Point origin = new Point();

    public FramePanel() {
        setLayout(null);
        setBounds(500, 500, 500, 500);
        JButton button = new JButton("xxx");
        button.setBounds(500, 500, 20, 30);
        add(button);
        setBackground(Color.gray);




    }

}