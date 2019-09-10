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
import com.seu.vCampus.util.*;

import java.util.ArrayList;
import java.util.List;


public class Common {

    private ClientIO io;
    private String ipAddress;
    private int Port;
    private boolean isLogin;
    private static Common instance = new Common();

/**
 * 在下方添加客户端所需要的信息
 * 例：显示基本信息需要一个Person对象
 * 对应添加：private Person BasicInformationPanel
 * 并且生成对应的get与set函数
 * 事件响应函数处理输入输出流所传输的对象即为此对象
 * UI中添加刷新按钮，所有显示的JSwing控件均与此对象关联
 * 便形成MVC模式
 * */
    private Person User;
    private BankCount userCount;
    private BankBill userBill;
    private ShopManage shopInformation;
    private BookManage bookInformation;
    private ArrayList<Goods> ShoppingList;
    private NewsManage NewsList;
    private static final int MAX_LEND_BOOK = 10;

    public int getSkinNumber() {
        return skinNumber;
    }

    public void setSkinNumber(int skinNumber) {
        this.skinNumber = skinNumber;
    }

    private int skinNumber;


    public static Common getInstance(){
        return instance;
    }

    private Common(){
        this.ipAddress = "169.254.80.230";
        this.Port = 8000;
        this.isLogin = false;
        User = new Person();
        userCount = new BankCount();
        shopInformation = new ShopManage();
        bookInformation = new BookManage();
        ShoppingList = new ArrayList<>();
        NewsList = new NewsManage();
        skinNumber = 1;
    }

    public void startIO()throws Exception{
        io = new ClientIO(ipAddress, Port);
    }

    public void reset(){
        isLogin = false;
        io = null;
        User = null;
        userCount = null;
        shopInformation = null;
        bookInformation = null;
    }



    public ClientIO getIO() { return io; }
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
    public void setUser(Person user) {
        User = user;
    }
    public void setUserBill(BankBill UserBill) { userBill = UserBill; }
    public void setUserCount(BankCount UserCount) { userCount = UserCount; }
    public void setShopInformation(ShopManage ShopInformation) { shopInformation = ShopInformation; }
    public void setBookInformation(BookManage BookInformation) { bookInformation = BookInformation; }

    public BankBill getUserBill() { return userBill; }
    public BankCount getUserCount() { return userCount; }
    public ShopManage getShopInformation() { return shopInformation; }
    public BookManage getBookInformation() { return bookInformation; }
    public Person getUser() {
        return User;
    }
    public ArrayList<Goods> getShoppingList() {return ShoppingList;}
    public void addGoodsIntoList(Goods add){
        ShoppingList.add(add);
    }
    public void deleteGoods(Goods delete){
        for(int i = 0;i<ShoppingList.size();i++){
            if(delete.getGoodsNumber().equals(ShoppingList.get(i).getGoodsNumber()))
                ShoppingList.remove(i);
        }
    }

    public NewsManage getNewsList() {
        return NewsList;
    }
    public void setNewsList(NewsManage newsList) {
        NewsList = newsList;
    }
    public static int getMaxLendBook() {
        return MAX_LEND_BOOK;
    }
}
