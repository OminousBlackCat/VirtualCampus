package com.seu.vCampus.util;
import java.util.ArrayList;
import java.util.List;

public class ShopManage extends Goods{
    private List<String> Orders=new ArrayList<String>();
    private List<String>  State=new ArrayList<String>();
    private List<Short> Finance=new ArrayList<Short>();
    private List<Short> Amount=new ArrayList<Short>();

    public ShopManage(){this.Type = MESSAGE_TYPE.TYPE_NULL;}

    public List<Short> getAmount() { return Amount; }

    public List<String> getOrders() {
        return Orders;
    }

    public List<String> getState() {
        return State;
    }

    public List<Short> getFinance() {
        return Finance;
    }

    public void setOrders(List<String> orders) { Orders = orders; }

    public void setState(List<String> state) { State = state; }

    public void setFinance(List<Short> finance) { this.Finance = finance; }

    public void setAmount(List<Short> amount) { Amount = amount; }

    public void setManage(String Ord,String Sta,short Fin,short Amo){
        Orders.add(Ord);
        State.add(Sta);
        Finance.add(Fin);
        Amount.add(Amo);
    }

    @Override
    public void setGoodsNumber(List<String> GoodsNumber) { goodsNumber.addAll(GoodsNumber); }

    @Override
    public void setGoodsName(List<String> GoodsName) { goodsName.addAll(GoodsName); }

    @Override
    public void setGoodsPrice(List<Short> GoodsPrice) { goodsPrice.addAll(GoodsPrice); }

    @Override
    public void setGoodsStock(List<Short> GoodsStock) { GoodsStock.addAll(GoodsStock); }

    @Override
    public void setGoods(String Number, String Name, short Price, short Stock) {
        goodsNumber.add(Number);
        goodsName.add(Name);
        goodsPrice.add(Price);
        goodsStock.add(Stock);
    }
}
