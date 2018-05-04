package com.smart.to;

import java.util.List;
import java.util.Map;

public class CategoryInfo {

    private String supName;

    private Map<String, List<Category>> info;

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public Map<String, List<Category>> getInfo() {
        return info;
    }

    public void setInfo(Map<String, List<Category>> info) {
        this.info = info;
    }
}

class Category {

    private int id;

    private String pic;

    private String subName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}

