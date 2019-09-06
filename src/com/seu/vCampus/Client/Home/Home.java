package com.seu.vCampus.Client.Home;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
<<<<<<< HEAD
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home {
=======
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.Timer;

import com.seu.vCampus.Client.BasicInformation.BasicInformationPanel;
import com.seu.vCampus.Client.Common;
import com.seu.vCampus.Client.Home.courseSelect;
import com.seu.vCampus.util.Person;


public class Home extends JFrame{
>>>>>>> fc662e3ac7dfaeebd46ebda78842b1bf77426d92

    private Common homeData;
    private static Point origin = new Point();
    private static ImageIcon TitleIcon = new ImageIcon("src/icon/cheen.png");
    private static ImageIcon Home = new ImageIcon("src/icon/left/Home.png");
    private static ImageIcon Library = new ImageIcon("src/icon/left/library.png");
    private static ImageIcon Shop = new ImageIcon("src/icon/left/shop.png");
    private static ImageIcon Bank = new ImageIcon("src/icon/left/Bank.png");
    private static ImageIcon Edu = new ImageIcon("src/icon/left/school.png");
    private static ImageIcon UserImage = new ImageIcon("src/icon/left/user.png");
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
        InitGlobalFont(new Font("Microsoft Yahei", Font.BOLD, 17));
        homeData = Common.getInstance();



        setBounds(200, 200, 1200, 864);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);



        Title = new JLabel("虚拟校园");
        Title.setBounds(60,25,80,20);
        Title.setForeground(Color.WHITE);
        getContentPane().add(Title);
        JLabel Icon = new JLabel(TitleIcon);


        Icon.setBounds(10,10,50,50);
        getContentPane().add(Icon);


        JLabel LogOut  = new JLabel("注销");
        LogOut.setBounds(1000,8,50,50);
        LogOut.setForeground(Color.WHITE);
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

        JLabel User = new JLabel("欢迎！"+homeData.getBasicInformation().getName());
        User.setForeground(Color.WHITE);
        User.setBounds(890,8,100,50);
        getContentPane().add(User);


        JLabel Time = new JLabel();
        Date now = new Date();
        Time.setBounds(580,8,300,50);
        getContentPane().add(Time);
        Time.setForeground(Color.WHITE);
        Timer timer;
        timer = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Time.setText(new SimpleDateFormat("yyyy年MM月dd日 EEEE hh:mm:ss").format(new Date()));
            }
        });
        timer.start();

        JLabel Smallest = new JLabel("—");
        Smallest.setBounds(1120,8,25,50);
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
        exit.setBounds(1160,8,25,50);
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




        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setBackground(Color.BLACK);
        tabbedPane.setBounds(0, 64, 1200, 800);
        getContentPane().add(tabbedPane);


        BasicInformationPanel basicInformation = new BasicInformationPanel();  //*****
        tabbedPane.addTab("主页",Home,basicInformation,null);
        tabbedPane.setForeground(Color.WHITE);

        System.out.println(homeData.getBasicInformation().getAuthorityLevel());

        switch (homeData.getBasicInformation().getAuthorityLevel()){
            case GROUP_USER_MANAGER:
            {
                JPanel UserManager = new JPanel();
                tabbedPane.addTab("用户管理",UserImage,UserManager,null);
                break;
            }
            case GROUP_STUDENT:{
                JPanel panel_1 = new JPanel();
                tabbedPane.addTab("图书", Library, panel_1, null);

                courseSelect panel_2 = new courseSelect();
                tabbedPane.addTab("选课", Edu, panel_2, null);

<<<<<<< HEAD
        JPanel panel = new JPanel();
        ImageIcon image=new ImageIcon("C:\\Users\\Fly\\Desktop\\111.png");
        image.setImage(image.getImage().getScaledInstance(80, 80, 100));
        tabbedPane.addTab("学生信息", null, panel, null);
=======
>>>>>>> fc662e3ac7dfaeebd46ebda78842b1bf77426d92

                JPanel panel_3 = new JPanel();
                tabbedPane.addTab("商店",Shop, panel_3, null);

<<<<<<< HEAD
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
=======

                JPanel panel_4 = new JPanel();
                tabbedPane.addTab("银行", Bank, panel_4, null);
                break;
            }
            case GROUP_TEACHER:{
                JPanel panel_1 = new JPanel();
                tabbedPane.addTab("图书馆", Library, panel_1, null);

                courseSelect panel_2 = new courseSelect();
                tabbedPane.addTab("教务", Edu, panel_2, null);
>>>>>>> fc662e3ac7dfaeebd46ebda78842b1bf77426d92


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
