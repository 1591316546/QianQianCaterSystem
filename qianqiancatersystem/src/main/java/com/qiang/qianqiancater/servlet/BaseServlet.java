package com.qiang.qianqiancater.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiang.qianqiancater.utils.Msg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author QIANG
 */
public class BaseServlet extends HttpServlet {
    /**
     * 重写service方法，根据路径分发请求到对应的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String uri = req.getRequestURI();
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
//        System.out.println("请求的路径"+uri);
//        System.out.println("方法名："+methodName);

        try {
            Method method = this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //抽取响应客户端的方法
    protected void responseMsg(boolean result, String hint, HttpServletResponse response) throws IOException {
        Msg msg = new Msg();
        if (result) {
            msg = Msg.success().add("hint", hint);
        } else {
            msg = Msg.fail().add("hint", hint);
        }

//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonMsg = objectMapper.writeValueAsString(msg);
//
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(jsonMsg);
        responseMsg(msg,response);
    }
    //抽取响应客户端的方法2
    protected void responseMsg(Msg msg,HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMsg = objectMapper.writeValueAsString(msg);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonMsg);
    }
}
