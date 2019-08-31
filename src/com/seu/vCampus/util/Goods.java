package com.seu.vCampus.util;

import java.util.ArrayList;
import java.util.List;

public class Goods extends Message{
    protected List<String> goodsNumber=new ArrayList<String>();
    protected List<String> goodsName=new ArrayList<String>();
    protected List<Short>  goodsPrice= new ArrayList<Short>();
    protected List<Short>  goodsStock= new ArrayList<Short>();

    public Goods(){this.Type = MESSAGE_TYPE.TYPE_NULL;}

    public List<Short> getGoodsPrice() { return goodsPrice; }

    public List<String> getGoodsNumber() {
        return goodsNumber;
    }

    public List<String> getGoodsName() {
        return goodsName;
    }

    public List<Short> getGoodsStock() { return goodsStock; }

    public void setGoodsStock(List<Short> GoodsStock) {}

    public void setGoodsName(List<String> GoodsName) {}

    public void setGoodsNumber(List<String> GoodsNumber) {}

    public void setGoodsPrice(List<Short> GoodsPrice) {}

    public void setGoods(String Number,String Name,short Price,short Stock){}
}
