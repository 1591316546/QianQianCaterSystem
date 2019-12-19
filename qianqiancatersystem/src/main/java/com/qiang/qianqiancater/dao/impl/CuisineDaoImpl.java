package com.qiang.qianqiancater.dao.impl;

import com.qiang.qianqiancater.bean.Category;
import com.qiang.qianqiancater.bean.Cuisine;
import com.qiang.qianqiancater.dao.CategoryDao;
import com.qiang.qianqiancater.dao.CuisineDao;
import com.qiang.qianqiancater.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author QIANG
 */
public class CuisineDaoImpl implements CuisineDao {

    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
    CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 根据类别查询指定页的菜品数据。
     *
     * @param categoryId
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public List<Cuisine> getCuiSineByCategoryPutaway(int categoryId, int pageSize, int currentPage) throws SQLException {
        String sql = "SELECT `cid`,`cname`,`description`,`image`,`price`,`putaway`,`joindate`,`categoryId`,`isSpecialty`\n" +
                "FROM t_cuisine\n" +
                "WHERE putaway='Y' AND categoryId = ?\n" +
                "LIMIT ?,?";
        List<Cuisine> cuisines = queryRunner.query(sql, new BeanListHandler<Cuisine>(Cuisine.class),
                categoryId, (currentPage - 1) * pageSize, pageSize);

        return cuisines;
    }

    /**
     * 根据菜名模糊搜索
     *
     * @param cname
     * @param pageSize
     * @param currentPage
     * @return
     * @throws SQLException
     */
    @Override
    public List<Cuisine> getCuiSineByNamePutaway(String cname, int pageSize, int currentPage) throws SQLException {
        String sql = "SELECT `cid`,`cname`,`description`,`image`,`price`,`putaway`,`joindate`,`categoryId`,`isSpecialty`\n" +
                "FROM t_cuisine\n" +
                "WHERE putaway = 'Y' AND cname LIKE ?" +
                "LIMIT ?,?";
        List<Cuisine> cuisines = queryRunner.query(sql, new BeanListHandler<Cuisine>(Cuisine.class), "%" + cname + "%", (currentPage - 1) * pageSize, pageSize);
        return cuisines;
    }

    @Override
    public Long getTotalNumByCategoryPutaway(int categoryId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM t_cuisine WHERE putaway='Y' AND categoryId=?";
        Long totalRecords = queryRunner.query(sql, new ScalarHandler<Long>(), categoryId);
        return totalRecords;
    }

    /**
     * 获取对应类别上架的菜品总数
     *
     * @param cname
     * @return
     * @throws SQLException
     */
    @Override
    public Long getTotalNumByNamePutaway(String cname) throws SQLException {
        String sql = "SELECT COUNT(*) FROM t_cuisine WHERE putaway='Y' AND cname LIKE ?";
        Long totalRecords = queryRunner.query(sql, new ScalarHandler<Long>(), "%" + cname + "%");
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
        Cuisine cuisine = queryRunner.query(sql, new BeanHandler<Cuisine>(Cuisine.class), cid);
        //添加菜品的分类信息
        fillCategoryInfo(cuisine);
        return cuisine;
    }

    /**
     * 获取菜品的大图
     *
     * @param cid
     * @return
     */
    @Override
    public String getCuisineBigImg(int cid) throws SQLException {
        String sql = "SELECT bigImg FROM t_images WHERE cid = ?";
        String imgPath = queryRunner.query(sql, new ScalarHandler<String>(), cid);
        return imgPath;
    }

    /**
     * 获取特色菜
     *
     * @return
     */
    @Override
    public List<Cuisine> getSpecialtyCuisine() throws SQLException {
        String sql = "SELECT `cid`,`cname`,`description`,`image`,`price`\n" +
                "FROM t_cuisine\n" +
                "WHERE putaway = 'Y' AND isSpecialty = 'Y'\n" +
                "ORDER BY joindate DESC\n" +
                "LIMIT 0,8";
        List<Cuisine> cuisineList = queryRunner.query(sql, new BeanListHandler<Cuisine>(Cuisine.class));
        return cuisineList;
    }

    /**
     * 分页获取全部的菜品
     *
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public List<Cuisine> getAllCuisines(int pageSize, int currentPage) throws SQLException {
        String sql = "SELECT `cid`,`cname`,`description`,`image`,`price`,`putaway`,`joindate`,`categoryId`,`isSpecialty`\n" +
                "FROM t_cuisine\n" +
                "ORDER BY cid\n" +
                "LIMIT ?,?";
        List<Cuisine> cuisineList = queryRunner.query(sql, new BeanListHandler<Cuisine>(Cuisine.class),
                (currentPage - 1) * pageSize, pageSize);

        return cuisineList;
    }

    /**
     * 所有菜品的数量
     *
     * @return
     */
    @Override
    public Long allCuisineCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM t_cuisine";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query;
    }

    /**
     * 保存菜品
     *
     * @param cuisine
     * @return
     */
    @Override
    public int saveCuisine(Cuisine cuisine) throws SQLException {
        String sql = "INSERT INTO t_cuisine(`cname`,`description`,`image`,`price`,`putaway`,`joindate`,`categoryId`,`isSpecialty`)\n" +
                "VALUES(?,?,?,?,?,?,?,?)";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//转换date类型为字符串以存入数据库
        int update = queryRunner.update(sql,
                cuisine.getCname(),
                cuisine.getDescription(),
                cuisine.getImage(),
                cuisine.getPrice(),
                cuisine.getPutaway(),
                cuisine.getJoindate() == null?null:dateFormat.format(cuisine.getJoindate()),
                cuisine.getCategoryId(),
                cuisine.getIsSpecialty());
        return update;
    }

    /**
     * 修改菜品
     *
     * @param cuisine
     * @return
     */
    @Override
    public int updateCuisine(Cuisine cuisine) throws SQLException {
        String sql = "UPDATE t_cuisine \n" +
                "SET `cname`=?,\n" +
                "`description`=?,\n" +
                "`image`=?,\n" +
                "`price`=?,\n" +
                "`categoryId`=?,\n" +
                "`isSpecialty`=?\n" +
                "WHERE `cid`=?";
        int update = queryRunner.update(sql,
                cuisine.getCname(),
                cuisine.getDescription(),
                cuisine.getImage(),
                cuisine.getPrice(),
                cuisine.getCategoryId(),
                cuisine.getIsSpecialty(),
                cuisine.getCid());
        return update;
    }

    /**
     * 修改上下架状态
     *
     * @param cid
     * @param putaway
     * @return
     */
    @Override
    public void updatePutaway(int cid, String putaway) throws SQLException {
        String sql = "UPDATE t_cuisine SET `putaway` = ? WHERE cid =?";
        queryRunner.update(sql, putaway, cid);
    }

    /**
     * 删除多个
     *
     * @param cid
     */
    @Override
    public void delete(int cid) throws SQLException {
        String sql = "delete from t_cuisine WHERE cid =?";
        queryRunner.update(sql,  cid);
    }

    //填充菜品的分类信息
    private void fillCategoryInfo(Cuisine cuisine) throws SQLException {
        //根据分类ID获取菜品的类别信息
        Category category = categoryDao.getCategoryById(cuisine.getCategoryId());
        cuisine.setCategory(category);
    }
}
