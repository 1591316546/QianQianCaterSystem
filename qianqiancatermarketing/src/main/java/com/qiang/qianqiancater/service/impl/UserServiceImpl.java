package com.qiang.qianqiancater.service.impl;

import com.qiang.qianqiancater.bean.User;
import com.qiang.qianqiancater.dao.UserDAO;
import com.qiang.qianqiancater.dao.impl.UserDAOImpl;
import com.qiang.qianqiancater.service.UserService;
import com.qiang.qianqiancater.utils.MailUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.UUID;

/**
 * @author QIANG
 */
public class UserServiceImpl implements UserService {

    //DAO对象
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean checkUsernameUnique(String username) {
        User u = userDAO.getUserByUsername(username);
        if (u == null) {
            //该用户名可用
            return true;
        } else {
            //该用户名不可用
            return false;
        }
    }

    @Override
    public boolean register(User user) {
        //后端检查用户名唯一
        boolean b = checkUsernameUnique(user.getUsername());
        if (b) {
            //生成激活码
            String activeCode = UUID.randomUUID().toString() + new Date().getTime();
            user.setActiveCode(activeCode);
            //保存用户
            int i = userDAO.saveUser(user);
            if (i == 1) {
                //保存成功
                try {
                    String addr = InetAddress.getLocalHost().getHostAddress();//获得本机IP
                    //拼接激活url
                    String url = "http://" + addr + "/user/activeAccount?activeCode=" + activeCode;
                    MailUtils.sendEmail("千乾餐饮新用户注册激活",
                            "您已经注册成功，<a href='" + url + "'>点击此处激活账户</a>。" +
                                    "如果不能自动跳转，请手动复制以下地址到浏览器地址栏激活。<br/>" +
                                    url + "<br/>" +
                                    "如果不是您本人注册，请忽略该邮件。请勿回复。",
                            user.getEmail(), null);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    @Override
    public boolean activeAccount(String activeCode) {
        int i = userDAO.setActiveStatus(activeCode);
        if (i == 1) {
            return true;
        }
        return false;
    }

    @Override
    public int login(String username, String password) {
        //检查用户是否激活，或是被禁用
        User user = userDAO.getUserByUsernameAndPswd(username, password);
        if (user == null) {
            //用户名和密码不匹配
            return 4;
        } else {
            if ("Y".equals(user.getActiveStatus())) {
                //已经激活,同意登录
                return 1;
            }else if("D".equals(user.getActiveStatus())){
                //没有激活
                return 2;
            }else {
                //账号禁用
                return 3;
            }
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
