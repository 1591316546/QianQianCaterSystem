package com.qiang.qianqiancater.servlet;

import com.qiang.qianqiancater.bean.*;
import com.qiang.qianqiancater.constant.Constant;
import com.qiang.qianqiancater.service.CuisineService;
import com.qiang.qianqiancater.service.OrderService;
import com.qiang.qianqiancater.service.impl.CuisineServiceImpl;
import com.qiang.qianqiancater.service.impl.OrderFormServiceImpl;
import com.qiang.qianqiancater.bean.Msg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @author QIANG
 */
@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {

    CuisineService cuisineService = new CuisineServiceImpl();
    OrderService orderService = new OrderFormServiceImpl();

    /*
     * 添加到菜篮子
     */
    public void addToBasket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //当前 session
        //System.out.println("OrderServlet: addToBasket: 当前session:" + request.getSession());

        //后端检查登录状态
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return;
        }
        //要放入菜篮子的菜品id
        String cidStr = request.getParameter("cid");
        //数量
        String countStr = request.getParameter("count");
        Integer cid = Integer.parseInt(cidStr);
        Integer count = Integer.parseInt(countStr);
        //查找对应菜品
        Cuisine cuisine = cuisineService.getCuisineById(cid);
        //拿到菜篮子
        FoodBasket foodBasket = getFoodBasket(request.getSession());
        //添加到篮子
        foodBasket.addToBasket(cuisine, count);
        //响应成功信息
        responseMsg(Msg.success(), response);
    }

    /**
     * 前端展示菜篮子响应菜篮子数据
     */
    public void showBasket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //拿到菜篮子
        FoodBasket foodBasket = getFoodBasket(request.getSession());
        //所有的菜品项列表
        Collection<FoodItem> foodItems = foodBasket.getFoodItems().values();
        //总金额
        Double totalMoney = foodBasket.getTotalMoney();
        //响应
        Msg msg = Msg.success().add("foodItems", foodItems).add("totalMoney", totalMoney);
        responseMsg(msg, response);
    }

    /**
     * 删除一种菜篮子里的菜品
     */
    public void deleteFoodItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cidStr = request.getParameter("cid");
        Integer cid = Integer.parseInt(cidStr);
        //移出菜篮子
        getFoodBasket(request.getSession()).removeItemFromBasket(cid);
        responseMsg(Msg.success(), response);
    }

    /**
     * 清空菜篮子
     */
    public void clearFoodBasket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        getFoodBasket(request.getSession()).clearBasket();
        responseMsg(Msg.success(), response);
    }

    /**
     * 确认订单
     */
    public void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取到用户
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //未登录
            responseMsg(Msg.fail(), response);
            return;
        }
        request.setCharacterEncoding("utf-8");
        //接收参数
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String remark = request.getParameter("remark");

        //获取到菜篮子
        FoodBasket foodBasket = getFoodBasket(request.getSession());

        OrderForm orderForm = new OrderForm();//订单对象
        orderForm.setOid(UUID.randomUUID().toString());//设置订单号
        orderForm.setTotalMoney(foodBasket.getTotalMoney());//设置总金额
        orderForm.setStatus(Constant.ORDER_WEIFUKUAN);//设置为未付款状态
        orderForm.setName(name);//用户信息
        orderForm.setAddress(address);
        orderForm.setPhone(phone);
        orderForm.setRemark(remark);
        orderForm.setOrderTime(new Date());//时间
        orderForm.setUser(user);//下单者
        //遍历菜篮子里的每一项
        for (FoodItem foodItem : foodBasket.getFoodItems().values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCuisine(foodItem.getCuisine());//菜品
            orderItem.setCount(foodItem.getCount());//数量
            orderItem.setSubtotal(foodItem.getSubtotal());//小计

            //添加到订单里
            orderForm.getOrderItemList().add(orderItem);
        }

        //保存订单
        orderService.saveOrderForm(orderForm);
        //清空菜篮子
        foodBasket.clearBasket();
        //跳转到第三方的url；
        String url = "http://127.0.0.1:8080/qianqiancater/jsp/the_third-party_payment.jsp?oid=" + orderForm.getOid();
        //响应客户端
        responseMsg(Msg.success().add("url", url), response);
    }

    /**
     * 显示当前登录者的订单数据 (用户部分)
     */
    public void getOrderFromList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //没有登录
            responseMsg(Msg.fail(), response);
            return;
        }
        //接收参数
        String pageSizeStr = request.getParameter("pageSize");
        String currentPageStr = request.getParameter("currentPage");
        int pageSize = 1;
        int currentPage = 1;
        try {
            pageSize = Integer.parseInt(pageSizeStr);
            currentPage = Integer.parseInt(currentPageStr);
        } catch (NumberFormatException e) {
        }
        //获取到封装好的订单列表
        PageBean<OrderForm> orderFormPage = orderService.getOrderFormPageBean(user, pageSize, currentPage);
        //响应到客户端
        Msg msg = Msg.success().add("page", orderFormPage);
        responseMsg(msg, response);
    }

    /**
     * 支付成功接口，修改订单的支付状态为已支付
     */
    public void paySuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String oid = request.getParameter("oid");
        boolean b = orderService.paySuccess(oid);
        if (b) {
            //修改成功
            responseMsg(Msg.success(), response);
        } else {
            //失败
            responseMsg(Msg.fail(), response);
        }
    }

    /**
     * 获得某种状态的订单 （管理端）
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void getOrderByStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String statusStr = request.getParameter("status");
        String curr = request.getParameter("page");//当前页
        String limit = request.getParameter("limit");//每页数据条数
        int status;
        int currentPage;
        int pageSize;
        try {
            status = Integer.parseInt(statusStr);
            currentPage = Integer.parseInt(curr);
            pageSize = Integer.parseInt(limit);
        } catch (NumberFormatException e) {
            responseMsg(DataMsg.fail("参数异常"), response);
            return;
        }

        PageBean<OrderForm> orderPageBean = orderService.getOrderFormOfStatusPageBean(status, pageSize, currentPage);
        if (orderPageBean.getTotalRecords() <= 0) {
            responseMsg(DataMsg.fail("当前没有订单"), response);
        } else {
            responseMsg(DataMsg.success(orderPageBean.getTotalRecords(), orderPageBean.getDataList()), response);
        }
    }

    /**
     * 接受订单 （管理端）
     */
    public void acceptOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] oids = request.getParameterValues("oid");
        boolean flag = true;
        for (String oid : oids) {
            flag = orderService.acceptOrder(oid);
        }
        if (flag) {
            responseMsg(Msg.success(), response);
        } else {
            responseMsg(Msg.fail(), response);
        }
    }

    /**
     * 拒绝订单
     */
    public void rejectOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] oids = request.getParameterValues("oid");
        boolean flag = true;
        for (String oid : oids) {
            flag = orderService.rejectOrder(oid);
        }
        if (flag) {
            responseMsg(Msg.success(), response);
        } else {
            responseMsg(Msg.fail(), response);
        }
    }

    /**
     * 完成订单
     */
    public void finshOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] oids = request.getParameterValues("oid");
        boolean flag = true;
        for (String oid : oids) {
            flag = orderService.orderFinish(oid);
        }
        if (flag) {
            responseMsg(Msg.success(), response);
        } else {
            responseMsg(Msg.fail(), response);
        }
    }

    /**
     * 询问某种状态的订单是否有新订单 根据最后一条记录时间戳
     */
