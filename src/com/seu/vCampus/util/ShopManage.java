package com.seu.vCampus.util;


public class ShopManage extends Message{
    private String Orders;
    private String State;
    private short finance;

    public ShopManage(){this.Type = MESSAGE_TYPE.TYPE_NULL;}

    public String getOrders() {
        return Orders;
    }

    public String getState() {
        return State;
    }

    public short getFinance() {
        return finance;
    }

    public void setOrders(String orders) {
        Orders = orders;
    }

    public void setState(String state) {
        State = state;
    }

    public void setFinance(short finance) {
        this.finance = finance;
    }

    public void setGoodsPrice(short goodsprice){}

    public void setGoodsName(String goodsname){}

    public void setGoodsNumber(String goodsnumber){}
}
