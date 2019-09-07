package com.seu.vCampus.util;

import java.util.ArrayList;
import java.util.List;

public class BankCount extends Message {
    private double BankBalance;
    private String BankPassword;
    private String CounterNumber;
    private List<BankBill> CountBill;

    public BankCount() {
        this.BankBalance = 0.0;
        this.BankPassword = "";
        this.CounterNumber = "";
        this.Type = MESSAGE_TYPE.TYPE_NULL;
        this.CountBill = new ArrayList<>();
    }

    public double getBankBalance() {
        return BankBalance;
    }

    public String getBankPassword() {
        return BankPassword;
    }

    public void setBankBalance(double bankBalance) { BankBalance = bankBalance; }

    public void setBankPassword(String bankPassword) {
        BankPassword = bankPassword;
    }
    public void addBill(BankBill bill){
        this.CountBill.add(bill);
    }
    public List<BankBill> getCountBill() { return CountBill; }

    public String getCounterNumber() {
        return CounterNumber;
    }

    public void setCounterNumber(String counterNumber) {
        CounterNumber = counterNumber;
    }
}
