package com.seu.vCampus.Client.Home;

import javax.swing.*;

public class UItest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("UItest");
        frame.setContentPane(new UItest().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTabbedPane tabbedPane2;
    private JLabel jl1;
    private JPanel selectedCourse;
    private JPanel first;
    private JPanel second;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
}
