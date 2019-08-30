package com.seu.vCampus.Server;

/**
 * @ServerThread.java
 * @服务端线程
 * @完成日期：2019_8_24
 * @作者：wxy
 */



import com.seu.vCampus.util.Login;
import com.seu.vCampus.util.Message;
import com.seu.vCampus.Database.DatabaseConnection;
import com.seu.vCampus.Database.DatabaseActions;

import java.sql.Connection;
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
    private Message msg;
    private DatabaseConnection connect = new DatabaseConnection();
    private Connection conn = connect.getConn();
    private DatabaseActions act = new DatabaseActions();





    public ServerThread(Socket s){
        this.socket = s;
        is = null;
        bis = null;
        ois = null;

        os = null;
        oos = null;

        msg = new Message();
    }


    @Override
    public void run(){
        try {

            is = socket.getInputStream();          //获得socket的输入流
            os = socket.getOutputStream();         //获得socket输入流

            while (true){

                bis = new BufferedInputStream(is);     //构建缓冲输入流
                ois = new ObjectInputStream(bis);      //反序列化获得对象
                oos = new ObjectOutputStream(os);      //
                msg = (Message) ois.readObject();   //获得message对象
                System.out.println(msg.getECardNumber());
                switch (msg.getType()){
                    case TYPE_LOGIN:
                        try{
                            act.validatePassword(conn, ((Login) msg));
                            System.out.println(msg.getType());
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                        oos.writeObject(msg);
                        break;
                    case TYPE_PERSON:

                        break;

                }


            }

        }catch (IOException ioe){
            ioe.printStackTrace();
        }catch (ClassNotFoundException cfe){
            cfe.printStackTrace();
        }

    }
}
