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
}