package com.qiang.qianqiancater.servlet;

import com.qiang.qianqiancater.bean.Cuisine;
import com.qiang.qianqiancater.bean.FoodBasket;
import com.qiang.qianqiancater.bean.FoodItem;
import com.qiang.qianqiancater.bean.User;
import com.qiang.qianqiancater.service.CuisineService;
import com.qiang.qianqiancater.service.impl.CuisineServiceImpl;
import com.qiang.qianqiancater.utils.Msg;
import org.omg.CORBA.INTERNAL;
import org.omg.CORBA.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author QIANG
 */
@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {

    CuisineService cuisineService = new CuisineServiceImpl();
    /*
     * 添加到菜篮子
     */
    public void addToBasket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //后端检查登录状态
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
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
        FoodBasket foodBasket = getFoodBasket(request);
        //添加到篮子
        foodBasket.addToBasket(cuisine,count);
        //响应成功信息
        responseMsg(Msg.success(),response);
    }

    /**
     * 前端展示菜篮子响应菜篮子数据
     */
    public void showBasket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //拿到菜篮子
        FoodBasket foodBasket = getFoodBasket(request);
        //所有的菜品项列表
        Collection<FoodItem> foodItems = foodBasket.getFoodItems().values();
        //总金额
        Double totalMoney = foodBasket.getTotalMoney();
        //响应
        Msg msg = Msg.success().add("foodItems",foodItems).add("totalMoney",totalMoney);
        responseMsg(msg,response);
    }

    /**
     * 删除一种菜篮子里的菜品
     */
    public void deleteFoodItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cidStr = request.getParameter("cid");
        Integer cid = Integer.parseInt(cidStr);
        //移出菜篮子
        getFoodBasket(request).removeItemFromBasket(cid);
        responseMsg(Msg.success(),response);
    }

    /**
     * 清空菜篮子
     */
    public void clearFoodBasket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        getFoodBasket(request).clearBasket();
        responseMsg(Msg.success(),response);
    }

    /**
     * 获取session中的菜篮子,没有就new一个放进去
     * @param request
     * @return FoodBasket
     */
    protected FoodBasket getFoodBasket(HttpServletRequest request){
        //从session中获取菜篮子
        FoodBasket foodBasket = (FoodBasket) request.getSession().getAttribute("FoodBasket");
        if (foodBasket == null){
            //如果没有就创建一个，加入session
            foodBasket = new FoodBasket();
            request.getSession().setAttribute("FoodBasket",foodBasket);
        }
        return foodBasket;
    }

}
