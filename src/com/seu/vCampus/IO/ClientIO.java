package com.seu.vCampus.IO;


import com.seu.vCampus.util.Message;


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
        os = socket.getOutputStream();
        is = socket.getInputStream();

    }

    public void SendMessages(Message send) throws Exception {
       oos = new ObjectOutputStream(os);
       oos.writeObject(send);
    }

    public Message ReceiveMessage() throws IOException{
        bis = new BufferedInputStream(is);     //构建缓冲输入流
        ois = new ObjectInputStream(bis);      //反序列化获得对象
        Message rtn = new Message();
        try{
            rtn = (Message) ois.readObject();   //获得message对象
        }catch (ClassNotFoundException cne){
            cne.printStackTrace();
        }
        return  rtn;
    }

}
