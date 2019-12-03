package com.qiang.qianqiancater.bean;

/**
 * @author QIANG
 */
public class FoodItem {
    // 菜品
    private Cuisine cuisine;
    // 数量
    private int count;
    // 小计金额
    private double subtotal;

    public FoodItem(){

    }

    public FoodItem(Cuisine cuisine,int count){
        this.cuisine = cuisine;
        this.count = count;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // 返回的小计金额是单价乘以数量
    public double getSubtotal() {
        return this.cuisine.getPrice() * this.count;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
