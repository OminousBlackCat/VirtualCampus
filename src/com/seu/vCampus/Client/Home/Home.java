package com.seu.vCampus.Client.Home;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.Timer;

import com.seu.vCampus.Client.Bank.Bank;
import com.seu.vCampus.Client.BasicInformation.BasicInformationPanel;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.Client.Launcher.Launcher;
import com.seu.vCampus.Client.Shop.MangerShop;
import com.seu.vCampus.Client.Shop.Shop;
import com.seu.vCampus.Client.courseSelect.courseSelectForStu;
import com.seu.vCampus.Client.courseSelect.courseSelectForT;
import com.seu.vCampus.util.Person;


public class Home extends JFrame{

    private Common homeData;
    private static Point origin = new Point();
    private static ImageIcon TitleIcon = new ImageIcon("src/icon/head/logo.png");
    private static ImageIcon Home = new ImageIcon("src/icon/left/Home.png");
    private static ImageIcon Library = new ImageIcon("src/icon/left/library.png");
    private static ImageIcon Shop = new ImageIcon("src/icon/left/shop.png");
    private static ImageIcon Bank = new ImageIcon("src/icon/left/Bank.png");
    private static ImageIcon Edu = new ImageIcon("src/icon/left/school.png");
    private static ImageIcon UserImage = new ImageIcon("src/icon/left/user.png");

    private JTabbedPane tabbedPane;
    private Bank bankPanel;
    private BasicInformationPanel homePanel;
    private courseSelectForStu coursePanelS;
    private Shop shopPanel;
    private MangerShop mangerShopPanel;

    private int skinNumber = 2;


    private void LoadCommon(){
        homeData = Common.getInstance();
    }


