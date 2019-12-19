package com.qiang.qianqiancater.dao.impl;

import com.qiang.qianqiancater.bean.User;
import com.qiang.qianqiancater.dao.UserDAO;
import com.qiang.qianqiancater.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author QIANG
 */
public class UserDAOImpl implements UserDAO {

    //jdbcTemplate
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //列名字符串
    String columnStr = "user_id,username,pswd , nickname, head_pic,email,gender ,registration_time, active_status, active_code";
    String columnStrQuery = "user_id userId,username,pswd , nickname, head_pic headPic,email ,gender ,registration_time registrationTime, active_status activeStatus, active_code activeCode";


    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT "+columnStrQuery+" FROM tab_user WHERE username=?";
        User user = null;
        try {
            user = (User) template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username);
        } catch (DataAccessException e) {
        }
        return user;
    }

    /**
     * 根据用户名查找一个用户对象
     *
     * @param id
     */
    @Override
    public User getUserByUserId(int id) {
        String sql = "SELECT "+columnStrQuery+" FROM tab_user WHERE user_id=?";
        User user = null;
        try {
            user = (User) template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),id);
        } catch (DataAccessException e) {
        }
        return user;
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into tab_user("+columnStr+") values(?,?,?,?,?,?,?,?,?,?)";
        int result = template.update(sql,
                user.getUserId(),
                user.getUsername(),
                user.getPswd(),
                user.getNickname(),
                user.getHeadPic(),
                user.getEmail(),
                user.getGender(),
                user.getRegistrationTime(),
                user.getActiveStatus(),
                user.getActiveCode());

        return result;
    }

    @Override
    public User getUserByUsernameAndPswd(String username, String pswd) {
        String sql = "SELECT "+columnStrQuery+" FROM tab_user WHERE username=? and pswd=?";
        User user = null;
        try {
            user = (User) template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username,pswd);
        } catch (DataAccessException e) {
        }
        return user;
    }

    @Override
    public int updateUser(User user,int id) {
        String sql = "update tab_user set username=?,pswd=? , " +
                "nickname=?, head_pic=?,email=?,gender=?," +
                "registration_time=?, active_status=?, active_code=?" +
                " where user_id=?";
        int result = template.update(sql,
                user.getUsername(),
                user.getPswd(),
                user.getNickname(),
                user.getHeadPic(),
                user.getEmail(),
                user.getGender(),
                user.getRegistrationTime(),
                user.getActiveStatus(),
                user.getActiveCode(),
                user.getUserId());
        return result;
    }

    @Override
    public int setActiveStatus(String activeCode) {
        String sql = "update tab_user set active_status=? where active_code=?";
        return template.update(sql,"Y",activeCode);
    }


}
