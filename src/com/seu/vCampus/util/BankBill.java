package com.seu.vCampus.util;

import java.util.Date;

public class BankBill extends Message{

    public enum BILL_TYPE{
        TYPE_INCOME,
        TYPE_EXPENDITURE;
    }


    private Date BillDate;
    private BILL_TYPE BillType;
    private double BillAmount;

    public Date getBillDate() {
        return BillDate;
    }

    public BILL_TYPE getBillType() {
        return BillType;
    }
    public String getBillTypeString(){
        if(this.getBillType()==BILL_TYPE.TYPE_EXPENDITURE){
            return "支出";
        }else {
            return "收入";
        }
    }

    public double getBillAmount() {
        return BillAmount;
    }

    public void setBillDate(Date billDate) {
        BillDate = billDate;
    }

    public void setBillType(BILL_TYPE billType) {
        BillType = billType;
    }

    public void setBillAmount(double billAmount) {
        BillAmount = billAmount;
    }
}
