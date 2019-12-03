package com.qiang.qianqiancater.dao;

import com.qiang.qianqiancater.bean.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @author QIANG
 */
public interface CategoryDao {
    /**
     * 根据id获取类别
     * @param categoryId
     * @return
     */
    Category getCategoryById(Integer categoryId) throws SQLException;

    /**
     * 获取所有分类信息
     * @return
     */
    List<Category> getAllCategorys() throws SQLException;

    /**
     * 新增一个分类
     * @param category
     * @return
     */
    Integer addCategory(Category category) throws SQLException;

    /**
     * 删除一个分类
     * @param categoryId
     * @return
     */
    Integer removeCategory(Integer categoryId) throws SQLException;

    /**
     * 修改一个分类
     * @param category
     * @return
     */
    Integer updateCategory(Category category) throws SQLException;
}
