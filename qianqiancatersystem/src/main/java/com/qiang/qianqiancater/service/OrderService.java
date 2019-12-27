package com.qiang.qianqiancater.service;

import com.qiang.qianqiancater.bean.OrderForm;
import com.qiang.qianqiancater.bean.PageBean;
import com.qiang.qianqiancater.bean.User;

/**
 * @author QIANG
 */
public interface OrderService {
    /**
     * 保存订单
     * @param orderForm
     */
    void saveOrderForm(OrderForm orderForm);

    /**
     * 查询一个用户的订单 封装到页中展示
     * @param pageSize
     * @param currentPage
     * @return
     */
    PageBean<OrderForm> getOrderFormPageBean(User user,int pageSize, int currentPage);

    /**
     * 查询一个状态的全部订单 封装到页中展示
     * @param pageSize
     * @param currentPage
     * @return
     */
    PageBean<OrderForm> getOrderFormOfStatusPageBean(int status,int pageSize, int currentPage);

    /**
     * 支付成功，修改订单的支付状态
     * @return
     */
    boolean paySuccess(String oid);
    /**
     * 接单，修改订单的状态
     * @return
     */
    boolean acceptOrder(String oid);
    /**
     * 退单，修改订单的状态
     * @return
     */
    boolean rejectOrder(String oid);
    /**
     * 订单完成，修改订单的状态
     * @return
     */
    boolean orderFinish(String oid);

    /**
     * 查询某状态的订单有没有新的
     * @param status
     * @return
     */
    long haveNewOrder(int status);

    /**
     * 取得总收入
     * @return
     */
    double getTotalIncome();
}
