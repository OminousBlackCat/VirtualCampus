package com.seu.vCampus.Client;

/**
 * @Launcher.java
 * @启动器UI
 * @执行main方法启动客户端
 * @作者：wxy
 * @完成日期：2019_8_26
 */

import javax.swing.*;


public class Launcher extends JFrame{
    private Common FrameData;

    private Launcher(){
        setTitle("登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400,400,300,300);
        setContentPane(new LauncherPanel());
        setResizable(false);
        FrameData = Common.getInstance();


        Thread launcherThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!FrameData.isLogin()){
                    System.out.print("");
                }
                setVisible(false);
            }
        });
        launcherThread.start();
        setVisible(true);
      }

    public static void main(String args[]){
        new Launcher();
    }

}
