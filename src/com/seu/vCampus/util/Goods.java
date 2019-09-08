package com.seu.vCampus.util;

public class Goods extends Message{
    protected String goodsNumber;
    protected String goodsName;
    protected double goodsPrice;
    protected short  goodsStock;

    public Goods(){this.Type = MESSAGE_TYPE.TYPE_NULL;}

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setGoodsStock(short goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public short getGoodsStock() {
        return goodsStock;
    }
}
