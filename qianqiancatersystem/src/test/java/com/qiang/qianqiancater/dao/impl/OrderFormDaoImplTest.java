package com.qiang.qianqiancater.dao.impl;

import com.qiang.qianqiancater.bean.OrderForm;
import com.qiang.qianqiancater.dao.OrderFormDao;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author QIANG
 */
public class OrderFormDaoImplTest {
    OrderFormDao orderFormDao = new OrderFormDaoImpl();
    @Test
    public void getAllOfStatus() {
        try {
            List<OrderForm> all = orderFormDao.getAllOfStatus(0, 10, 1);
            for (OrderForm item:all){
                System.out.println(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void countOrderStatus() {
        try {
            Long aLong = orderFormDao.countOrderStatus(0);
            System.out.println(aLong);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void countNewOrderStatus() {
        try {
            Long aLong = orderFormDao.countNewOrderStatus(1,1576598194000L);
            System.out.println(aLong);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}