package com.qiang.qianqiancater.bean;

/**
 * 表示菜品分类信息的类
 * @author QIANG
 */
public class Category {
    private Integer categoryId;// 类别id
    private String categoryName;// 分类的名称

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
