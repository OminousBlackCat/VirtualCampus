package com.seu.vCampus.Client.Home;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Enumeration;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.Client.Home.courseSelect;
import com.seu.vCampus.util.Person;

public class Home extends JFrame{

    private Common homeData;
    private static Point origin = new Point();
    private static ImageIcon TitleIcon = new ImageIcon("src/icon/cheen.png") ;
    private JLabel Title;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Common temp = Common.getInstance();
                    temp.setBasicInformation(new Person());
                    new Home();
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
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        InitGlobalFont(new Font("alias", Font.PLAIN, 18));



        setBounds(200, 200, 1200, 864);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.cyan);



        Title = new JLabel("虚拟校园");
        Title.setBounds(60,30,80,20);


        getContentPane().add(Title);
        JLabel Icon = new JLabel(TitleIcon);
        Icon.setBounds(10,10,50,50);
        getContentPane().add(Icon);
        homeData = Common.getInstance();


        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setBounds(0, 64, 1200, 800);
        getContentPane().add(tabbedPane);


        JPanel basicInformation = new JPanel();  //*****
        tabbedPane.addTab("个人信息",null,basicInformation,null);

        System.out.println(homeData.getBasicInformation().getAuthorityLevel());

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



        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                origin.x = e.getX();
                origin.y = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
            }
        });


        setVisible(true);


    }

    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

}
