package com.smart.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_user_log")
public class UserLog {

    @Id
    @Column("id")
    private int id;

    @Column("user_id")
    @Comment("用户id")
    private int userId;

    @Column("type")
    @Comment("操作类别")
    private int type;

    @Column("is_success")
    @Comment("是否成功？0否：1是")
    private int isSuccess;

    @Column("msg")
    @Comment("详细信息")
    private String msg;

    @Column("create_time")
    @Comment("操作时间")
    private long createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", isSuccess=" + isSuccess +
                ", msg='" + msg + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
