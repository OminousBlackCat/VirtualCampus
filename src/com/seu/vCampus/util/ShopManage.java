package com.seu.vCampus.util;
import java.util.ArrayList;
import java.util.List;

public class ShopManage extends Goods{
    private List<Goods> goods=new ArrayList<Goods>();

    public ShopManage(){this.Type = MESSAGE_TYPE.TYPE_NULL;}

    public List<Goods> getGoods() {
        return goods;
    }
    public void addGoods(Goods g){
        goods.add(g);
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
}
