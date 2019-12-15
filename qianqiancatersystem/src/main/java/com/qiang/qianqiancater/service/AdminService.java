package com.qiang.qianqiancater.service;

import com.qiang.qianqiancater.bean.Admin;
import com.qiang.qianqiancater.dao.AdminDao;

/**
 * @author QIANG
 */
public class AdminService {
    AdminDao adminDao = new AdminDao();

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    public Admin checkLogin(String username, String password) {
        Admin admin = adminDao.getAdmin(username);
        if (admin == null){
            return null;
        }
        if (password != null){
            if (password.equals(admin.getPassword())){
                return admin;
            }
        }
        return null;
    }
}
