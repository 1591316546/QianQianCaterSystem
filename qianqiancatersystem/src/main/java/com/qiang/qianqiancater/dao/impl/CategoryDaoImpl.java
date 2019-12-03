package com.qiang.qianqiancater.dao.impl;

import com.qiang.qianqiancater.bean.Category;
import com.qiang.qianqiancater.dao.CategoryDao;
import com.qiang.qianqiancater.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author QIANG
 */
public class CategoryDaoImpl implements CategoryDao {

    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
    /**
     * 根据id获取类别
     *
     * @param categoryId
     * @return
     */
    @Override
    public Category getCategoryById(Integer categoryId) throws SQLException {
        String sql = "SELECT `categoryId`,`categoryName` FROM t_category WHERE categoryId = ?";
        Category category = queryRunner.query(sql, new BeanHandler<Category>(Category.class), categoryId);
        return category;
    }

    /**
     * 获取所有分类信息
     *
     * @return
     */
    @Override
    public List<Category> getAllCategorys() throws SQLException {
        String sql = "SELECT `categoryId`,`categoryName` FROM t_category";
        List<Category> categorys = queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
        return categorys;
    }

    /**
     * 新增一个分类
     *
     * @param category
     * @return
     */
    @Override
    public Integer addCategory(Category category) throws SQLException {
        String sql = "INSERT INTO t_category(`categoryName`) VALUES (?)";
        return queryRunner.update(sql, category.getCategoryName());
    }

    /**
     * 删除一个分类
     *
     * @param categoryId
     * @return
     */
    @Override
    public Integer removeCategory(Integer categoryId) throws SQLException {
        String sql = "DELETE FROM t_category WHERE categoryId = ?";
        return queryRunner.update(sql, categoryId);
    }

    /**
     * 修改一个分类
     *
     * @param category
     * @return
     */
    @Override
    public Integer updateCategory(Category category) throws SQLException {
        String sql = "UPDATE t_category SET categoryName = ? WHERE categoryId = ?";
        return queryRunner.update(sql, category.getCategoryName(),category.getCategoryId());
    }
}
