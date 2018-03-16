package com.smart.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_campus")
public class Campus {

    @Id
    @Column("campus_id")
    @Comment("学校id")
    private int id;

    @Column("name")
    @Comment("学校名")
    private String name;

    @Column("area_id")
    @Comment("所在区域")
    private int areaId;

}
