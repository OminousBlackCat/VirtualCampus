package com.seu.vCampus.Client;

import com.seu.vCampus.IO.ClientIO;
import com.seu.vCampus.util.Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Launcher extends JFrame{

    private ClientIO io;
    private JPanel clientPanel;
    private JTextField ECardNumber;
    private JPasswordField passWord;
    private JButton Login;
    private JButton config;
    private String ipAddress = "10.203.175.22";
    private int Port = 8000;

    private Launcher(){
        setTitle("登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400,400,300,300);

        clientPanel = new JPanel();
        clientPanel.setBorder(new EmptyBorder(5,5,5,5));
        clientPanel.setBackground(Color.gray);
        setContentPane(clientPanel);
        clientPanel.setLayout(null);
        setResizable(false);

        Login = new JButton("登录");
        Login.setEnabled(true);
        Login.setBounds(150,200,100,30);
        clientPanel.add(Login);

        config = new JButton("配置");
        config.setBounds(40,200,100,30);
        config.setEnabled(true);
        clientPanel.add(config);

        ECardNumber = new JTextField();
        ECardNumber.setBounds(90,40,150,25);
        clientPanel.add(ECardNumber);
        JLabel cardNumber = new JLabel("一卡通号：");
        cardNumber.setBounds(20,40,80,25);
        clientPanel.add(cardNumber);

        passWord = new JPasswordField();
        passWord.setBounds(90,100,150,25);
        clientPanel.add(passWord);
        JLabel psw = new JLabel("密码：");
        psw.setBounds(20,100,80,25);
        clientPanel.add(psw);

        JLabel IPLabel = new JLabel("服务端IP："+ipAddress);
        IPLabel.setBounds(70,150,150,30);
        clientPanel.add(IPLabel);

        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    io = new ClientIO(ipAddress,Port);
                }catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,"网络连接异常","错误",JOptionPane.ERROR_MESSAGE);
                }

                com.seu.vCampus.util.Login LoginMessage = new Login();
                LoginMessage.setPassWord(String.valueOf(passWord.getPassword()));
                LoginMessage.setECardNumber(ECardNumber.getText());
                LoginMessage.setAuthorityLevel(5);
                try{
                    io.SendMessages(LoginMessage);
                }catch (Exception ioe){
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(null,"网络连接异常","错误",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEnabled(false);

                class ConfigWindow extends JFrame{
                    private ConfigWindow(){
                        setTitle("配置");
                        setBounds(450,450,300,200);

                        JPanel configPanel = new JPanel();
                        setContentPane(configPanel);
                        configPanel.setBorder(new EmptyBorder(5,5,5,5));
                        configPanel.setBounds(0,0,300,200);
                        configPanel.setLayout(null);

                        JTextField ipAdd = new JTextField();
                        ipAdd.setBounds(80,30,150,25);
                        ipAdd.setText(ipAddress);

                        JLabel ipLabel = new JLabel("IP地址：");
                        ipLabel.setBounds(30,30,50,25);
                        configPanel.add(ipLabel);
                        configPanel.add(ipAdd);

                        JLabel portLabel = new JLabel("端口：");
                        portLabel.setBounds(30,75,50,25);
                        JLabel port = new JLabel(Integer.toString(Port));
                        port.setBounds(80,75,150,25);
                        configPanel.add(port);
                        configPanel.add(portLabel);

                        JButton button = new JButton("确认修改");
                        button.setBounds(80,110,100,25);
                        configPanel.add(button);
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ipAddress = ipAdd.getText();
                                setVisible(false);
                                Launcher.this.setEnabled(true);
                                IPLabel.setText("服务端IP："+ipAddress);
                            }
                        });

                        addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                super.windowClosing(e);
                                Launcher.this.setEnabled(true);
                            }
                        });

                        setVisible(true);
                    }
                }

                new ConfigWindow();


            }
        });

    setVisible(true);

    }

    public static void main(String args[]){
        new Launcher();
    }

}
