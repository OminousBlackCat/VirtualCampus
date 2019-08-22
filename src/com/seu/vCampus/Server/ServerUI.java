package com.seu.vCampus.Server;


/**
 * 服务端的界面与线程逻
 * 执行main方法启动服务端
 * 完成日期：2019_8_22
 * 作者：wxy
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerUI  extends JFrame{

    private static final int port = 8000;
    private ServerSocket serverSocket;

    private JPanel serverMainPanel;
    private JTextField ipField;
    private JTextArea mainTextArea;
    private JButton StartButton;
    private JButton StopButton;
    private socketThread socketthread;




    class socketThread extends Thread{
        @Override
        public void run(){
            StartButton.setEnabled(false);
            StopButton.setEnabled(true);
            mainTextArea.setText(mainTextArea.getText()+"正在启动");

            try{
                serverSocket = new ServerSocket(port);
                mainTextArea.setText(mainTextArea.getText()+"\n"+"成功启动！");
            }catch (IOException ioe){
                mainTextArea.setText(mainTextArea.getText()+"\n"+ioe.toString());
            }

            int count = 0;
            while (true){
                try{
                    if(serverSocket.isClosed()){
                        return;
                    }
                    System.out.println("正在等待连接.........");
                    Socket socket = serverSocket.accept();
                    new  ServerThread(socket).start();
                    mainTextArea.setText(mainTextArea.getText()+"\n"+"成功连接到第"+count+"个客户端");
                }
                catch (IOException ioe){
                    mainTextArea.setText(mainTextArea.getText()+"\n"+ioe.toString());
                }

            }
        }
    }



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
                socketthread = new socketThread();
                socketthread.start();
            }
        });


        StopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartButton.setEnabled(true);
                StopButton.setEnabled(false);
                try{
                    serverSocket.close();
                }catch (IOException ioe){
                    mainTextArea.setText(mainTextArea.getText()+"\n"+ioe.toString());
                }
                mainTextArea.setText(mainTextArea.getText()+"\n"+"服务端已停止");
            }
        });


        setVisible(true);
    }

    public static void main(String args[]){
        new ServerUI();
    }
}
