package com.smart.bean;

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
    private String nickname;

    @Column("real_name")
    @Comment("真实姓名")
    private String realname;

    @Column("avatar")
    @Comment("头像")
    private String avatar;

    @Column("introduce")
    @Comment("个人介绍")
    private String introduce;

    @Column("credit_score")
    @Comment("信用积分")
    private int creditScore = 500;

}
