package com.smart.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_comment")
public class Comments {

    @Id
    @Column("comment_id")
    @Comment("评论id")
    private int commentId;

    @Column("good_id")
    @Comment("商品id")
    private int goodsId;

    @Column("user_id")
    @Comment("用户id")
    private int userId;

    @Column("reply_id")
    @Comment("回复评论的id")
    private int replyId;

    @Column("is_delete")
    @Comment("是否已删除？0未删除：1已删除")
    private int isDelete;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", goodsId=" + goodsId +
                ", userId=" + userId +
                ", replyId=" + replyId +
                ", isDelete=" + isDelete +
                '}';
    }
}
