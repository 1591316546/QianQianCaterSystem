package com.qiang.qianqiancater.dao.impl;

import com.qiang.qianqiancater.bean.Cuisine;
import com.qiang.qianqiancater.dao.CuisineDao;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author QIANG
 */
public class CuisineDaoImplTest {

    CuisineDao cuisineDao = new CuisineDaoImpl();

    @Test
    public void getCuiSineByCategory() {
        try {
            List<Cuisine> cuiSines = cuisineDao.getCuiSineByCategory(1, 5, 2);
            System.out.println(cuiSines);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}