//    public void haveNewOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String statusStr = request.getParameter("status");
//        String timestampStr = request.getParameter("timestamp");
//        int status = -1;
//        long timestamp = 0L;
//        try {
//            status = Integer.parseInt(statusStr);
//            timestamp = Long.parseLong(timestampStr);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//        boolean b = orderService.haveNewOrder(status,timestamp);
//        if (b){
//            responseMsg(Msg.success(),response);
//        }else {
//            responseMsg(Msg.fail(),response);
//        }
//    }

    /**
     * 通过记录数的改变获知有无新订单
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void haveNewOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String statusStr = request.getParameter("status");
//        String timestampStr = request.getParameter("timestamp");
        int status = -1;
        try {
            status = Integer.parseInt(statusStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int count = (int) orderService.haveNewOrder(status);

        responseMsg(Msg.success().add("count", count), response);
    }

    /**
     * 取得总收益的数据
     * @param request
     * @param response
     * @throws IOException
     */
    public void getTotalIncome(HttpServletRequest request, HttpServletResponse response) throws IOException {
       double totalIncome =  orderService.getTotalIncome();
       responseMsg(Msg.success().add("totalIncome",totalIncome),response);
    }

        /**
         * 获取session中的菜篮子,没有就new一个放进去
         *
         * @param session
         * @return FoodBasket
         */
    protected FoodBasket getFoodBasket(HttpSession session) {
        //从session中获取菜篮子
        FoodBasket foodBasket = (FoodBasket) session.getAttribute("FoodBasket");
        if (foodBasket == null) {
            //如果没有就创建一个，加入session
            foodBasket = new FoodBasket();
            session.setAttribute("FoodBasket", foodBasket);
        }
        return foodBasket;
    }

}
