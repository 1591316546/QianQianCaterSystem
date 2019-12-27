package com.qiang.qianqiancater.filter;

import com.qiang.qianqiancater.bean.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台管理系统 登录权限过滤器
 * @author QIANG
 */
@WebFilter("/adminconsole/*")
public class LoginFilter implements Filter {


    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获得请求路径
        String requestURI = request.getRequestURI();
//        System.out.println("requestUrl--->" + requestURI);

        if (requestURI.contains("/layui") || requestURI.contains("/login.html")) {
            //静态资源 和 登录页 放行
            chain.doFilter(req, resp);
        } else {
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if (admin != null) {
                //登录了放行
                chain.doFilter(req, resp);
            }else {
                //没有登录 ，重定向到登录页面
                response.sendRedirect(request.getContextPath()+"/adminconsole/login.html");
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
