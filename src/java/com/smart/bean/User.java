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
    private String realnam = "";

    @Column("avatar")
    @Comment("头像")
    private String avatar = "";

    @Column("introduce")
    @Comment("个人介绍")
    private String introduce = "";

    @Column("friends")
    private String friends = "";

    @Column("credit_score")
    @Comment("信用积分")
    private int creditScore = 500;

}
