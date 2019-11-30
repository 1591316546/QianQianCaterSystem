package com.qiang.qianqiancater.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author QIANG
 */
public class JDBCUtils {
    private static DataSource ds;

    static {
        Properties pro = new Properties();
        InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pro.load(in);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    /*未使用连接池的版本*/
    /********************************************************
    private static String user;
    private static String password;
    private static String url;

    static {
        try {
            //获取配置文件
            Properties pro = new Properties();
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pro.load(in);
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");

            Class.forName(pro.getProperty("driver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
    *****************************************************/

    /**
     * 释放连接
     * @param stat
     * @param conn
     */
    public static void close(Statement stat,Connection conn){
        /*if (stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
        //优化后
        close(null,stat,conn);
    }

    /**
     * 释放资源重载
     * @param rs
     * @param stat
     * @param conn
     */
    public static void close(ResultSet rs,Statement stat, Connection conn){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取连接池对象
     * @return
     */
    public static DataSource getDataSource(){
        return ds;
    }
}
