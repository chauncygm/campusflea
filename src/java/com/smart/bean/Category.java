package com.smart.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_category")
public class Category {

    @Id
    @Column("cate_id")
    @Comment("分类id")
    private int cateId;

    @Column("category_name")
    @Comment("分类名称")
    private String cateName;

    @Column("level")
    @Comment("分类级别：1(一级) 2(二级) 3(三级)")
    private String level;

    @Column("father_category")
    @Comment("父级类别")
    private int fatherCategory;

    @Column("cate_pic")
    @Comment("类别图片")
    private String catePic;

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getFatherCategory() {
        return fatherCategory;
    }

    public void setFatherCategory(int fatherCategory) {
        this.fatherCategory = fatherCategory;
    }

    public String getCatePic() {
        return catePic;
    }

    public void setCatePic(String catePic) {
        this.catePic = catePic;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cateId=" + cateId +
                ", cateName='" + cateName + '\'' +
                ", level='" + level + '\'' +
                ", fatherCategory=" + fatherCategory +
                ", catePic='" + catePic + '\'' +
                '}';
    }
}
