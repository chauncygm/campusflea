package com.smart.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_feedback")
public class Feedback {

    @Id
    @Column("feedback_id")
    @Comment("反馈id")
    private int feedbackId;

    @Column("user_id")
    @Comment("用户id")
    private int userId;

    @Column("create_time")
    @Comment("创建时间")
    private long createTime;

    @Column("content")
    @Comment("内容")
    private String Content;

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", Content='" + Content + '\'' +
                '}';
    }
}
