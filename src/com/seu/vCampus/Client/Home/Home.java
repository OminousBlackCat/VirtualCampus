package com.seu.vCampus.Client.Home;
import javax.swing.*;
import java.awt.*;
public class Home {
    private JFrame 选课;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home window = new Home();
                    window.选课.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Home() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        选课 = new JFrame();
        选课.setBounds(100, 100, 476, 353);
        选课.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        选课.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setBounds(0, 13, 444, 280);
        选课.getContentPane().add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("学生信息", null, panel, null);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("图书馆", null, panel_1, null);
        panel_1.setLayout(null);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("选课", null, panel_2, null);
        panel_2.setLayout(null);

        JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane_1.setBounds(0, 0, 363, 275);
        panel_2.add(tabbedPane_1);

        JPanel panel_5 = new JPanel();

        tabbedPane_1.addTab("第一学期", null, panel_5, null);
        panel_5.setLayout(null);

        JPanel panel_7 = new JPanel();
        panel_7.setBounds(0, 0, 63, 243);
        panel_5.add(panel_7);

        JLabel label = new JLabel("\u5DF2\u9009\u8BFE\u7A0B");
        panel_7.add(label);

        table = new JTable(5,6);
        table.setBounds(63, 0, 295, 243);
        table.setRowHeight(48);
        panel_5.add(table);

        JPanel panel_6 = new JPanel();
        tabbedPane_1.addTab("第二学期", null, panel_6, null);

        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("商店", null, panel_3, null);

        JPanel panel_4 = new JPanel();
        tabbedPane.addTab("银行", null, panel_4, null);
    }
}

