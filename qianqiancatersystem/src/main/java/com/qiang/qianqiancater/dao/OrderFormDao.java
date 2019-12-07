package com.qiang.qianqiancater.dao;

import com.qiang.qianqiancater.bean.OrderForm;
import com.qiang.qianqiancater.bean.OrderItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author QIANG
 */
public interface OrderFormDao {
    /**
     * 保存订单
     * @param orderForm
     * @return
     */
    int saveOrderForm(Connection conn,OrderForm orderForm) throws SQLException;

    /**
     * 保存订单中的一项
     * @param orderItem
     * @return
     */
    int saveOrderItem(Connection conn,OrderItem orderItem,String oid) throws SQLException;

    /**
     * 获取一个订单中的所有项
     * @param oid
     * @return
     */
    List<OrderItem> getAllOrderItems(String oid) throws SQLException;

    /**
     * 修改一个订单
     * @param orderForm
     * @return
     */
    int updateOrderForm(Connection conn,OrderForm orderForm) throws SQLException;

    /**
     * 获取一个订单通过id
     * @param oid
     * @return
     */
    OrderForm getOrderFormById(String oid) throws SQLException;

    /**
     * 传入用户id 和状态码 获取某人某个状态的全部订单
     * 传入null获取全部。
     * @param userId
     * @param status
     * @return
     */
    List<OrderForm> getAll(Integer userId, Integer status) throws SQLException;

    /**
     * 获取某人所有记录 带分页功能
     * @param userId
     * @return
     */
    List<OrderForm> getAllofSomeone(Integer userId,int pageSize,int currentPage) throws SQLException;

    /**
     * 某人的订单总记录数
     * @param userId
     * @return
     */
    Long countOrderForm(Integer userId) throws SQLException;

    /**
     * 获取某种状态的全部订单
     * @param status
     * @return
     */
    List<OrderForm> getAll(Integer status) throws SQLException;
}
