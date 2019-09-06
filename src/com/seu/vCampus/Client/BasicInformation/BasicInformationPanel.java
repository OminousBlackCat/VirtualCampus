package com.seu.vCampus.Client.BasicInformation;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            AvatarPanel.setBounds(630, 80, 180, 180);
            AvatarPanel.setBorder(BorderFactory.createLoweredBevelBorder());
            AvatarPanel.setBackground(Color.WHITE);
            Avatar = new JLabel();
            Avatar.setBackground(Color.WHITE);
            System.out.println("src/Head_Big/" + userData.getUser().getAvatarID() + ".png");
            AvatarImage = new ImageIcon("src/Head_Big/" + userData.getUser().getAvatarID() + ".png");
            Avatar.setBounds(0, 0, 180, 180);
            Avatar.setIcon(AvatarImage);
            AvatarPanel.add(Avatar);
            add(AvatarPanel);

            CurrentAvatar = new JLabel("当前头像");
            CurrentAvatar.setForeground(Color.WHITE);
            CurrentAvatar.setBounds(690, 270, 100, 20);
            add(CurrentAvatar);
            ChangeAvatar = new JButton("修改头像");
            ChangeAvatar.setBackground(Color.BLACK);
            ChangeAvatar.setForeground(Color.WHITE);
            ChangeAvatar.setBounds(668, 300, 110, 40);
            add(ChangeAvatar);
            ConfirmAvatar = new JButton("保存");
            ConfirmAvatar.setForeground(Color.WHITE);
            ConfirmAvatar.setBackground(Color.pink);
            ConfirmAvatar.setBounds(600, 300, 70, 40);
            CancelAvatar = new JButton("取消");
            CancelAvatar.setForeground(Color.WHITE);
            CancelAvatar.setBackground(Color.ORANGE);
            CancelAvatar.setBounds(770, 300, 70, 40);
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
                    AvatarImage = new ImageIcon("src/Head_Big/" + Integer.toString(next) + ".png");
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
                    userData.getIo().SendMessages(userData.getUser());
                    userData.setUser((Person) userData.getIo().ReceiveMessage());
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
                    AvatarImage = new ImageIcon("src/Head_Big/" + userData.getUser().getAvatarID() + ".png");
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
        {
            Sex = new JLabel();
            UserName = new JLabel();
            StudentNumber = new JLabel();
            Group = new JLabel();
            ECardNumber = new JLabel();
            LendBookNumber = new JLabel();
            ECardBalance = new JLabel();
            JLabel UserNameT = new JLabel("用户名：");
            JLabel StudentNumberT = new JLabel("学号：");
            JLabel SexT = new JLabel("性别：");
            JLabel GroupT = new JLabel("用户组：");
            JLabel ECardNumberT = new JLabel("一卡通号：");
            JLabel LendBookNumberT = new JLabel("当前已借阅    本图书");
            JLabel ECardBalanceT = new JLabel("一卡通余额：");
            System.out.println(userData.getUser().getAuthorityLevel());
            System.out.println(userData.getUser().getSex());
            Sex.setText(userData.getUser().getSex());
            UserName.setText(userData.getUser().getName());
            StudentNumber.setText(userData.getUser().getStudentNumber());
            Group.setText(userData.getUser().getAuthorityLevel().toString());
            ECardNumber.setText(userData.getUser().getECardNumber());
            LendBookNumber.setText(Integer.toString(userData.getUser().getLendBooksNumber()));
            ECardBalance.setText(Double.toString(userData.getUser().getECardBalance()));

            UserNameT.setBounds(130,80,50,30);
            UserName.setBounds(180,80,50,30);
            add(UserName);
            add(UserNameT);




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
        String filepath = "src/BackGroundImage/"+index+".jpg";
        System.out.println(filepath);
        BackGroundImage = new ImageIcon(filepath);
    }


}
