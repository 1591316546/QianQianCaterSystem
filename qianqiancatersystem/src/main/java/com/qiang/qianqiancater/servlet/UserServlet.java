package com.qiang.qianqiancater.servlet;

import com.qiang.qianqiancater.bean.DataMsg;
import com.qiang.qianqiancater.bean.User;
import com.qiang.qianqiancater.service.UserService;
import com.qiang.qianqiancater.service.impl.UserServiceImpl;
import com.qiang.qianqiancater.bean.Msg;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author QIANG
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    //获得一个UsersService对象
    private UserService userService = new UserServiceImpl();

    /**
     * 注册功能
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        //先检查验证码
        String checkCode = request.getParameter("checkCode");
        String check_code_session = (String) request.getSession().getAttribute("check_code_session");
        //使验证码失效
        request.getSession().removeAttribute("check_code_session");
        if (check_code_session == null || !check_code_session.equalsIgnoreCase(checkCode)) {
            //验证码不对
            responseMsg(false, "验证码不正确", response);
            return;
        }

        User user = new User();
        try {
            //封装对象
            BeanUtils.populate(user, parameterMap);
            boolean result = userService.register(user);
            if (result) {
                responseMsg(true, "注册成功", response);
            } else {
                responseMsg(false, "注册失败", response);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查用户名是否唯一
     */
    public void checkUsernameUnique(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        boolean result = userService.checkUsernameUnique(username);
//        Msg msg = new Msg();
//        if (result) {
//            msg = Msg.success().add("hint", "用户名可用");
//        } else {
//            msg = Msg.fail().add("hint", "用户名已被占用，换一个试试");
//        }
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonMsg = objectMapper.writeValueAsString(msg);
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(jsonMsg);
        //替换为一个方法
        if (result) {
            responseMsg(true, "用户名可用", response);
        } else {
            responseMsg(false, "用户名已被占用，换一个试试", response);
        }
    }

    /**
     * 账户激活
     */
    public void activeAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String activeCode = request.getParameter("activeCode");
        boolean result = userService.activeAccount(activeCode);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        if (result) {
            response.getWriter().write("您的账户已经激活，可以登录了。");
        }else {
            response.getWriter().write("激活失败，请联系客服。");
        }
    }

    /**
     * 登录
     *
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        String check_code_session = (String) request.getSession().getAttribute("check_code_session");
        //当前session
        //System.out.println("UserServlet ：login ：当前session --->" + request.getSession());
        //使验证码失效
        request.getSession().removeAttribute("check_code_session");
        if (check_code_session == null || !check_code_session.equalsIgnoreCase(checkCode)) {
            //验证码不对
            responseMsg(false, "验证码不正确", response);
            return;
        }
        int result = userService.login(username, password);
        if (result == 1) {
            //登录成功，用户信息写入session
            User user = userService.getUserByUsername(username);
            request.getSession().setAttribute("user", user);
            //响应
            responseMsg(true, "登录成功！", response);
        } else if (result == 2) {
            responseMsg(false, "账户没有激活", response);
        } else if (result == 3) {
            responseMsg(false, "账户被禁用！", response);
        } else {
            responseMsg(false, "用户名或密码错误！", response);
        }
    }

    /**
     * 获取登录信息
     */
    public void getLoginInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //未登录
            responseMsg(Msg.fail(), response);
        } else {
            //已登录
            Msg msg = Msg.success().add("nickname", user.getNickname());
            responseMsg(msg, response);
        }
    }

    /**
     * 退出登录
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //使session失效
        request.getSession().invalidate();
        //重定向到首页
        //response.sendRedirect(getServletContext().getContextPath()+"/jsp/index.jsp");
        // 发送Msg
        responseMsg(Msg.success(), response);
    }

    /**
     * 获取所有用户（后台管理端）
     */
    public void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("limit");

        int currentPage ;
        int pageSize ;
        try {
            currentPage = Integer.parseInt(currentPageStr);
            pageSize = Integer.parseInt(pageSizeStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            responseMsg(DataMsg.fail("参数异常"), response);
            return;
        }
        DataMsg allUsers = userService.getAllUsers(pageSize, currentPage);
        responseMsg(allUsers, response);
    }

    /**
     * 设置用户账户的状态
     */
    public void setActiveStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String status = request.getParameter("status");
        int uid;
        try {
            uid = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            responseMsg(DataMsg.fail("参数转换异常"),response);
            return;
        }
        boolean b = userService.setActiveStatus(uid, status);
        if (b){
            responseMsg(DataMsg.success("修改成功！"),response);
        }else {
            responseMsg(DataMsg.fail("修改失败！"),response);
        }
    }

    /**
     * 取得所有用户数量 （后台）
     * @param request
     * @param response
     * @throws IOException
     */
    public void getUserCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int count = userService.getUserCount();
        responseMsg(DataMsg.success(count,null),response);
    }

}
