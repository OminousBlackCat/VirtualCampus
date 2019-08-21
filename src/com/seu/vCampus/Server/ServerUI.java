package com.seu.vCampus.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerUI {
    public static void main(String args[]){
        try{
            ServerSocket serverSocket = new ServerSocket(8000);
            Socket socket = null;
            int count = 0;
            System.out.println("------------Server is Staring----------");
            while (true){
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                count++;
                System.out.println("Number of Client is" + count);
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
