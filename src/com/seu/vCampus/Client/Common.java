package com.seu.vCampus.Client;

/**
 * @名称：Common.java
 * @类型：单例类
 * @用途：用来存储客户端所有的临时数据与对象
 * @使用方法：在你所需要的类中声明一个私有的Common对象，在使用前调用静态方法getInstance便可以获得唯一的实例对象
 * @作者：wxy
 * @完成日期：2019/8/30
 */



import com.seu.vCampus.IO.ClientIO;
import com.seu.vCampus.util.BankBill;
import com.seu.vCampus.util.BankCount;
import com.seu.vCampus.util.Person;
import com.seu.vCampus.util.ShopManage;


public class Common {

    private ClientIO io;
    private String ipAddress;
    private int Port;
    private boolean isLogin;
    private static Common instance = new Common();

/**
 * 在下方添加客户端所需要的信息
 * 例：显示基本细心需要一个Person对象
 * 对应添加：private Person BasicInformationPanel
 * 并且生成对应的get与set函数
 * 事件响应函数处理输入输出流所传输的对象即为此对象
 * UI中添加刷新按钮，所有显示的JSwing控件均与此对象关联
 * 便形成MVC模式
 * */
    private Person BasicInformation;
    private BankCount userCount;
    private BankBill userBill;

    private ShopManage shopInfomation;



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



    public ClientIO getIo() { return io; }
    public void setIo(ClientIO io) { this.io = io; }
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
