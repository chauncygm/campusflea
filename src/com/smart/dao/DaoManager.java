package com.smart.dao;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class DaoManager {

    /**
     * 枚举实现单例
     */
    private DaoManager(){}
    private enum Singleton{
        INSTANCE;
        DaoManager processor = new DaoManager();
        DaoManager getProcessor () {
            return processor;
        }
    }
    public static DaoManager getInstance() {
        return Singleton.INSTANCE.getProcessor();
    }

    @Inject
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

}
