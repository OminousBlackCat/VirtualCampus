package com.seu.vCampus.Client;

import com.seu.vCampus.io.ClientIO;
import com.seu.vCampus.util.Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Launcher extends JFrame{

    private ClientIO io;
    private JPanel clientPanel;
    private JTextField ECardNumber;
    private JPasswordField passWord;
    private JButton Login;
    private JButton config;
    private JPanel configPanel;
    private String ipAddress = "10.203.175.22";
    private int Port = 8000;

    private Launcher(){
        setTitle("登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,300,400);

        clientPanel = new JPanel();
        clientPanel.setBorder(new EmptyBorder(5,5,5,5));
        clientPanel.setBackground(Color.gray);
        setContentPane(clientPanel);
        clientPanel.setLayout(null);

        Login = new JButton("登录");
        Login.setEnabled(true);
        Login.setBounds(150,300,100,30);
        clientPanel.add(Login);

        config = new JButton("配置");
        config.setBounds(40,300,100,30);
        config.setEnabled(true);
        clientPanel.add(config);

        ECardNumber = new JTextField();
        ECardNumber.setBounds(80,40,150,25);
        clientPanel.add(ECardNumber);
        JLabel cardNumber = new JLabel("一卡通号：");
        cardNumber.setBounds(20,40,80,25);
        clientPanel.add(cardNumber);

        passWord = new JPasswordField();
        passWord.setBounds(80,100,150,25);
        clientPanel.add(passWord);
        JLabel psw = new JLabel("密码：");
        psw.setBounds(20,100,80,25);
        clientPanel.add(psw);

        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                io = new ClientIO(ipAddress,Port);
                com.seu.vCampus.util.Login message = new Login();
                message.setPassWord(String.valueOf(passWord.getPassword()));
                message.setECardNumber(ECardNumber.getText());
                message.setAuthorityLevel(5);
                try{
                    io.SendMessages(message);
                }catch (IOException ioe){
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(null,"网络连接异常","错误",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    setVisible(true);

    }

    public static void main(String args[]){
        new Launcher();
    }

}
