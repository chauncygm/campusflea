package com.smart.bean;

import org.nutz.dao.entity.annotation.*;

import java.util.Date;

@Table("t_account")
public class Account {

    @Id(auto = false)
    @Column("account_id")
    @Comment("账号id")
    private int accountId;

    @Name
    @Column("username")
    @Comment("用户名")
    private String username;

    @Column("password")
    @Comment("账号密码")
    private String password;

    @Column("mobile")
    @Comment("绑定手机号")
    private String mobile;

    @Column("create_time")
    @Comment("注册时间")
    private long creatTime;

    @Column("is_effect")
    @Comment("是否有效？1有效：0无效")
    private int isEffect;

    public Account() {}

    public Account(int accountId, String username, String mobile, String password) {
        this.accountId = accountId;
        this.isEffect = 1;
        this.username = username;
        this.mobile = mobile;
        this.password = password;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                ", creatTime=" + creatTime +
                ", isEffect=" + isEffect +
                '}';
    }
}
