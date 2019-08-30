package com.seu.vCampus.util;


public class ShopManage extends Goods{
    private String Orders;
    private String State;
    private short Finance;
    private short Amount;

    public ShopManage(){this.Type = MESSAGE_TYPE.TYPE_NULL;}

    public short getAmount() { return Amount; }

    public String getOrders() {
        return Orders;
    }

    public String getState() {
        return State;
    }

    public short getFinance() {
        return Finance;
    }

    public void setOrders(String orders) {
        Orders = orders;
    }

    public void setState(String state) {
        State = state;
    }

    public void setFinance(short finance) {
        this.Finance = finance;
    }

    public void setAmount(short amount) { Amount = amount; }

    @Override
    public void setGoodsNumber(String goodsNumber) { super.setGoodsNumber(goodsNumber); }

    @Override
    public void setGoodsName(String goodsName) { super.setGoodsName(goodsName); }

    @Override
    public void setGoodsPrice(short goodsPrice) { super.setGoodsPrice(goodsPrice); }

    @Override
    public void setGoodsStock(short goodsStock) { super.setGoodsStock(goodsStock); }
}
