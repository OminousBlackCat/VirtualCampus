package com.seu.vCampus.IO;


import com.seu.vCampus.util.Message;


import javax.swing.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;

public class ClientIO {

    private static final short TimeOut = 3000;
    private String IPAddress;
    private int Port;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private BufferedInputStream bis;


    public ClientIO(String ip, int port) throws Exception {
        IPAddress = ip;
        Port = port;


        socket = new Socket();
        socket.connect(new InetSocketAddress(IPAddress,Port),5000);
        socket.setSoTimeout(10000);
        os = socket.getOutputStream();
        is = socket.getInputStream();

    }

    public void SendMessages(Message send) {
        try{
            oos = new ObjectOutputStream(os);
            oos.writeObject(send);
        }catch (Exception e){
            e.printStackTrace();
            send.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
        }
    }

    public Message ReceiveMessage(){
        Message rtn = new Message();
        try{
            bis = new BufferedInputStream(is);     //构建缓冲输入流
            ois = new ObjectInputStream(bis);      //反序列化获得对象
            rtn = (Message) ois.readObject();   //获得message对象
            System.out.println(rtn.getType()+rtn.getECardNumber());
            return  rtn;
        }catch (Exception e){
            e.printStackTrace();
            rtn.setType(Message.MESSAGE_TYPE.TYPE_FAIL);
            return  rtn;
        }

    }

}
