package com.qiang.qianqiancater.bean;

import java.sql.Blob;
import java.util.Date;

/**
 * @author QIANG
 */
public class User {
    private Integer userId;
    private String username;
    private String pswd;
    /*昵称*/
    private String nickname;
    /*头像*/
    private Blob headPic;
    private String email;
    /*性别*/
    private String gender;
    private Long registrationTime;
    /*用户状态*/
    private String activeStatus;
    /*激活需要的激活码*/
    private String activeCode;

    public User() {
        this.registrationTime = new Date().getTime();
        this.activeStatus = "D";
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

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
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



    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", pswd='" + pswd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headPic=" + headPic +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", registrationTime=" + registrationTime +
                ", activeStatus='" + activeStatus + '\'' +
                ", activeCode='" + activeCode + '\'' +
                '}';
    }
}
