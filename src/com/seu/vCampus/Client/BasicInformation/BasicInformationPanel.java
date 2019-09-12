package com.seu.vCampus.Client.BasicInformation;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.News;
import com.seu.vCampus.util.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class BasicInformationPanel extends JPanel {

    private Common userData;
    private JLabel Avatar;
    private ImageIcon AvatarImage;
    private JPanel AvatarPanel;
    private JLabel CurrentAvatar;
    private JButton ChangeAvatar;
    private JButton ConfirmAvatar;
    private JButton CancelAvatar;
    private JLabel Sex;
    private JLabel StudentNumber;
    private JLabel ECardNumber;
    private JLabel UserName;
    private JLabel Group;
    private JLabel LendBookNumber;
    private JLabel ECardBalance;
    private JLabel Major;
    private HTML newsA;
    private HTML newsB;
    private HTML newsC;
    private HTML newsD;
    private static ImageIcon  BackGroundImage;
    private JLabel BackGround = new JLabel();
    private static final int AvatarNumber = 31;
    private int current;
    private int next;



    private void initialize(String index){
        userData = Common.getInstance();

        setBackground(new Color(63,87,123));
        setForeground(Color.WHITE);
        setLayout(null);


        /**
         * @修改头像相关代码
         * */
        {
            AvatarPanel = new JPanel();
            AvatarPanel.setBounds(690, 80, 180, 180);
            AvatarPanel.setBorder(BorderFactory.createLoweredBevelBorder());
            AvatarPanel.setBackground(Color.WHITE);
            Avatar = new JLabel();
            Avatar.setBackground(Color.WHITE);
            System.out.println(Common.picturePath +"/Head_Big/" + userData.getUser().getAvatarID() + ".png");
            AvatarImage = new ImageIcon(Common.picturePath +"/Head_Big/" + userData.getUser().getAvatarID() + ".png");
            Avatar.setBounds(0, 0, 180, 180);
            Avatar.setIcon(AvatarImage);
            AvatarPanel.add(Avatar);
            add(AvatarPanel);

            CurrentAvatar = new JLabel("当前头像");
            CurrentAvatar.setForeground(Color.WHITE);
            CurrentAvatar.setBounds(750, 270, 100, 20);
            add(CurrentAvatar);
            ChangeAvatar = new JButton("修改头像");
            ChangeAvatar.setBackground(Color.BLACK);
            ChangeAvatar.setBounds(728, 300, 110, 40);
            add(ChangeAvatar);
            ConfirmAvatar = new JButton("保存");
            ConfirmAvatar.setBackground(Color.pink);
            ConfirmAvatar.setBounds(660, 300, 70, 40);
            CancelAvatar = new JButton("取消");
            CancelAvatar.setBackground(Color.ORANGE);
            CancelAvatar.setBounds(835, 300, 70, 40);
            add(CancelAvatar);
            add(ConfirmAvatar);
            CancelAvatar.setVisible(false);
            ConfirmAvatar.setVisible(false);

            current = Integer.parseInt(userData.getUser().getAvatarID());


            ChangeAvatar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (current < AvatarNumber) {
                        current++;
                        next = current;
                    } else {
                        current = 1;
                        next = 1;
                    }
                    AvatarImage = new ImageIcon(Common.picturePath +"/Head_Big/" + Integer.toString(next) + ".png");
                    Avatar.setIcon(AvatarImage);
                    CancelAvatar.setVisible(true);
                    ConfirmAvatar.setVisible(true);
                }
            });

            ConfirmAvatar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userData.getUser().setAvatarID(Integer.toString(next));
                    System.out.println(userData.getUser().getAvatarID());
                    userData.getUser().setType(Message.MESSAGE_TYPE.TYPE_CHANGE_AVATAR);
                    userData.getIO().SendMessages(userData.getUser());
                    userData.setUser((Person) userData.getIO().ReceiveMessage());
                    System.out.println(userData.getUser().getType());
                    if (userData.getUser().getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS) {
                        CancelAvatar.setVisible(false);
                        ConfirmAvatar.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "网络连接异常",
                                "错误", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });

            CancelAvatar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AvatarImage = new ImageIcon(Common.picturePath +"/Head_Big/" + userData.getUser().getAvatarID() + ".png");
                    Avatar.setIcon(AvatarImage);
                    CancelAvatar.setVisible(false);
                    ConfirmAvatar.setVisible(false);
                    current = Integer.parseInt(userData.getUser().getAvatarID());
                }
            });
        }

        /**
         * @用户基本信息显示相关代码
         * */
        flush();

        {
            int last = userData.getNewsList().getNews().size();
            News temp = userData.getNewsList().getNews().get(last-1);
            newsA = new HTML(temp.getNewsTitle(),temp.getURL(),temp.getNewsDate());
            temp = userData.getNewsList().getNews().get(last-2);
            newsB = new HTML(temp.getNewsTitle(),temp.getURL(),temp.getNewsDate());
            temp = userData.getNewsList().getNews().get(last-3);
            newsC = new HTML(temp.getNewsTitle(),temp.getURL(),temp.getNewsDate());
            temp = userData.getNewsList().getNews().get(last-4);
            newsD = new HTML(temp.getNewsTitle(),temp.getURL(),temp.getNewsDate());


            JLabel newsT = new JLabel("近期要闻：");
            newsT.setBounds(100,320,150,30);
            newsT.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
            add(newsT);
            newsA.setBounds(100,350,500,30);
            newsB.setBounds(100,380,500,30);
            newsC.setBounds(100,410,500,30);
            newsD.setBounds(100,440,500,30);
            add(newsA);
            add(newsB);
            add(newsC);
            add(newsD);

        }


        BackGround.setBounds(0,0,1040,800);
        ChangeBackGround(index);
        BackGround.setIcon(BackGroundImage);

        add(BackGround);
    }
    public BasicInformationPanel(String index){
        initialize(index);
    }
    public void ChangeBackGround(String index){
        String filepath = Common.picturePath +"/BackGroundImage/"+index+".jpg";
        System.out.println(filepath);
        BackGroundImage = new ImageIcon(filepath);
    }
    public void flush(){
        userData = Common.getInstance();

        Sex = new JLabel();
        UserName = new JLabel();
        StudentNumber = new JLabel();
        Group = new JLabel();
        ECardNumber = new JLabel();
        LendBookNumber = new JLabel();
        ECardBalance = new JLabel();
        Major = new JLabel();
        JLabel UserNameT = new JLabel("用户名：");
        JLabel StudentNumberT = new JLabel("学号：");
        JLabel SexT = new JLabel("性   别：");
        JLabel GroupT = new JLabel("用户组：");
        JLabel ECardNumberT = new JLabel("一卡通号：");
        JLabel LendBookNumberT = new JLabel("已借阅图书数量：");
        JLabel ECardBalanceT = new JLabel("一卡通余额：");
        JLabel MajorT = new JLabel("专业：");
        Sex.setText(userData.getUser().getSex());
        UserName.setText(userData.getUser().getName());
        StudentNumber.setText(userData.getUser().getStudentNumber());
        Group.setText(userData.getUser().getAuthorityLevel().toString());
        ECardNumber.setText(userData.getUser().getECardNumber());
        LendBookNumber.setText(Integer.toString(userData.getUser().getLendBooksNumber())+" 本");
        ECardBalance.setText(Double.toString(userData.getUser().getECardBalance())+" 元");
        System.out.println(userData.getUser().getECardBalance());
        Major.setText(userData.getUser().getMajor());

        UserNameT.setBounds(100,100,80,30);
        UserName.setBounds(170,100,150,30);
        UserNameT.setForeground(Color.WHITE);
        UserName.setForeground(Color.cyan);
        UserNameT.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        UserName.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        add(UserName);
        add(UserNameT);

        ECardNumberT.setBounds(290,100,100,30);
        ECardNumber.setBounds(380,100,100,30);
        ECardNumberT.setForeground(Color.WHITE);
        ECardNumber.setForeground(Color.cyan);
        ECardNumber.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        ECardNumberT.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        add(ECardNumber);
        add(ECardNumberT);

        StudentNumberT.setBounds(500,100,80,30);
        StudentNumber.setBounds(550,100,80,30);
        StudentNumberT.setForeground(Color.WHITE);
        StudentNumber.setForeground(Color.cyan);
        StudentNumberT.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        StudentNumber.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        add(StudentNumberT);
        add(StudentNumber);

        GroupT.setBounds(100,140,100,30);
        Group.setBounds(170,140,100,30);
        GroupT.setForeground(Color.WHITE);
        Group.setForeground(Color.YELLOW);
        GroupT.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        Group.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        add(Group);
        add(GroupT);

        SexT.setBounds(290,140,100,30);
        Sex.setBounds(360,140,100,30);
        SexT.setForeground(Color.WHITE);
        Sex.setForeground(Color.YELLOW);
        SexT.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        Sex.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        add(Sex);
        add(SexT);

        MajorT.setBounds(500,140,80,30);
        Major.setBounds(550,140,150,30);
        MajorT.setForeground(Color.WHITE);
        Major.setForeground(Color.YELLOW);
        MajorT.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        Major.setFont(new Font("Microsoft Yahei", Font.BOLD,20));
        add(MajorT);
        add(Major);

        ECardBalanceT.setBounds(100,180,150,30);
        ECardBalance.setBounds(250,180,200,30);
        ECardBalanceT.setForeground(Color.WHITE);
        ECardBalance.setForeground(Color.RED);
        ECardBalanceT.setFont(new Font("Microsoft Yahei", Font.BOLD,25));
        ECardBalance.setFont(new Font("Microsoft Yahei", Font.BOLD,25));
        add(ECardBalanceT);
        add(ECardBalance);

        LendBookNumberT.setBounds(100,220,300,30);
        LendBookNumber.setBounds(300,220,200,30);
        LendBookNumberT.setForeground(Color.WHITE);
        LendBookNumber.setForeground(Color.RED);
        LendBookNumberT.setFont(new Font("Microsoft Yahei", Font.BOLD,25));
        LendBookNumber.setFont(new Font("Microsoft Yahei", Font.BOLD,25));
        add(LendBookNumberT);
        add(LendBookNumber);


    }



}
