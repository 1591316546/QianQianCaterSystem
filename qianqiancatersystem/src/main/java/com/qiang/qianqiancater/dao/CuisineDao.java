package com.qiang.qianqiancater.dao;

import com.qiang.qianqiancater.bean.Cuisine;

import java.sql.SQLException;
import java.util.List;

/**
 * @author QIANG
 */
public interface CuisineDao {
    /**
     * 根据类别查询指定页的菜品数据。
     * @param categoryId
     * @param pageSize
     * @param currentPage
     * @return
     */
    List<Cuisine> getCuiSineByCategory(int categoryId, int pageSize, int currentPage) throws SQLException;

    /**
     * 获取上架的菜品总数
     * @return
     * @throws SQLException
     */
    Long getTotalPutawayNumByCategory(int categoryId) throws SQLException;

    /**
     * 根据id获取菜品
     * @param cid
     * @return
     */
    Cuisine getCuisineById(int cid) throws SQLException;
}
