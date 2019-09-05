package com.seu.vCampus.Client.BasicInformation;

import com.seu.vCampus.Client.Common;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class BasicInformationPanel extends JPanel {

    private Common userData;
    private JLabel Avatar;
    private ImageIcon AvatarImage;
    private JPanel AvatarPanel;
    private static ImageIcon BackGroundImage;

    public BasicInformationPanel(){

        userData = Common.getInstance();
        setBackground(Color.gray);
        setForeground(Color.WHITE);
        setLayout(null);

        AvatarPanel = new JPanel();
        AvatarPanel.setBounds(150,100,180,180);
        AvatarPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        AvatarPanel.setBackground(Color.WHITE);
        Avatar = new JLabel();
        Avatar.setBackground(Color.WHITE);
        System.out.println("src/Head_Big/"+userData.getBasicInformation().getAvatarID()+".png");
        AvatarImage = new ImageIcon("src/Head_Big/"+userData.getBasicInformation().getAvatarID()+".png");
        Avatar.setBounds(0,0,180,180);
        Avatar.setIcon(AvatarImage);
        AvatarPanel.add(Avatar);
        add(AvatarPanel);



    }


}
