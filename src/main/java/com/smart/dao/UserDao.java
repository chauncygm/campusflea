package com.smart.dao;

import org.apache.log4j.Logger;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;

public class UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class);

    @Inject
    private NutDao dao;

    public int selectCount(){
        return 1;
    }
}
