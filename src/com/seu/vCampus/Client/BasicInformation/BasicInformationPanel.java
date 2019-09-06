package com.seu.vCampus.Client.BasicInformation;

import com.seu.vCampus.Client.Common;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.util.Person;

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
    private JLabel Welcome;
    private JLabel BasicTitle;
    private JLabel Name;
    private JLabel UserGroup;
    private JLabel LendBook;
    private JLabel ECardBlance;
    private static ImageIcon BackGroundImage = new ImageIcon("src/BackGroundImage/backGround.jpg");
    private JLabel BackGround;
    private static final int AvatarNumber = 31;
    private int current;
    private int next;


    private void initialize(){
        userData = Common.getInstance();

        setBackground(new Color(63,87,123));
        setForeground(Color.WHITE);
        setLayout(null);



        AvatarPanel = new JPanel();
        AvatarPanel.setBounds(130,80,180,180);
        AvatarPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        AvatarPanel.setBackground(Color.WHITE);
        Avatar = new JLabel();
        Avatar.setBackground(Color.WHITE);
        System.out.println("src/Head_Big/"+userData.getUser().getAvatarID()+".png");
        AvatarImage = new ImageIcon("src/Head_Big/"+userData.getUser().getAvatarID()+".png");
        Avatar.setBounds(0,0,180,180);
        Avatar.setIcon(AvatarImage);
        AvatarPanel.add(Avatar);
        add(AvatarPanel);

        CurrentAvatar = new JLabel("当前头像");
        CurrentAvatar.setForeground(Color.WHITE);
        CurrentAvatar.setBounds(190,270,100,20);
        add(CurrentAvatar);
        ChangeAvatar = new JButton("修改头像");
        ChangeAvatar.setBackground(Color.BLACK);
        ChangeAvatar.setForeground(Color.WHITE);
        ChangeAvatar.setBounds(168,300,110,40);
        add(ChangeAvatar);
        ConfirmAvatar = new JButton("保存");
        ConfirmAvatar.setForeground(Color.WHITE);
        ConfirmAvatar.setBackground(Color.pink);
        ConfirmAvatar.setBounds(100,300,70,40);
        CancelAvatar = new JButton("取消");
        CancelAvatar.setForeground(Color.WHITE);
        CancelAvatar.setBackground(Color.ORANGE);
        CancelAvatar.setBounds(270,300,70,40);
        add(CancelAvatar);
        add(ConfirmAvatar);
        CancelAvatar.setVisible(false);
        ConfirmAvatar.setVisible(false);

        current = Integer.parseInt(userData.getUser().getAvatarID());


        ChangeAvatar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(current<AvatarNumber){
                    current++;
                    next = current;
                }else {
                    current = 1;
                    next = 1;
                }
                AvatarImage = new ImageIcon("src/Head_Big/"+Integer.toString(next)+".png");
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
                if(userData.getUser().getType() == Message.MESSAGE_TYPE.TYPE_SUCCESS){
                    CancelAvatar.setVisible(false);
                    ConfirmAvatar.setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null,"网络连接异常",
                            "错误",JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        CancelAvatar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AvatarImage = new ImageIcon("src/Head_Big/"+userData.getUser().getAvatarID()+".png");
                Avatar.setIcon(AvatarImage);
                CancelAvatar.setVisible(false);
                ConfirmAvatar.setVisible(false);
                current = Integer.parseInt(userData.getUser().getAvatarID());
            }
        });


        BackGround = new JLabel(BackGroundImage);
        BackGround.setBounds(0,200,1040,650);
        add(BackGround);
    }
    public BasicInformationPanel(){
        initialize();
    }
    public void ChangeBackGround(String index){
        BackGroundImage = new ImageIcon("src/BackGroundImage/"+index+".jpg");
        BackGround.setIcon(BackGroundImage);
    }


}
