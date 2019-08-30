package com.seu.vCampus.Client;

import com.seu.vCampus.IO.ClientIO;


public class Common {

    private ClientIO io;
    private String ipAddress = "10.203.175.22";
    private int Port = 8000;
    private static Common common = new Common();


    public static Common getInstance(){
        return common;
    }

    public void startIO()throws Exception{
        io = new ClientIO(ipAddress, Port);
    }



    public ClientIO getIo() {
        return io;
    }

    public void setIo(ClientIO io) {
        this.io = io;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return Port;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setPort(int port) {
        Port = port;
    }
}
