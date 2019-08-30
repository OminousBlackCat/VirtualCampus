package com.seu.vCampus.Client;

import com.seu.vCampus.IO.ClientIO;
import com.seu.vCampus.util.Person;


public class Common {

    private ClientIO io;
    private String ipAddress;
    private int Port;
    private boolean isLogin;
    private static Common instance = new Common();



    private Person BasicInformation;


    public static Common getInstance(){
        return instance;
    }

    private Common(){
        this.ipAddress = "10.203.197.88";
        this.Port = 8000;
        this.isLogin = false;
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
    public boolean isLogin() { return isLogin; }
    public void setLogin(boolean login) { isLogin = login; }
    public void setBasicInformation(Person basicInformation) {
        BasicInformation = basicInformation;
    }
    public Person getBasicInformation() {
        return BasicInformation;
    }
}
