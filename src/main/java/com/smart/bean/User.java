package com.smart.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_user")
public class User {

    @Id
    @Comment("用户id")
    private int id;

    @Column
    @Comment("用户昵称")
    private String nickname;

    @Column
    @Comment("真实姓名")
    private String realname;

    @Column
    @Comment("账号密码")
    private String password;

    @Column
    @Comment("绑定手机号")
    private String mobile;

    @Column
    @Comment("邮箱账号")
    private String email;

    @Column
    @Comment("是否有效：1有效，0无效")
    private int is_effect;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", realname='" + realname + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", is_effect=" + is_effect +
                '}';
    }
}
