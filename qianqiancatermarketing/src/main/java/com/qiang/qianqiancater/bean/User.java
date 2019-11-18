package com.qiang.qianqiancater.bean;

import java.sql.Blob;
import java.util.Date;

/**
 * @author QIANG
 */
public class User {
    private Integer userId;
    private String username;
    private String password;
    /*昵称*/
    private String nickname;
    /*头像*/
    private Blob headPic;
    private String email;
    private Date brith;
    /*性别*/
    private String gender;
    private Long registrationTime;
    /*用户状态*/
    private String status;
    /*激活需要的激活码*/
    private String activationCode;

    public User() {
        this.registrationTime = new Date().getTime();
        this.status = "D";
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Blob getHeadPic() {
        return headPic;
    }

    public void setHeadPic(Blob headPic) {
        this.headPic = headPic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBrith() {
        return brith;
    }

    /**
     * 数据库存储生日专用
     * @return
     */
    public java.sql.Date getBrithForSql() {
        return new java.sql.Date(brith.getTime());
    }

    public void setBrith(Date brith) {
        this.brith = brith;
    }

    /**
     * sql Date转为util.Date
     * @param brith
     */
    public void setBrith(java.sql.Date brith) {
        this.brith = new Date(brith.getTime());
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Long registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headPic=" + headPic +
                ", email='" + email + '\'' +
                ", brith=" + brith +
                ", gender='" + gender + '\'' +
                ", registrationTime=" + registrationTime +
                ", status='" + status + '\'' +
                ", activationCode='" + activationCode + '\'' +
                '}';
    }
}
