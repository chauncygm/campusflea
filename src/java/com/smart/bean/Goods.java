package com.smart.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.mvc.annotation.Param;

@Table("t_goods")
public class Goods {

    @Id
    @Column("good_id")
    @Comment("商品id")
    private int goodId;

    @Column("user_id")
    @Comment("用户id")
    private int userId;

    @Column("good_name")
    @Comment("商品名")
    private String goodName;

    @Column("category_id")
    @Comment("类型id")
    private int categoryId;

    @Column("title")
    @Comment("标题")
    private String title;

    @Column("subtitle")
    @Comment("副标题")
    private String subTitle;

    @Column("primary_price")
    @Comment("原价格")
    private float primaryPrice = 0.00f;

    @Column("real_price")
    @Comment("实际价格")
    private float realPrice = 0.00f;

    @Column("good_pics")
    @Comment("商品图片")
    private String goodPics;

    @Column("content")
    @Comment("内容")
    private String content;

    @Column("put_time")
    @Comment("发布时间")
    private int putTime;

    @Column("deadline")
    @Comment("截止时间")
    private int deadline;

    @Column("view_num")
    @Comment("浏览数")
    private int viewNum = 0;

    @Column("love_num")
    @Comment("点赞数")
    private int loveNum = 0;

    @Column("collect_num")
    @Comment("收藏数")
    private int collectNum = 0;

    @Column("is_sell")
    @Comment("是否卖出？0否：1是")
    private int isSell = 0;

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public float getPrimaryPrice() {
        return primaryPrice;
    }

    public void setPrimaryPrice(float primaryPrice) {
        this.primaryPrice = primaryPrice;
    }

    public float getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(float realPrice) {
        this.realPrice = realPrice;
    }

    public String getGoodPics() {
        return goodPics;
    }

    public void setGoodPics(String goodPics) {
        this.goodPics = goodPics;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPutTime() {
        return putTime;
    }

    public void setPutTime(int putTime) {
        this.putTime = putTime;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public int getLoveNum() {
        return loveNum;
    }

    public void setLoveNum(int loveNum) {
        this.loveNum = loveNum;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public int getIsSell() {
        return isSell;
    }

    public void setIsSell(int isSell) {
        this.isSell = isSell;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodId=" + goodId +
                ", userId=" + userId +
                ", goodName='" + goodName + '\'' +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", primaryPrice=" + primaryPrice +
                ", realPrice=" + realPrice +
                ", goodPics='" + goodPics + '\'' +
                ", content='" + content + '\'' +
                ", putTime=" + putTime +
                ", deadline=" + deadline +
                ", viewNum=" + viewNum +
                ", loveNum=" + loveNum +
                ", collectNum=" + collectNum +
                ", isSell=" + isSell +
                '}';
    }
}
