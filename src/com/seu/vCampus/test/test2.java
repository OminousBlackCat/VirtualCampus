package com.seu.vCampus.test;

import com.seu.vCampus.Client.Common;

public class test2 extends Thread {
    private Common c2;
    @Override
    public void run(){
        c2 = Common.getInstance();
        try {
            System.out.println("阿玮死了(3s)");
            sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("杰哥你在干什么啊杰哥");
        c2.setLogin(true);

        try {
            sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("阿玮又死了三秒");
    }
}
