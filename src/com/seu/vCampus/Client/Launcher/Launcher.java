package com.seu.vCampus.Client.Launcher;

/**
 * @Launcher.java
 * @启动器UI
 * @执行main方法启动客户端
 * @作者：wxy
 * @完成日期：2019_8_26
 */

import com.seu.vCampus.Client.Common;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Enumeration;


public class Launcher extends JFrame{
    private Common FrameData;
    private static Point origin = new Point();

    public Launcher(){
        InitGlobalFont(new Font("Microsoft Yahei", Font.BOLD,15));
        setTitle("登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBounds(400,400,500,400);
        setContentPane(new LauncherPanel());
        setResizable(false);
        FrameData = Common.getInstance();


        Thread launcherThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!FrameData.isLogin()){
                    System.out.print("");
                }
                dispose();
            }
        });
        launcherThread.start();

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

    public static void main(String args[]){
        new Launcher();
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
