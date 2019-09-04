package com.seu.vCampus.Client.Home;

import javax.swing.*;

import java.awt.*;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.Client.Home.courseSelect;
public class Home extends JFrame{

    private JFrame Homepage;
    private Common homeData;

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
        Homepage.setBounds(100, 100, 1000, 700);
        Homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Homepage.getContentPane().setLayout(new BorderLayout());
        homeData = Common.getInstance();

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setBounds(0, 13, 444, 280);
        Homepage.getContentPane().add(tabbedPane);


        JPanel basicInformation = new JPanel();  //*****
        tabbedPane.addTab("个人信息",null,basicInformation,null);

        switch (homeData.getBasicInformation().getAuthorityLevel()){
            case GROUP_USER_MANAGER:
            {
                JPanel UserManager = new JPanel();
                tabbedPane.addTab("管理用户",null,UserManager,null);
                break;
            }
            case GROUP_STUDENT:{
                JPanel panel_1 = new JPanel();
                tabbedPane.addTab("图书馆", null, panel_1, null);

                courseSelect panel_2 = new courseSelect();
                tabbedPane.addTab("选课", null, panel_2, null);


                JPanel panel_3 = new JPanel();
                tabbedPane.addTab("商店",null, panel_3, null);


                JPanel panel_4 = new JPanel();
                tabbedPane.addTab("银行", null, panel_4, null);
                break;
            }
            case GROUP_TEACHER:{
                JPanel panel_1 = new JPanel();
                tabbedPane.addTab("图书馆", null, panel_1, null);

                courseSelect panel_2 = new courseSelect();
                tabbedPane.addTab("教务", null, panel_2, null);


                JPanel panel_3 = new JPanel();
                tabbedPane.addTab("商店",null, panel_3, null);


                JPanel panel_4 = new JPanel();
                tabbedPane.addTab("银行", null, panel_4, null);
                break;
            }
            case GROUP_LIBRARY_MANAGER:{
                JPanel LibraryManager = new JPanel();
                tabbedPane.addTab("图书管理",null,LibraryManager,null);
                break;
            }
            case GROUP_SHOP_MANAGER:{
                JPanel ShopManager = new JPanel();
                tabbedPane.addTab("图书管理",null,ShopManager,null);
                break;
            }

        }
    }
}
