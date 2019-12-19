package com.qiang.qianqiancater.dao.impl;

import com.qiang.qianqiancater.bean.User;
import com.qiang.qianqiancater.dao.UserDAO;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author QIANG
 */
public class UserDAOImplTest {
    UserDAO userDAO = new UserDAOImpl();
    @Test
    public void getUserByUsername() {
        User qiang = userDAO.getUserByUsername("qiang3");
        System.out.println(qiang);//如果找不到直接抛出异常
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("jun");
        user.setPswd("123456");
        user.setNickname("军");
        user.setEmail("jun@126.com");
        user.setGender("male");
        int i = userDAO.saveUser(user);
        System.out.println("完成："+i);
    }

    @Test
    public void getUserByUsernameAndPswd() {
        User user = userDAO.getUserByUsernameAndPswd("qiang2","123456");
        System.out.println(user);
    }

    @Test
    public void updateUser() {
        User user = userDAO.getUserByUsername("qiang1");
        user.setNickname("强子");
        int i = userDAO.updateUser( user,user.getUserId());
        System.out.println(i);
    }

    @Test
    public void testSetActiveStatus(){
        int i = userDAO.setActiveStatus("6afd5973-a58b-4676-9d35-1d423c7784c51574169480982");
        System.out.println(i);
    }

    @Test
    public void testGetAll(){
        List<User> i = null;
        try {
            i = userDAO.getAll(0,10);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}