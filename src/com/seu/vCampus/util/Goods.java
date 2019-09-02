package com.seu.vCampus.util;

import java.util.ArrayList;
import java.util.List;

public class Goods extends Message{
    protected String goodsNumber;
    protected String goodsName;
    protected short  goodsPrice;
    protected short  goodsStock;

    public Goods(){this.Type = MESSAGE_TYPE.TYPE_NULL;}

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsPrice(short goodsPrice) {
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

    public short getGoodsPrice() {
        return goodsPrice;
    }

    public short getGoodsStock() {
        return goodsStock;
    }
}
