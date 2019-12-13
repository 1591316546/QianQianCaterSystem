package com.qiang.qianqiancater.bean;

/**
 * @author QIANG
 * 后台管理员实体类
 */
public class Admin {
    private Integer id;//id
    private String username;//用户名
    private String password;
    private String role;//角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
