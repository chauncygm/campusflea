package com.smart.bean;

import com.smart.utils.RUtil;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_user")
public class User {

    @Id
    @Column("user_id")
    @Comment("用户id")
    private int userId;

    @Column("nick_name")
    @Comment("用户昵称")
    private String nickname = RUtil.UUID();

    @Column("real_name")
    @Comment("真实姓名")
    private String realname = "";

    @Column("sex")
    @Comment("性别（0：女，1：男）")
    private int sex;

    @Column("email")
    @Comment("邮箱")
    private int email;

    @Column("area_id")
    @Comment("所在区域")
    private int areaId;

    @Column("campus_id")
    @Comment("学校id")
    private int campusId;

    @Column("avatar")
    @Comment("头像")
    private String avatar = "";

    @Column("introduce")
    @Comment("个人介绍")
    private String introduce = "";

    @Column("friends")
    private String friends = "";

    @Column("likes")
    @Comment("点赞数")
    private int likes;

    @Column("like_list")
    @Comment("点赞列表")
    private String likeList;

    @Column("collections")
    @Comment("收藏数")
    private int collections;

    @Column("collection_list")
    @Comment("我的收藏")
    private String collectionList;

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

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
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
                ", email=" + email +
                ", areaId=" + areaId +
                ", campusId=" + campusId +
                ", avatar='" + avatar + '\'' +
                ", introduce='" + introduce + '\'' +
                ", friends='" + friends + '\'' +
                ", likes=" + likes +
                ", likeList='" + likeList + '\'' +
                ", collections=" + collections +
                ", collectionList='" + collectionList + '\'' +
                ", creditScore=" + creditScore +
                '}';
    }
}
