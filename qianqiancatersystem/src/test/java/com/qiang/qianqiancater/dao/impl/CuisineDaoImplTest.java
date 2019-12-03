package com.qiang.qianqiancater.dao.impl;

import com.qiang.qianqiancater.bean.Cuisine;
import com.qiang.qianqiancater.dao.CuisineDao;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author QIANG
 */
public class CuisineDaoImplTest {

    CuisineDao cuisineDao = new CuisineDaoImpl();

    @Test
    public void getCuiSineByCategory() {
        try {
            List<Cuisine> cuiSines = cuisineDao.getCuiSineByCategoryPutaway(1, 5, 2);
            System.out.println(cuiSines);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testgetCuiSineByName(){
        try {
            List<Cuisine> cuiSines = cuisineDao.getCuiSineByNamePutaway("鱼", 5, 2);
            System.out.println(cuiSines);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}