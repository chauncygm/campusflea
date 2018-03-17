package com.smart.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_message")
public class Message {

    @Id
    @Column("message_id")
    @Comment("消息id")
    private int messageId;

    @Column("send_id")
    @Comment("发送方id")
    private int sendId;

    @Column("reply_id")
    @Comment("接收方id")
    private int replyId;

    @Column("content")
    @Comment("内容")
    private String content;

    @Column("send_time")
    @Comment("发送时间")
    private long sendTime;

    @Column("is_read")
    @Comment("是否已读？0未读：1已读")
    private int isRead = 0;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", sendId=" + sendId +
                ", replyId=" + replyId +
                ", content='" + content + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
