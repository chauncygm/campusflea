package com.smart.dao;

import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class GoodsDao {

    @Inject
    private NutDao dao;

}
