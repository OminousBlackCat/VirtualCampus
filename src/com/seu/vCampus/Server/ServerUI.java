package com.seu.vCampus.Server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

public class ServerUI  extends JFrame{

    private static final int port = 8000;
    private ServerSocket serverSocket;

    private JPanel serverMainPanel;
    private JTextField ipField;
    private JTextArea mainTextArea;
    private JButton StartButton;
    private JButton StopButton;

    public ServerUI(){
        setTitle("服务端");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,300,400);

        serverMainPanel = new JPanel();
        serverMainPanel.setBorder(new EmptyBorder(5,5,5,5));
        serverMainPanel.setBackground(Color.gray);
        setContentPane(serverMainPanel);
        serverMainPanel.setLayout(null);


        StopButton = new JButton("停止服务端");
        StopButton.setEnabled(false);
        StopButton.setBounds(150,300,100,30);
        serverMainPanel.add(StopButton);


        StartButton = new JButton("启动服务端");
        StartButton.setEnabled(true);
        StartButton.setBounds(40,300,100,30);
        serverMainPanel.add(StartButton);


        ipField = new JTextField();
        String localIP = null;
        try{
            localIP = (InetAddress.getLocalHost()).getHostAddress();
        }catch (UnknownHostException uhe){
            uhe.printStackTrace();
        }
        ipField.setText(localIP);
        ipField.setBounds(100,40,150,25);
        serverMainPanel.add(ipField);
        JLabel IPLabel = new JLabel("本机IP:");
        IPLabel.setBounds(50,40,60,25);
        serverMainPanel.add(IPLabel);


        JLabel portLabel = new JLabel("端口号:  " + port);
        portLabel.setBounds(50,70,200,25);
        serverMainPanel.add(portLabel);



        mainTextArea = new JTextArea();
        //mainTextArea.setBounds(50,100,200,180);
        mainTextArea.setLineWrap(true);
        //serverMainPanel.add(mainTextArea);
        JScrollPane scrollPane = new JScrollPane(mainTextArea);
        scrollPane.setBounds(50,100,200,180);
        serverMainPanel.add(scrollPane);



        StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StartButton.setEnabled(false);
                        StopButton.setEnabled(true);
                        mainTextArea.setText(mainTextArea.getText()+"\n"+"正在启动");
                        startServer();
                    }
                }).start();
            }
        });


        StopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartButton.setEnabled(true);
                StopButton.setEnabled(false);
                serverSocket = null;
                mainTextArea.setText(mainTextArea.getText()+"\n"+"服务端已停止");
            }
        });


        setVisible(true);
    }

    private void startServer(){
        try{
            serverSocket = new ServerSocket(port);
            mainTextArea.setText(mainTextArea.getText()+"\n"+"成功启动！");
        }catch (IOException ioe){
            mainTextArea.setText(mainTextArea.getText()+"\n"+ioe.toString());
        }

        int count = 0;
        while (true){
            try{
                Socket socket = serverSocket.accept();
                new  ServerThread(socket).start();
                mainTextArea.setText(mainTextArea.getText()+"\n"+"成功连接到第"+count+"个客户端");
            }catch (IOException ioe){
                mainTextArea.setText(mainTextArea.getText()+"\n"+ioe.toString());
            }
        }
    }

    private void stopServer(){
        try{
            serverSocket.close();
        }catch (IOException ioe){
            mainTextArea.setText(mainTextArea.getText()+"\n"+ioe.toString());
        }
    }

    public static void main(String args[]){
        new ServerUI();
    }
}
