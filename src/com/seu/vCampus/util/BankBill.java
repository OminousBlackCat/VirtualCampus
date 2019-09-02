package com.seu.vCampus.util;

import java.util.Date;

public class BankBill {

    public enum BILL_TYPE{
        TYPE_INCOME,
        TYPE_EXPENDITURE;
    }


    private Date BillDate;
    private BILL_TYPE BillType;
    private double BillNumber;

    public Date getBillDate() {
        return BillDate;
    }

    public BILL_TYPE getBillType() {
        return BillType;
    }

    public double getBillNumber() {
        return BillNumber;
    }

    public void setBillDate(Date billDate) {
        BillDate = billDate;
    }

    public void setBillType(BILL_TYPE billType) {
        BillType = billType;
    }

    public void setBillNumber(double billNumber) {
        BillNumber = billNumber;
    }
}
