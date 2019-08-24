package com.seu.vCampus.io;


import com.seu.vCampus.util.Message;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;


import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;

public class ClientIO {

    private String IPAddress;
    private int Port;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private BufferedInputStream bis;


    public ClientIO(String ip, int port){
        IPAddress = ip;
        Port = port;

        try{
            socket = new Socket(IPAddress,Port);
            os = socket.getOutputStream();
            is = socket.getInputStream();
        }catch (UnknownHostException uhe){
            uhe.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void SendMessages(Message send) throws IOException {

       oos = new ObjectOutputStream(os);
       oos.writeObject(send);
    }

    public Message ReceiveMessage() throws IOException{
        ois = new ObjectInputStream(is);
        Message



    }

}
