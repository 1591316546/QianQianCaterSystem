package com.qiang.qianqiancater.servlet;

import com.qiang.qianqiancater.bean.Cuisine;
import com.qiang.qianqiancater.bean.PageBean;
import com.qiang.qianqiancater.service.CuisineService;
import com.qiang.qianqiancater.service.impl.CuisineServiceImpl;
import com.qiang.qianqiancater.utils.Msg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author QIANG
 */
@WebServlet("/cuisine/*")
public class CuisineServlet extends BaseServlet {

    CuisineService cuisineService = new CuisineServiceImpl();

    /**
     * 根据类别id获取商品列表
     * 通过json返回一个分页对象
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getCuisineByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryIdStr = request.getParameter("categoryId");
        String pageSizeStr = request.getParameter("pageSize");
        String currentPageStr = request.getParameter("currentPage");

        int pageSize = Integer.parseInt(pageSizeStr);
        int categoryId = Integer.parseInt(categoryIdStr);
        int currentPage = Integer.parseInt(currentPageStr);
        //获取到封装好的数据
        PageBean<Cuisine> pageBean = cuisineService.getCuiSineByCategory(categoryId, pageSize, currentPage);
        //封装消息
        Msg msg = Msg.success().add("page", pageBean);
        responseMsg(msg, response);
    }

    /**
     * 根据ID查询菜品信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getCuisineById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");
        int cid = Integer.parseInt(cidStr);
        Cuisine cuisine = cuisineService.getCuisineById(cid);
        Msg msg = Msg.success().add("cuisine", cuisine);
        responseMsg(msg, response);
    }

    /**
     * 根据菜品ID获取大图
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getCuisineBigImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //返回大图的路径
        String cidStr = request.getParameter("cid");
        int cid = Integer.parseInt(cidStr);
        String imgPath = cuisineService.getCuisineBigImg(cid);
        Msg msg = Msg.success().add("imgPath", imgPath);
        responseMsg(msg, response);
    }

    /**
     * 搜索菜品
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void searchCuisines(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter("cname");
        String pageSizeStr = request.getParameter("pageSize");
        String currentPageStr = request.getParameter("currentPage");

        int pageSize = Integer.parseInt(pageSizeStr);
        int currentPage = Integer.parseInt(currentPageStr);
        //获取到封装好的数据
        PageBean<Cuisine> pageBean = cuisineService.getCuiSineByName(cname, pageSize, currentPage);
        //封装消息
        Msg msg = Msg.success().add("page", pageBean);
        responseMsg(msg, response);
    }

    /**
     * 响应特色菜列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getSpecialCuisines(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cuisine> cuisines = cuisineService.getSpecialtyCuisine();
        responseMsg(Msg.success().add("cuisines",cuisines),response);
    }

}
