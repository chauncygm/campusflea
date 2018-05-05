package com.smart.to;

import com.smart.bean.User;

public class UserInfo {

    private String username;

    private String realname;

    private int sex;

    private String campus;

    private String email;

    private String mobile;

    private String  location;

    private String  introduce;

    private int publishs;

    private int likes;

    private int collections;

    public UserInfo() {}

    public UserInfo(User user) {
        this.username = user.getNickname();
        this.realname = user.getRealname();
        this.sex = user.getSex();
        this.email = user.getEmail();
        this.mobile = user.getMobile();
        this.introduce = user.getIntroduce();
        this.publishs = user.getPublishs();
        this.likes = user.getLikes();
        this.collections = user.getCollections();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getPublishs() {
        return publishs;
    }

    public void setPublishs(int publishs) {
        this.publishs = publishs;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getCollections() {
        return collections;
    }

    public void setCollections(int collections) {
        this.collections = collections;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", sex=" + sex +
                ", campus='" + campus + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", location='" + location + '\'' +
                ", introduce='" + introduce + '\'' +
                ", publishs=" + publishs +
                ", likes=" + likes +
                ", collections=" + collections +
                '}';
    }
}
