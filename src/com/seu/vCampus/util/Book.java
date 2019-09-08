package com.seu.vCampus.util;

import java.util.Date;

public class Book extends Message{

    private String BID;
    private String Name;
    private String Author;
    private boolean isLent;
    private Date LendDate;
    private short lendDays;


    /**
     * 私有成员的getter和setter
     * 初始化仅初始化一个私有成员，其余均在需要时进行set
     * */
    public Book(){
        isLent = false;
    }

    public String getBID() {
        return BID;
    }

    public String getName() {
        return Name;
    }

    public String getAuthor() {
        return Author;
    }

    public boolean isLent() {
        return isLent;
    }

    public Date getLendDate() {
        return LendDate;
    }

    public short getLendDays() { return lendDays; }

    public void setBID(String BID) {
        this.BID = BID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setLent(boolean lent) {
        isLent = lent;
    }

    public void setLendDate(Date lendDate) {
        LendDate = lendDate;
    }

    public void setLendDays(short LendDays) { lendDays = LendDays; }
}
