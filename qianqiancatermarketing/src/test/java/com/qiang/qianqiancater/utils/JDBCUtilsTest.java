package com.qiang.qianqiancater.utils;

import com.qiang.qianqiancater.bean.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author QIANG
 */
public class JDBCUtilsTest {

    /**
     * 测试获取连接和关闭连接
     */
    @Test
    public void getConnection() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(connection);
        JDBCUtils.close(null,connection);

    }

    @Test
    public void testAddInsert(){
        Connection conn = null;
        PreparedStatement prestat = null;
        try {
            conn = JDBCUtils.getConnection();
            User user = new User();
            user.setUsername("qinag");
            user.setPassword("123456");
            user.setNickname("小强");
            user.setBrith(new Date());
            user.setEmail("qiang@sss.ss");

            String sql ="insert into tab_user(username,password,nickname,email,brith,registration_time) values(?,?,?,?,?,?)";
            prestat = conn.prepareStatement(sql);
            prestat.setString(1,user.getUsername());
            prestat.setString(2,user.getPassword());
            prestat.setString(3,user.getNickname());
            prestat.setString(4,user.getEmail());
            prestat.setDate(5, user.getBrithForSql());
            prestat.setLong(6,user.getRegistrationTime());
            prestat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(prestat,conn);
        }
    }
}