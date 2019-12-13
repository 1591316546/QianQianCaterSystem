package com.qiang.qianqiancater.bean;


import java.util.Date;

/**
 * @author QIANG
 */
public class Cuisine {
    private Integer cid;
    private String cname; // 菜名
    private String description; //描述

    private String image; //图片
    private Double price; //价格
    private String putaway; //上架状态

    private Date joindate; //入库时间
    private Integer categoryId; // 分类id
    private String isSpecialty; //是否特色菜

    private Category category; // 分类信息；

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPutaway() {
        return putaway;
    }

    public void setPutaway(String putaway) {
        this.putaway = putaway;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getIsSpecialty() {
        return isSpecialty;
    }

    public void setIsSpecialty(String isSpecialty) {
        this.isSpecialty = isSpecialty;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Cuisine{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", putaway='" + putaway + '\'' +
                ", joindate=" + joindate +
                ", categoryId=" + categoryId +
                ", isSpecialty='" + isSpecialty + '\'' +
                ", category=" + category +
                '}';
    }
}
