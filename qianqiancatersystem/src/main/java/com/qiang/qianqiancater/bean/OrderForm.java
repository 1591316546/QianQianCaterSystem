package com.qiang.qianqiancater.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author QIANG
 */
public class OrderForm {
    private String oid;//订单号UUID
    private Double totalMoney;//总金额
    private Integer status;//订单状态
    private String name;//收货人信息
    private String address;
    private String phone;
    private String remark;
    private Date orderTime;//下单时间
    private Integer uid;//下单者的id
    private Date payTime;//付款时间
    private Date finishTime;//订单完成时间

    private User user;//下单者
    private List<OrderItem> orderItemList;//订单的每一个条目

    public OrderForm() {
        this.orderItemList = new ArrayList<>();
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
