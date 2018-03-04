package com.smart.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_account")
public class Account {

    @Id
    @Column("account_id")
    @Comment("账号id")
    private int accountId;

    @Column("password")
    @Comment("账号密码")
    private String password;

    @Column("mobile")
    @Comment("绑定手机号")
    private String mobile;

    @Column("email")
    @Comment("绑定邮箱账号")
    private String email;

    @Column("uid")
    @Comment("设备唯一标志符")
    private String udi;

    @Column("create_time")
    @Comment("注册时间")
    private long creatTime;

    @Column("is_effect")
    @Comment("是否有效？1有效：0无效")
    private int isEffect = 1;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUdi() {
        return udi;
    }

    public void setUdi(String udi) {
        this.udi = udi;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    public int getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(int isEffect) {
        this.isEffect = isEffect;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", udi='" + udi + '\'' +
                ", creatTime=" + creatTime +
                ", isEffect=" + isEffect +
                '}';
    }
}
