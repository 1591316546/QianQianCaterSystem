package com.qiang.qianqiancater.service;

import com.qiang.qianqiancater.bean.Cuisine;
import com.qiang.qianqiancater.bean.PageBean;

import java.util.List;

/**
 * @author QIANG
 */
public interface CuisineService {
    /**
     * 根据类别和查询菜品并封装到分页对象中
     * @param categoryId
     * @param pageSize
     * @param currentPage
     * @return
     */
    PageBean<Cuisine> getCuiSineByCategory(int categoryId, int pageSize, int currentPage);

    /**
     * 根据名字查询的菜品封装到分页对象中
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
}
