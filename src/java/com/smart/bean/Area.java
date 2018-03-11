package com.smart.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_area")
public class Area {

    @Id
    @Column("area_id")
    @Comment("地区id")
    private int areaId;

    @Column("area_name")
    @Comment("地区名")
    private int areaName;

    @Column("level")
    @Comment("地区级别？1省：2市：3区县")
    private int level;

    @Column("father_area")
    @Comment("父级地区id")
    private int fatherArea;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getAreaName() {
        return areaName;
    }

    public void setAreaName(int areaName) {
        this.areaName = areaName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFatherArea() {
        return fatherArea;
    }

    public void setFatherArea(int fatherArea) {
        this.fatherArea = fatherArea;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                ", areaName=" + areaName +
                ", level=" + level +
                ", fatherArea=" + fatherArea +
                '}';
    }
}
