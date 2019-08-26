package com.seu.vCampus.Server;

/**
 *
 *
 */



import com.seu.vCampus.util.Login;
import com.seu.vCampus.util.Message;
import connectTest.AccessConnection;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.net.Socket;
import java.io.*;
import java.sql.SQLException;


public class ServerThread  extends Thread{
    private Socket socket;
    /**
     * 输入的流
     **/
    private InputStream is;
    private BufferedInputStream bis ;
    private ObjectInputStream ois;
    /**
     * 输出的流
     **/
    private ObjectOutputStream oos;
    private OutputStream os;
    /**
     * 输入的message对象与输出的message对象
     **/
    private Message obtian;
    private Message target;





    public ServerThread(Socket s){
        this.socket = s;
        is = null;
        bis = null;
        ois = null;

        os = null;
        oos = null;

        obtian = new Message();
        target = new Message();
    }


    @Override
    public void run(){
        try {

            is = socket.getInputStream();          //获得socket的输入流
            bis = new BufferedInputStream(is);     //构建缓冲输入流
            ois = new ObjectInputStream(bis);      //反序列化获得对象
            obtian = (Message) ois.readObject();   //获得message对象
            System.out.println(obtian.getECardNumber());


            switch (obtian.getType()){
                case TYPE_LOGIN:
                    System.out.println("是登录信息,密码是"+((Login) obtian).getPassWord());
                    AccessConnection login=new AccessConnection();
                    Connection conn=login.getConn();
                    try{
                        String compare = login.passwordCompare(conn,obtian.getECardNumber(),((Login) obtian).getPassWord());
                        System.out.println(compare);
                        System.out.println(((Login) obtian).getPassWord());
                        if(compare.equals(((Login) obtian).getPassWord())){
                            System.out.println("匹配成功");
                        }else  {
                            System.out.println("匹配失败");
                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }

                    break;
                case TYPE_PERSON:

                    break;

            }




        }catch (IOException ioe){
            ioe.printStackTrace();
        }catch (ClassNotFoundException cfe){
            cfe.printStackTrace();
        }

    }
}
