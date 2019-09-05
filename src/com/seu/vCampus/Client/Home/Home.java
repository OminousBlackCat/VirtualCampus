package com.seu.vCampus.Client.Home;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home {

    private JFrame Homepage;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home window = new Home();
                    window.Homepage.setVisible(true);
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
        Homepage = new JFrame();
        Homepage.setBounds(100, 100, 476, 353);
        Homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Homepage.getContentPane().setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setBounds(0, 13, 444, 280);
        Homepage.getContentPane().add(tabbedPane);

        JPanel panel = new JPanel();
        ImageIcon image=new ImageIcon("C:\\Users\\Fly\\Desktop\\111.png");
        image.setImage(image.getImage().getScaledInstance(80, 80, 100));
        tabbedPane.addTab("学生信息", null, panel, null);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("图书馆", null, panel_1, null);

        courseSelectForStu panel_2 = new courseSelectForStu();
        tabbedPane.addTab("", null, panel_2, null);

        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                p(e);
            }
            private  void p(MouseEvent e){
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    Rectangle rect = tabbedPane.getBoundsAt(i);
                    if (rect.contains(e.getX(), e.getY())) {
                        if(i==2)
                            panel_2.refresh();
                    }
                }
            }
        }

        );

        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("商店",null, panel_3, null);


        JPanel panel_4 = new JPanel();
        tabbedPane.addTab("银行", null, panel_4, null);
    }
}
