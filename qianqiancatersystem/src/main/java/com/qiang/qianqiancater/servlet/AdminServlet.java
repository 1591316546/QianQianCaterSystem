package com.qiang.qianqiancater.servlet;

import com.qiang.qianqiancater.bean.Admin;
import com.qiang.qianqiancater.service.AdminService;
import com.qiang.qianqiancater.bean.Msg;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author QIANG
 */
@WebServlet("/admin/*")
public class AdminServlet extends BaseServlet {
    AdminService adminService = new AdminService();

    /**
     * 登录接口
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        System.out.println(username+"---"+password);
        Admin admin = adminService.checkLogin(username, password);
        if (admin == null) {
            responseMsg(Msg.fail(), response);
            return;
        }
        //登录成功存入session
        request.getSession().setAttribute("admin", admin);
        responseMsg(Msg.success(), response);
    }

    /**
     * 获取登录者信息
     */
    public void loginInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            //未登录
            responseMsg(Msg.fail(), response);
            return;
        }
        //登录了
        responseMsg(Msg.success().add("admin", admin), response);
    }

    /**
     * 退出登录
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //使失效
        request.getSession().invalidate();
        responseMsg(Msg.success(), response);
    }
}