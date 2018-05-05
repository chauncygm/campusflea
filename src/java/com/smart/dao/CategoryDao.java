package com.smart.dao;

import com.smart.bean.Category;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class CategoryDao {

    @Inject
    private NutDao dao;

    public List<Category> getSupCategory() {
        return dao.query(Category.class, Cnd.where("level", "=", 1));
    }

    public List<Category> getSubCategory(int cateId) {
        return dao.query(Category.class, Cnd.where("father_category", "=", cateId));
    }

}
