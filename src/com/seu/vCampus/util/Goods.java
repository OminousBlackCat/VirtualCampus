package com.seu.vCampus.util;

import java.util.ArrayList;

public class Goods extends Message{
    private String goodsNumber;
    private String goodsName;
    private short  goodsPrice;
    private short  goodsStock;

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

    public short getGoodsStock() { return goodsStock; }

    public void setGoodsStock(short goodsStock) {}

    public void setGoodsName(String goodsName) {}

    public void setGoodsNumber(String goodsNumber) {}

    public void setGoodsPrice(short goodsPrice) {}
}
