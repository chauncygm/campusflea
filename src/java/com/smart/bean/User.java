package com.smart.bean;

import com.smart.utils.RUtil;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_user")
public class User {

    @Id(auto = false)
    @Column("user_id")
    @Comment("用户id")
    private int userId;

    @Column("nick_name")
    @Comment("用户昵称")
    private String nickname = "";

    @Column("real_name")
    @Comment("真实姓名")
    private String realname = "";

    @Column("sex")
    @Comment("性别（0：女，1：男）")
    private int sex = 1;

    @Column("email")
    @Comment("邮箱")
    private String email = "";

    @Column("mobile")
    @Comment("手机号")
    private String mobile = "";

    @Column("area_id")
    @Comment("所在区域")
    private int areaId = 0;

    @Column("campus_id")
    @Comment("学校id")
    private int campusId = 0;

    @Column("avatar")
    @Comment("头像")
    private String avatar = "";

    @Column("introduce")
    @Comment("个人介绍")
    private String introduce = "";

    @Column("friends")
    private String friends = "";

    @Column("publishs")
    @Comment("发布数")
    private int publishs = 0;

    @Column("likes")
    @Comment("点赞数")
    private int likes = 0;

    @Column("like_list")
    @Comment("点赞列表")
    private String likeList = "";

    @Column("collections")
    @Comment("收藏数")
    private int collections = 0;

    @Column("collection_list")
    @Comment("我的收藏")
    private String collectionList = "";

    @Column("credit_score")
    @Comment("信用积分")
    private int creditScore = 500;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
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

    public String getLikeList() {
        return likeList;
    }

    public void setLikeList(String likeList) {
        this.likeList = likeList;
    }

    public int getCollections() {
        return collections;
    }

    public void setCollections(int collections) {
        this.collections = collections;
    }

    public String getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(String collectionList) {
        this.collectionList = collectionList;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", realname='" + realname + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", areaId=" + areaId +
                ", campusId=" + campusId +
                ", avatar='" + avatar + '\'' +
                ", introduce='" + introduce + '\'' +
                ", friends='" + friends + '\'' +
                ", publishs=" + publishs +
                ", likes=" + likes +
                ", likeList='" + likeList + '\'' +
                ", collections=" + collections +
                ", collectionList='" + collectionList + '\'' +
                ", creditScore=" + creditScore +
                '}';
    }
}
