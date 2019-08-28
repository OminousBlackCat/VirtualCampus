package com.seu.vCampus.util;

public class Bank extends Message {
    private short BankBalance;
    private short Income;
    private short Expenditure;

    public Bank() { this.Type = MESSAGE_TYPE.TYPE_NULL; }

    public void setIncome(short income) {
        Income = income;
    }

    public void setExpenditure(short expenditure) {
        Expenditure = expenditure;
    }

    public void setBankBalance(short bankBalance) {
        BankBalance = bankBalance;
    }

    public short getBankBalance() {
        return BankBalance;
    }

    public short getIncome() {
        return Income;
    }

    public short getExpenditure() {
        return Expenditure;
    }
}
