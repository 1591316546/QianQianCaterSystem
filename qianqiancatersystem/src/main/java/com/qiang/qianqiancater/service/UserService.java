package com.qiang.qianqiancater.service;

import com.qiang.qianqiancater.bean.User;

/**
 * @author QIANG
 */
public interface UserService {
    /**
     * 检查用户名唯一
     * @param username
     * @return
     */
    boolean checkUsernameUnique(String username);

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 激活账户
     * @param activeCode
     * @return
     */
    boolean activeAccount(String activeCode);

    /**
     * 登录
     * @param username
     * @param password
     * @return 返回状态。1 表示成功 2 账号未激活，3，账号被禁用，4，验证不通过
     */
    int login(String username, String password);

    /**
     * 根据用户名拿到用户信息去展示
     * @param username
     * @return
     */
    User getUserByUsername(String username);
}
