package com.qiang.qianqiancater.bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author QIANG
 */
public class FoodBasket {
    //菜篮子里的菜品项目列表
    private Map<Integer,FoodItem> foodItems;
    //总金额
    private Double totalMoney;

    public FoodBasket() {
        foodItems = new HashMap<>();
        totalMoney = 0.0;
    }

    /**
     * 添加到菜篮子
     */
    public void addToBasket(Cuisine cuisine,Integer count){
        //判断菜品是否已经存在
        if (this.foodItems.containsKey(cuisine.getCid())){
            //存在，只增加数量和金额
            FoodItem foodItem = this.foodItems.get(cuisine.getCid());
            foodItem.setCount(foodItem.getCount() + count);
            //总金额加上新增菜的价钱
            this.totalMoney += cuisine.getPrice() * count;
        }else {
            //不存在 new出一个新的菜品项
            FoodItem foodItem = new FoodItem(cuisine,count);
            this.foodItems.put(cuisine.getCid(),foodItem);
            //总金额加上小计
            this.totalMoney += foodItem.getSubtotal();
        }
    }

    /**
     * 移除菜品
     * @param cid
     */
    public void removeItemFromBasket(Integer cid){
        //移除对应的菜品
        FoodItem foodItem = this.foodItems.remove(cid);
        //减去对应的金额
        this.totalMoney -= foodItem.getSubtotal();
    }

    /**
     * 清空菜篮子
     */
    public void clearBasket(){
        this.foodItems.clear();
        this.totalMoney = 0.0;
    }

    public Map<Integer, FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(Map<Integer, FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public Double getTotalMoney() {
        BigDecimal b = new BigDecimal(totalMoney);
        return b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