    private void initialize() {
        InitGlobalFont(new Font("Microsoft Yahei", Font.BOLD, 17));
        LoadCommon();


        homePanel = new BasicInformationPanel("02");
        bankPanel = new Bank();
        shopPanel = new Shop();
        mangerShopPanel = new MangerShop();


        setBounds(200, 200, 1200, 864);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        /**
         * @此处代码块用来初始化Home的顶部与侧部装饰元素
         * */
        {
            JLabel Icon = new JLabel(TitleIcon);
            Icon.setBounds(10, 0, 350, 64);
            getContentPane().add(Icon);


            JLabel LogOut = new JLabel("注销");
            LogOut.setBounds(1000, 8, 50, 50);
            LogOut.setForeground(Color.WHITE);
            LogOut.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dispose();
                    homeData.reset();
                    new Launcher();
                }
            });
            LogOut.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    LogOut.setForeground(Color.gray);
                }
            });
            LogOut.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    LogOut.setForeground(Color.WHITE);
                }
            });
            getContentPane().add(LogOut);

            JLabel User = new JLabel("欢迎！" + homeData.getUser().getName());
            User.setForeground(Color.WHITE);
            User.setBounds(850, 8, 150, 50);
            getContentPane().add(User);


            JLabel Time = new JLabel();
            Date now = new Date();
            Time.setBounds(500, 8, 300, 50);
            getContentPane().add(Time);
            Time.setForeground(Color.WHITE);
            Timer timer;
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    Time.setText(new SimpleDateFormat("yyyy年MM月dd日 EEEE hh:mm:ss").format(new Date()));
                }
            });
            timer.start();

            JLabel Smallest = new JLabel("—");
            Smallest.setBounds(1120, 8, 25, 50);
            Smallest.setForeground(Color.WHITE);
            getContentPane().add(Smallest);
            Smallest.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setExtendedState(JFrame.ICONIFIED);
                }
            });
            Smallest.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    Smallest.setForeground(Color.gray);
                }
            });
            Smallest.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    Smallest.setForeground(Color.WHITE);
                }
            });


            JLabel exit = new JLabel("X");
            exit.setBounds(1160, 8, 25, 50);
            exit.setForeground(Color.WHITE);
            getContentPane().add(exit);
            exit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.exit(0);
                }
            });
            exit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    exit.setForeground(Color.gray);
                }
            });
            exit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    exit.setForeground(Color.WHITE);
                }
            });


            tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
            tabbedPane.setBackground(Color.BLACK);
            tabbedPane.setBounds(0, 64, 1200, 800);
            getContentPane().add(tabbedPane);


            tabbedPane.addTab("主页", Home, homePanel, null);
            tabbedPane.setForeground(Color.WHITE);

            JLabel skin = new JLabel("换肤");
            skin.setBounds(400, 8, 50, 50);
            skin.setForeground(Color.WHITE);
            getContentPane().add(skin);
            skin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    skin.setForeground(Color.gray);
                }
            });
            skin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    skin.setForeground(Color.WHITE);
                }
            });
            skin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    skinNumber++;
                    switch (skinNumber) {
                        case 1:
                            getContentPane().setBackground(new Color(63, 87, 123));
                            homePanel = new BasicInformationPanel("01");
                            tabbedPane.setComponentAt(0, homePanel);
                            break;
                        case 2:
                            getContentPane().setBackground(Color.BLACK);
                            homePanel = new BasicInformationPanel("02");
                            tabbedPane.setComponentAt(0, homePanel);
                            break;
                        case 3:
                            getContentPane().setBackground(new Color(85, 20, 0));
                            homePanel = new BasicInformationPanel("03");
                            tabbedPane.setComponentAt(0, homePanel);
                            break;
                        case 4:
                            getContentPane().setBackground(new Color(0, 70, 40));
                            homePanel = new BasicInformationPanel("04");
                            tabbedPane.setComponentAt(0, homePanel);
                            skinNumber = 0;
                            break;
                    }
                }
            });
        }


        System.out.println(homeData.getUser().getAuthorityLevel());
        switch (homeData.getUser().getAuthorityLevel()){
            case GROUP_USER_MANAGER:
            {
                JPanel UserManager = new JPanel();
                tabbedPane.addTab("用户管理",UserImage,UserManager,null);
                break;
            }
            case GROUP_STUDENT:{
                JPanel panel_1 = new JPanel();
                tabbedPane.addTab("图书", Library, panel_1, null);

                coursePanelS = new courseSelectForStu();
                tabbedPane.addTab("选课", Edu, coursePanelS, null);

                JPanel panel_3 = new JPanel();
                tabbedPane.addTab("商店",Shop, shopPanel.getPanel(), null);


                JPanel panel_4 = new JPanel();
                tabbedPane.addTab("银行", Bank, bankPanel.getPanel(), null);
                break;
            }
            case GROUP_TEACHER:{
                JPanel panel_1 = new JPanel();
                tabbedPane.addTab("图书馆", Library, panel_1, null);

                courseSelectForT panel_2 = new courseSelectForT();
                tabbedPane.addTab("教务", Edu, panel_2, null);

                JPanel panel_3 = new JPanel();
                tabbedPane.addTab("商店",Shop, panel_3, null);


                JPanel panel_4 = new JPanel();
                tabbedPane.addTab("银行", Bank, panel_4, null);
                break;
            }
            case GROUP_LIBRARY_MANAGER:{
                JPanel LibraryManager = new JPanel();
                tabbedPane.addTab("图书管理",Library,LibraryManager,null);
                break;
            }
            case GROUP_SHOP_MANAGER:{
                JPanel ShopManager = new JPanel();
                tabbedPane.addTab("商品管理",Shop,ShopManager,null);
                break;
            }

        }


        /***
         * @此处的代码块用来为窗口提供鼠标拖动功能
         */

        {
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
        }

        setVisible(true);

    }

    public Home() {

        this.setUndecorated(true);
        initialize();
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Common temp = Common.getInstance();
                    Person test = new Person();
                    test.setAuthorityLevel(Person.USER_GROUP.GROUP_STUDENT);
                    temp.setUser(test);
                    new Home();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
