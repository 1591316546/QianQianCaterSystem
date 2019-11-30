package com.qiang.qianqiancater.dao.impl;

import com.qiang.qianqiancater.bean.Cuisine;
import com.qiang.qianqiancater.dao.CuisineDao;
import com.qiang.qianqiancater.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author QIANG
 */
public class CuisineDaoImpl implements CuisineDao {

    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
    /**
     * 根据类别查询指定页的菜品数据。
     *
     * @param categoryId
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public List<Cuisine> getCuiSineByCategory(int categoryId, int pageSize, int currentPage) throws SQLException {
        String sql = "SELECT `cid`,`cname`,`description`,`image`,`price`,`putaway`,`joindate`,`categoryId`,`isSpecialty`\n" +
                    "FROM t_cuisine\n" +
                    "WHERE putaway='Y' AND categoryId = ?\n" +
                    "LIMIT ?,?";
        List<Cuisine> cuisines = queryRunner.query(sql, new BeanListHandler<Cuisine>(Cuisine.class),
                categoryId,(currentPage-1)*pageSize,pageSize);
        return cuisines;
    }

    @Override
    public Long getTotalPutawayNumByCategory(int categoryId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM t_cuisine WHERE putaway='Y' AND categoryId=?";
        Long totalRecords = queryRunner.query(sql, new ScalarHandler<Long>(),categoryId);
        return totalRecords;
    }

    /**
     * 根据id获取菜品
     *
     * @param cid
     * @return
     */
    @Override
    public Cuisine getCuisineById(int cid) throws SQLException {
        String sql = "SELECT `cid`,`cname`,`description`,`image`,`price`,`putaway`,`joindate`,`categoryId`,`isSpecialty`\n" +
                "FROM t_cuisine\n" +
                "WHERE cid = ?";
        Cuisine cuisine = queryRunner.query(sql,new BeanHandler<Cuisine>(Cuisine.class),cid);
        return cuisine;
    }
}
