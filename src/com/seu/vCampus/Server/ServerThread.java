package com.seu.vCampus.Server;

/**
 *
 *
 */



import com.seu.vCampus.util.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.net.Socket;
import java.io.*;


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





        }catch (IOException ioe){
            ioe.printStackTrace();
        }catch (ClassNotFoundException cfe){
            cfe.printStackTrace();
        }

    }
}
