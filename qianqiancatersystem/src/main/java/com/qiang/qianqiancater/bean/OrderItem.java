package com.qiang.qianqiancater.bean;

/**
 * @author QIANG
 */
public class OrderItem {
    private Integer itemId;
    private Integer cid;//菜品Id
    private Integer count;//数量
    private Double subtotal;//小计

    private Cuisine cuisine;//菜品

    public OrderItem() {
    }

    public OrderItem(Cuisine cuisine, Integer count, Double subtotal) {
        this.cuisine = cuisine;
        this.count = count;
        this.subtotal = subtotal;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
