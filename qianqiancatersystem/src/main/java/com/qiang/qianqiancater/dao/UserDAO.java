package com.qiang.qianqiancater.dao;

import com.qiang.qianqiancater.bean.User;

/**
 * @author QIANG
 */
public interface UserDAO {
    /**
     * 根据用户名查找一个用户对象
     * @param username
     */
    User getUserByUsername(String username);

    /**
     * 根据用户名查找一个用户对象
     * @param id
     */
    User getUserByUserId(int id);

    /**
     * 保存用户对象
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 根据用户名和密码查找用户，登录校验
     * @param username
     * @param pswd
     * @return
     */
    User getUserByUsernameAndPswd(String username,String pswd);

    /**
     * 根据id修改用户信息
     * @param user
     * @return 影响的行数
     */
    int updateUser(User user,int id);

    /**
     * 通过激活码设置激活状态
     * @param activeCode
     * @return
     */
    int setActiveStatus(String activeCode);
}
