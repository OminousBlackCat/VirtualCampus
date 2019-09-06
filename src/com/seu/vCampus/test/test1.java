package com.seu.vCampus.test;

import com.seu.vCampus.Client.Common;

public class test1 extends Thread{

    private Common c1;

    @Override
    public void run(){
        c1 = Common.getInstance();
        while (true){
//            System.out.print("");
            if(c1.isLogin()) {
                System.out.println("我冲了！");
                break;
            }
        }
    }

    public static void main(String args[]){
        test2 t2 = new test2();
        test1 t1 = new test1();
        t2.start();
        t1.start();
    }
}
