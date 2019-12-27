package com.qiang.qianqiancater.service.impl;

import com.qiang.qianqiancater.bean.OrderForm;
import com.qiang.qianqiancater.bean.OrderItem;
import com.qiang.qianqiancater.bean.PageBean;
import com.qiang.qianqiancater.bean.User;
import com.qiang.qianqiancater.constant.Constant;
import com.qiang.qianqiancater.dao.CuisineDao;
import com.qiang.qianqiancater.dao.OrderFormDao;
import com.qiang.qianqiancater.dao.impl.CuisineDaoImpl;
import com.qiang.qianqiancater.dao.impl.OrderFormDaoImpl;
import com.qiang.qianqiancater.service.OrderService;
import com.qiang.qianqiancater.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author QIANG
 */
public class OrderFormServiceImpl implements OrderService {

    OrderFormDao orderFormDao = new OrderFormDaoImpl();


    /**
     * 保存订单
     *
     * @param orderForm
     */
    @Override
    public void saveOrderForm(OrderForm orderForm) {
        Connection conn = null;
        OrderFormDao orderFormDao = new OrderFormDaoImpl();
        try {
            //获取连接 开启事务
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            //保存订单
            orderFormDao.saveOrderForm(conn, orderForm);
            //保存订单里的每一项
            for (OrderItem oi : orderForm.getOrderItemList()) {
                orderFormDao.saveOrderItem(conn, oi, orderForm.getOid());
            }
            conn.commit();//提交事务
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                //回滚
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JDBCUtils.close(null, conn);
        }
    }

    /**
     * 查询一个用户的订单 封装到页中展示
     *
     * @param user
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public PageBean<OrderForm> getOrderFormPageBean(User user, int pageSize, int currentPage) {

        PageBean<OrderForm> pageBean = new PageBean<>();
        try {
            //总记录数
            long count = orderFormDao.countOrderSomeone(user.getUserId());
            //订单列表
            List<OrderForm> orderFormList = orderFormDao.getAllofSomeone(user.getUserId(), pageSize, currentPage);
            //计算总页数
            int totalPages = (int) ((count % pageSize == 0) ? (count / pageSize) : (count / pageSize + 1));
            //封装到 PageBean
            pageBean.setTotalRecords((int) count);
            pageBean.setTotalPages(totalPages);
            pageBean.setPageSize(pageSize);
            pageBean.setCurrentPage(currentPage);
            pageBean.setDataList(orderFormList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pageBean;
    }

    /**
     * 查询一个状态的全部订单 封装到页中展示
     *
     * @param status
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public PageBean<OrderForm> getOrderFormOfStatusPageBean(int status, int pageSize, int currentPage) {
        PageBean<OrderForm> pageBean = new PageBean<>();
        try {
            //总记录数
            long count = orderFormDao.countOrderStatus(status);
            //订单列表
            List<OrderForm> orderFormList = orderFormDao.getAllOfStatus(status, pageSize, currentPage);
            //计算总页数
            int totalPages = (int) ((count % pageSize == 0) ? (count / pageSize) : (count / pageSize + 1));
            //封装到 PageBean
            pageBean.setTotalRecords((int) count);
            pageBean.setTotalPages(totalPages);
            pageBean.setPageSize(pageSize);
            pageBean.setCurrentPage(currentPage);
            pageBean.setDataList(orderFormList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pageBean;
    }

    /**
     * 支付成功，修改订单的支付状态
     *
     * @param oid
     * @return
     */
    @Override
    public boolean paySuccess(String oid) {
        if (oid != null) {
            OrderFormDao orderFormDao = new OrderFormDaoImpl();
            Connection conn = null;
            try {
                conn = JDBCUtils.getConnection();
                OrderForm orderForm = orderFormDao.getOrderFormById(oid);
                if (orderForm != null) {
                    orderForm.setStatus(Constant.ORDER_YIFUKUAN);//设置为已付款
                    orderForm.setPayTime(new Date());//设置付款时间
                    int i = orderFormDao.updateOrderForm(conn, orderForm);
                    if (i == 1){//修改行数为1表示修改成功
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.close(null, conn);
            }
        }
        return false;
    }

    /**
     * 接单，修改订单的状态
     *
     * @param oid
     * @return
     */
    @Override
    public boolean acceptOrder(String oid) {
        OrderFormDao orderFormDao = new OrderFormDaoImpl();
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            OrderForm orderForm = orderFormDao.getOrderFormById(oid);
            if (orderForm != null) {
                orderForm.setStatus(Constant.ORDER_JIEDAN);//设置为接单
                int i = orderFormDao.updateOrderForm(conn, orderForm);
                if (i == 1){//修改行数为1表示修改成功
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(null, conn);
        }
        return false;
    }

    /**
     * 退单，修改订单的状态
     *
     * @param oid
     * @return
     */
    @Override
    public boolean rejectOrder(String oid) {
        OrderFormDao orderFormDao = new OrderFormDaoImpl();
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            OrderForm orderForm = orderFormDao.getOrderFormById(oid);
            if (orderForm != null) {
                orderForm.setStatus(Constant.ORDER_TUIKUAN);//设置为退单
                int i = orderFormDao.updateOrderForm(conn, orderForm);
                if (i == 1){//修改行数为1表示修改成功
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(null, conn);
        }
        return false;
    }

    /**
     * 订单完成，修改订单的状态
     *
     * @param oid
     * @return
     */
    @Override
    public boolean orderFinish(String oid) {
        OrderFormDao orderFormDao = new OrderFormDaoImpl();
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            OrderForm orderForm = orderFormDao.getOrderFormById(oid);
            if (orderForm != null) {
                orderForm.setStatus(Constant.ORDER_WANCHENG);//设置为已付款
                orderForm.setFinishTime(new Date());//设置付款时间
                int i = orderFormDao.updateOrderForm(conn, orderForm);
                if (i == 1){//修改行数为1表示修改成功
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(null, conn);
        }
        return false;
    }

    /**
     * 查询某状态的订单有没有新的
     *
     * @param status
     * @return
     */
    @Override
    public long haveNewOrder(int status) {
        long count = 0L;
        try {
            count = orderFormDao.countOrderStatus(status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 取得总收入
     *
     * @return
     */
    @Override
    public double getTotalIncome() {
        double totalIncome = 0;
        try {
            totalIncome = orderFormDao.allFinishOrderTotalMoney();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalIncome;
    }
}
