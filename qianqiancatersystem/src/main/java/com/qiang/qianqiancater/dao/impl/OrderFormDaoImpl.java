package com.qiang.qianqiancater.dao.impl;

import com.qiang.qianqiancater.bean.Cuisine;
import com.qiang.qianqiancater.bean.OrderForm;
import com.qiang.qianqiancater.bean.OrderItem;
import com.qiang.qianqiancater.bean.User;
import com.qiang.qianqiancater.dao.CuisineDao;
import com.qiang.qianqiancater.dao.OrderFormDao;
import com.qiang.qianqiancater.dao.UserDAO;
import com.qiang.qianqiancater.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author QIANG
 */
public class OrderFormDaoImpl implements OrderFormDao {

    QueryRunner queryRunnerG = new QueryRunner(JDBCUtils.getDataSource());
    UserDAO userDAO = new UserDAOImpl();
    CuisineDao dao = new CuisineDaoImpl();

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//转换date类型为字符串以存入数据库
    /**
     * 保存订单
     *
     * @param conn
     * @param orderForm
     * @return
     */
    @Override
    public int saveOrderForm(Connection conn, OrderForm orderForm) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "INSERT INTO t_orderform(`oid`,`totalMoney`,`status`,`name`,`address`,`phone`,`remark`,`orderTime`,`uid`)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        int result = queryRunner.update(conn, sql,
                orderForm.getOid(),
                orderForm.getTotalMoney(),
                orderForm.getStatus(),
                orderForm.getName(),
                orderForm.getAddress(),
                orderForm.getPhone(),
                orderForm.getRemark(),
                dateFormat.format(orderForm.getOrderTime()),
                orderForm.getUser().getUserId());
        return result;
    }

    /**
     * 保存订单中的一项
     *
     * @param conn
     * @param orderItem
     * @return
     */
    @Override
    public int saveOrderItem(Connection conn, OrderItem orderItem, String oid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "INSERT INTO t_orderitem (`cid`,`count`,`subtotal`,`oid`)\n" +
                "VALUES (?,?,?,?)";
        int result = queryRunner.update(conn, sql, orderItem.getCuisine().getCid(), orderItem.getCount(), orderItem.getSubtotal(), oid);
        return result;
    }

    /**
     * 获取一个订单中的所有项
     *
     * @param oid
     * @return
     */
    @Override
    public List<OrderItem> getAllOrderItems(String oid) throws SQLException {
        String sql = "SELECT `itemId`,`cid`,`count`,`subtotal`\n" +
                "FROM t_orderitem WHERE oid =?";
        List<OrderItem> orderItems = queryRunnerG.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), oid);
        //查询并填充每一个订单项中的菜品
        for (OrderItem item : orderItems){
            Cuisine cuisine = dao.getCuisineById(item.getCid());
            item.setCuisine(cuisine);
        }
        return orderItems;
    }

    /**
     * 修改一个订单
     *
     * @param conn
     * @param orderForm
     * @return
     */
    @Override
    public int updateOrderForm(Connection conn, OrderForm orderForm) throws SQLException {
        String sql = "UPDATE t_orderform \n" +
                "SET \n" +
                "`totalMoney`=?,\n" +
                "`status`=?,\n" +
                "`name`=?,\n" +
                "`address`=?,\n" +
                "`phone`=?,\n" +
                "`remark`=?,\n" +
                "`payTime`=?,\n" +
                "`finishTime`=?\n" +
                "WHERE `oid`=?";
        QueryRunner queryRunner = new QueryRunner();
        int result = queryRunner.update(conn, sql,
                orderForm.getTotalMoney(),
                orderForm.getStatus(),
                orderForm.getName(),
                orderForm.getAddress(),
                orderForm.getPhone(),
                orderForm.getRemark(),
                orderForm.getPayTime() == null ? null : dateFormat.format(orderForm.getPayTime()),
                orderForm.getFinishTime() == null ? null : dateFormat.format(orderForm.getFinishTime()),
                orderForm.getOid());

        return result;
    }

    /**
     * 获取一个订单通过id
     *
     * @param oid
     * @return
     */
    @Override
    public OrderForm getOrderFormById(String oid) throws SQLException {
        String sql = "SELECT `oid`,`totalMoney`,`status`,`name`,`address`,`phone`,`remark`,`orderTime`,`uid`,`payTime`,`finishTime`\n" +
                "FROM t_orderform\n" +
                "WHERE oid=?";
        OrderForm orderForm = queryRunnerG.query(sql, new BeanHandler<OrderForm>(OrderForm.class), oid);
        //填充
        fillUserAndOrderItemInfo(orderForm);
        return orderForm;
    }

    /**
     * 传入用户id 和状态码 获取某人某个状态的全部订单
     * 传入null获取全部。
     *
     * @param userId
     * @param status
     * @return
     */
    @Override
    public List<OrderForm> getAllOfStatus(Integer userId, Integer status) throws SQLException {
        String sql = "SELECT `oid`,`totalMoney`,`status`,`name`,`address`,`phone`,`remark`,`orderTime`,`uid`,`payTime`,`finishTime`\n" +
                "FROM t_orderform\n" +
                "WHERE 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        if (!(userId == null)) {
            sb.append(" AND `uid` = ?");

            params.add(userId);
        }
        if (!(status == null)) {
            sb.append(" AND `status`=?");
            params.add(status);
        }

        sql = sb.toString();

        List<OrderForm> orderFormList = queryRunnerG.query(sql, new BeanListHandler<OrderForm>(OrderForm.class), params.toArray());

        //填充订单项和用户信息
        for (OrderForm orderForm :orderFormList){
            fillUserAndOrderItemInfo(orderForm);
        }
        return orderFormList;
    }

    /**
     * 获取某人所有记录 分页
     *
     * @param userId
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public List<OrderForm> getAllofSomeone(Integer userId, int pageSize, int currentPage) throws SQLException {
        String sql = "SELECT `oid`,`totalMoney`,`status`,`name`,`address`,`phone`,`remark`,`orderTime`,`uid`,`payTime`,`finishTime`\n" +
                "FROM t_orderform\n" +
                "WHERE uid=? " +
                "ORDER BY orderTime DESC LIMIT ?,?";
        List<OrderForm> orderFormList = queryRunnerG.query(sql, new BeanListHandler<OrderForm>(OrderForm.class),
                userId, (currentPage - 1) * pageSize, pageSize);
        //填充订单项和用户信息
        for (OrderForm orderForm :orderFormList){
            fillUserAndOrderItemInfo(orderForm);
        }
        return orderFormList;
    }

    /**
     * 某人的订单总记录数
     *
     * @param userId
     * @return
     */
    @Override
    public Long countOrderSomeone(Integer userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM t_orderform WHERE uid = ?";
        Long count = queryRunnerG.query(sql, new ScalarHandler<Long>(), userId);
        return count;
    }

    /**
     * 获取某种状态的全部订单
     *
     * @param status
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public List<OrderForm> getAllOfStatus(Integer status, int pageSize, int currentPage) throws SQLException {
        String sql = "SELECT `oid`,`totalMoney`,`status`,`name`,`address`,`phone`,`remark`,`orderTime`,`uid`,`payTime`,`finishTime`\n" +
                "FROM t_orderform \n" +
                "WHERE `status`=?\n" +
                "ORDER BY `payTime` DESC\n" +
                "LIMIT ?,?";
        List<OrderForm> orderFormList = queryRunnerG.query(sql, new BeanListHandler<OrderForm>(OrderForm.class), status, (currentPage - 1) * pageSize, pageSize);
        //填充订单项和用户信息
        for (OrderForm orderForm :orderFormList){
            fillUserAndOrderItemInfo(orderForm);
        }
        return orderFormList;
    }

    /**
     * 某种状态的订单总数
     *
     * @param status
     * @return
     */
    @Override
    public Long countOrderStatus(Integer status) throws SQLException {
        String sql = "SELECT COUNT(*) FROM t_orderform WHERE `status`=?";
        Long query = queryRunnerG.query(sql, new ScalarHandler<Long>(),status);
        return query;
    }

    /**
     * 统计某状态最新订单的个数
     *
     * @param status
     * @param timestamp
     * @return
     */
    @Override
    public Long countNewOrderStatus(int status, long timestamp) throws SQLException {
        String sql = "SELECT COUNT(*) FROM t_orderform WHERE `status` = ? AND `payTime` > ?";
        Long count = queryRunnerG.query(sql, new ScalarHandler<Long>(), status, dateFormat.format(new Date(timestamp)));
        return count;
    }

    /**
     * 总的订单收益，完成的订单总额
     *
     * @return
     */
    @Override
    public double allFinishOrderTotalMoney() throws SQLException {
        String sql = "SELECT SUM(totalMoney) FROM t_orderform WHERE `status`=3";
        Double query = queryRunnerG.query(sql, new ScalarHandler<Double>());
        return query;
    }

    /**
     * 填充订单的每一个子项 和 下单用户信息
     * @param orderForm
     * @throws SQLException
     */
    private void fillUserAndOrderItemInfo(OrderForm orderForm) throws SQLException {
        //填充订单的每一项
        List<OrderItem> allOrderItems = getAllOrderItems(orderForm.getOid());
        orderForm.setOrderItemList(allOrderItems);
        //用户信息
        User user = userDAO.getUserByUserId(orderForm.getUid());
        orderForm.setUser(user);
    }
}
