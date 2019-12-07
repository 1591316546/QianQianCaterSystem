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
     * 支付成功，修改订单的支付状态
     * @return
     */
    boolean paySuccess(String oid);
}
