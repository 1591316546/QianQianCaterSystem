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
    List<Cuisine> getCuiSineByCategoryPutaway(int categoryId, int pageSize, int currentPage) throws SQLException;

    /**
     * 根据菜名模糊搜索
     * @param cname
     * @param pageSize
     * @param currentPage
     * @return
     * @throws SQLException
     */
    List<Cuisine> getCuiSineByNamePutaway(String cname, int pageSize, int currentPage) throws SQLException;

    /**
     * 获取对应类别上架的菜品总数
     * @param categoryId
     * @return
     * @throws SQLException
     */
    Long getTotalNumByCategoryPutaway(int categoryId) throws SQLException;

    /**
     * 模糊搜索对应名字的菜品总数
     * @param cname
     * @return
     * @throws SQLException
     */
    Long getTotalNumByNamePutaway(String cname) throws SQLException;

    /**
     * 根据id获取菜品
     * @param cid
     * @return
     */
    Cuisine getCuisineById(int cid) throws SQLException;

    /**
     * 获取菜品的大图
     * @param cid
     * @return
     */
    String getCuisineBigImg(int cid) throws SQLException;

    /**
     * 获取特色菜
     * @return
     */
    List<Cuisine> getSpecialtyCuisine() throws SQLException;

    /**
     * 分页获取全部的菜品
     * @return
     */
    List<Cuisine> getAllCuisines(int pageSize ,int currentPage) throws SQLException;

    /**
     * 所有菜品的数量
     * @return
     */
    Long allCuisineCount() throws SQLException;

    /**
     * 保存菜品
     * @param cuisine
     * @return
     */
    int saveCuisine(Cuisine cuisine) throws SQLException;

    /**
     * 修改菜品
     * @param cuisine
     * @return
     */
    int updateCuisine(Cuisine cuisine) throws SQLException;

    /**
     * 修改上下架状态
     * @param cid
     * @param putaway
     * @return
     */
    void updatePutaway(int cid , String putaway) throws SQLException;

    /**
     * 删除多个
     * @param cid
     */
    void delete(int cid) throws SQLException;
}
