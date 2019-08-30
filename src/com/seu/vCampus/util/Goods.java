package com.seu.vCampus.util;

public class Goods extends Message{
    private String goodsNumber;
    private String goodsName;
    private short  goodsPrice;

    public Goods(){this.Type = MESSAGE_TYPE.TYPE_NULL;}

    public short getGoodsPrice() {
        return goodsPrice;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) { this.goodsName = goodsName; }

    public void setGoodsNumber(String goodsNumber) { this.goodsNumber = goodsNumber; }

    public void setGoodsPrice(short goodsPrice) { this.goodsPrice = goodsPrice; }
}
