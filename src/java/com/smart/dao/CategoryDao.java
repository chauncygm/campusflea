package com.smart.dao;

import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class CategoryDao {

    @Inject
    private NutDao dao;

}
