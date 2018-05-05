package com.smart.dto;

import java.util.List;

public class CategoryInfo {

    private String name;

    private String picture;

    private List<CategoryInfo> subCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<CategoryInfo> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<CategoryInfo> subCategory) {
        this.subCategory = subCategory;
    }
}

