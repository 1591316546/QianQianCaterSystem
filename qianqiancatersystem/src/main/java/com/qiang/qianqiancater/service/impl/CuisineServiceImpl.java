package com.qiang.qianqiancater.service.impl;

import com.qiang.qianqiancater.bean.Cuisine;
import com.qiang.qianqiancater.bean.PageBean;
import com.qiang.qianqiancater.dao.CategoryDao;
import com.qiang.qianqiancater.dao.CuisineDao;
import com.qiang.qianqiancater.dao.impl.CategoryDaoImpl;
import com.qiang.qianqiancater.dao.impl.CuisineDaoImpl;
import com.qiang.qianqiancater.service.CuisineService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author QIANG
 */
public class CuisineServiceImpl implements CuisineService {

    CuisineDao cuisineDao = new CuisineDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public PageBean<Cuisine> getCuiSineByCategory(int categoryId, int pageSize, int currentPage) {
        PageBean<Cuisine> pageBean = new PageBean<>();
        try {
            //总记录数
            long totalrecordsNum = cuisineDao.getTotalNumByCategoryPutaway(categoryId);
            //对应的记录列表
            List<Cuisine> cuisineList = cuisineDao.getCuiSineByCategoryPutaway(categoryId, pageSize, currentPage);
            //总页数
            int totalPages = (int) (totalrecordsNum % pageSize == 0 ? totalrecordsNum / pageSize : totalrecordsNum / pageSize + 1);
            //封装pageBean对象
            pageBean.setCurrentPage(currentPage);
            pageBean.setPageSize(pageSize);
            pageBean.setTotalRecords((int) totalrecordsNum);
            pageBean.setTotalPages(totalPages);
            pageBean.setDataList(cuisineList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pageBean;
    }

    /**
     * 根据名字查询的菜品封装到分页对象中
     *
     * @param cname
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public PageBean<Cuisine> getCuiSineByName(String cname, int pageSize, int currentPage) {
        PageBean<Cuisine> pageBean = new PageBean<>();
        try {
            //总记录数
            long totalrecordsNum = cuisineDao.getTotalNumByNamePutaway(cname);
            //对应的记录列表
            List<Cuisine> cuisineList = cuisineDao.getCuiSineByNamePutaway(cname, pageSize, currentPage);
            //总页数
            int totalPages = (int) (totalrecordsNum % pageSize == 0 ? totalrecordsNum / pageSize : totalrecordsNum / pageSize + 1);
            //封装pageBean对象
            pageBean.setCurrentPage(currentPage);
            pageBean.setPageSize(pageSize);
            pageBean.setTotalRecords((int) totalrecordsNum);
            pageBean.setTotalPages(totalPages);
            pageBean.setDataList(cuisineList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pageBean;
    }

    /**
     * 根据ID获取菜品
     *
     * @param cid
     * @return
     */
    @Override
    public Cuisine getCuisineById(int cid) {
        try {
            //查询到菜品信息
            Cuisine cuisine = cuisineDao.getCuisineById(cid);
            //补充菜品的类别信息
            cuisine.setCategory(categoryDao.getCategoryById(cuisine.getCategoryId()));
            return cuisine;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取菜的大图
     *
     * @param cid
     * @return
     */
    @Override
    public String getCuisineBigImg(int cid) {
        String bigImgPath = "";
        try {
            bigImgPath = cuisineDao.getCuisineBigImg(cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bigImgPath;
    }

    /**
     * 获取特色菜
     *
     * @return
     */
    @Override
    public List<Cuisine> getSpecialtyCuisine() {
        List<Cuisine> cuisineList = null;
        try {
            cuisineList = cuisineDao.getSpecialtyCuisine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuisineList;
    }
}
