package com.qiang.qianqiancater.dao;

import com.qiang.qianqiancater.bean.Admin;
import com.qiang.qianqiancater.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @author QIANG
 */
public class AdminDao {
    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    public Admin getAdmin(String username){
        String sql = "SELECT id ,username,`password`,r.`rname` role \n" +
                "FROM administrator a \n" +
                "LEFT JOIN role r \n" +
                "ON a.`rid` = r.`rid`\n" +
                "WHERE a.`username` = ?";
        Admin admin = null;
        try {
            admin = queryRunner.query(sql, new BeanHandler<Admin>(Admin.class), username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
