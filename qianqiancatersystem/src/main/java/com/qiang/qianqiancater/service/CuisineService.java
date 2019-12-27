package com.qiang.qianqiancater.service;

import com.qiang.qianqiancater.bean.Cuisine;
import com.qiang.qianqiancater.bean.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author QIANG
 */
public interface CuisineService {
    /**
     * 根据类别和查询已上架菜品并封装到分页对象中
     * @param categoryId
     * @param pageSize
     * @param currentPage
     * @return
     */
    PageBean<Cuisine> getCuiSineByCategory(int categoryId, int pageSize, int currentPage);

    /**
     * 根据名字模糊查询已上架的菜品封装到分页对象中
     * @param cname
     * @param pageSize
     * @param currentPage
     * @return
     */
    PageBean<Cuisine> getCuiSineByName(String cname, int pageSize, int currentPage);

    /**
     * 根据ID获取菜品
     * @param cid
     * @return
     */
    Cuisine getCuisineById(int cid);

    /**
     * 获取菜的大图
     * @param cid
     * @return
     */
    String getCuisineBigImg(int cid);

    /**
     * 获取特色菜
     * @return
     */
    List<Cuisine> getSpecialtyCuisine();

    /**
     * 获取所有菜品封装到pageBean
     * @param pageSize
     * @param currentPage
     * @return
     */
    PageBean<Cuisine> getAllCuiSines(int pageSize, int currentPage);

    /**
     * 保存菜品 成功返回true
     * @param cuisine
     * @return
     */
    boolean saveCuisine(Cuisine cuisine);

    /**
     * 修改菜品 成功返回true
     * @param cuisine
     * @return
     */
    boolean updateCuisine(Cuisine cuisine);

    /**
     * 修改上架下架的状态
     * @param cids
     * @param putaway
     */
    boolean updatePutaway(String[] cids ,String putaway);

    /**
     * 删除
     * @param cids
     */
    boolean delete(String[] cids) ;

    /**
     * 获取菜品总数量
     * @return
     */
    int getCuisinesCount();
}
